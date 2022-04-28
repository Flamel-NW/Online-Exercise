package com.garret.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garret.entity.Exam;
import com.garret.entity.ExamToQuestion;
import com.garret.entity.Param;
import com.garret.entity.Question;
import com.garret.entity.QuestionAnswer;
import com.garret.mapper.ExamMapper;
import com.garret.mapper.ExamToQuestionMapper;
import com.garret.mapper.ParamMapper;
import com.garret.mapper.QuestionMapper;
import com.garret.service.ExamToQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ExamToQuestionServiceImpl
        extends ServiceImpl<ExamToQuestionMapper, ExamToQuestion>
        implements ExamToQuestionService {
    private ExamToQuestionMapper examToQuestionMapper;
    private ExamMapper examMapper;
    private QuestionMapper questionMapper;
    private ParamMapper paramMapper;

    @Autowired
    public void setExamToQuestionMapper(ExamToQuestionMapper examToQuestionMapper) {
        this.examToQuestionMapper = examToQuestionMapper;
    }

    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Autowired
    public void setParamMapper(ParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    @Value("${placeholder}")
    private String placeholder;

    @Override
    public boolean save(ExamToQuestion examToQuestion) {
        Question question = questionMapper.selectById(examToQuestion.getQid());
        question.setChosenTimes(question.getChosenTimes() + 1);
        question.setTotalScore(question.getTotalScore() + examToQuestion.getScore());

        Exam exam = examMapper.selectById(examToQuestion.getEid());
        exam.setScore(exam.getScore() + examToQuestion.getScore());

        LambdaQueryWrapper<ExamToQuestion> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ExamToQuestion::getEid, examToQuestion.getEid());
        lambdaQueryWrapper.eq(ExamToQuestion::getQid, examToQuestion.getQid());

        return examToQuestionMapper.update(examToQuestion, lambdaQueryWrapper) > 0
                && questionMapper.updateById(question) > 0
                && examMapper.updateById(exam) > 0;
    }

    @Override
    public QuestionAnswer getRandom(Integer eid) {
        Exam exam = examMapper.selectById(eid);
        Question question = getRandomQuestion(exam);
        if (question == null) return null;

        ExamToQuestion examToQuestion = new ExamToQuestion();
        examToQuestion.setEid(exam.getId());
        examToQuestion.setQid(question.getId());
        examToQuestion.setScore(0);
        examToQuestionMapper.insert(examToQuestion);

        return new QuestionAnswer(question, getRandomParam(question), placeholder);
    }

    private Question getRandomQuestion(Exam exam) {
        LambdaQueryWrapper<ExamToQuestion> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ExamToQuestion::getEid, exam.getId());
        List<ExamToQuestion> list = examToQuestionMapper.selectList(lambdaQueryWrapper);
        if (list.size() >= 10)
            return null;

        int[] counts = new int[5];
        boolean[] flags = new boolean[20];
        for (ExamToQuestion examToQuestion : list) {
            int qid = examToQuestion.getQid() - 1;
            flags[qid] = true;
            counts[qid / 4]++;
        }
        for (int i = 0; i < 5; i++)
            if (counts[i] == 2)
                for (int j = 0; j < 4; j++)
                    flags[i * 4 + j] = true;

        int[] weights = new int[20];
        for (int i = 0; i < 20; i++) {
            if (flags[i]) {
                weights[i] = 0;
                continue;
            }
            Question question = questionMapper.selectById(i + 1);
            int weight = Math.max(question.getWeight() - question.getCheatTimes() * 10, 0);
            weights[i] = weight;
        }
        List<Integer> weightList = new ArrayList<>();
        for (int weight : weights)
            weightList.add(weight);
        int randomInt = weightedRandom(weightList);
        return questionMapper.selectById(randomInt + 1);
    }

    private Param getRandomParam(Question question) {
        String stem = question.getStem();
        if (stem.contains(placeholder)) {
            LambdaQueryWrapper<Param> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Param::getQid, question.getId());
            List<Param> paramList = paramMapper.selectList(lambdaQueryWrapper);

            List<Integer> weightList = new ArrayList<>();
            for (Param param : paramList)
                weightList.add(param.getWeight());
            int randomInt = weightedRandom(weightList);

            return paramList.get(randomInt);
        } else
            return paramMapper.selectById(question.getId());
    }

    private int weightedRandom(List<Integer> weight) {
        int total = 0;
        for (Integer integer : weight)
            total += integer;

        Random random = new Random();
        int randomInt = random.nextInt(total);
        for (int i = 0; i < weight.size(); i++) {
            randomInt -= weight.get(i);
            if (randomInt < 0)
                return i;
        }
        return -1;
    }
}
