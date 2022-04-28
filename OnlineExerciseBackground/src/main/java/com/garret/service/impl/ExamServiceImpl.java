package com.garret.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garret.entity.Exam;
import com.garret.entity.ExamToQuestion;
import com.garret.entity.Question;
import com.garret.mapper.ExamMapper;
import com.garret.mapper.ExamToQuestionMapper;
import com.garret.mapper.QuestionMapper;
import com.garret.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ExamServiceImpl extends ServiceImpl<ExamMapper, Exam> implements ExamService {
    private ExamMapper examMapper;
    private ExamToQuestionMapper examToQuestionMapper;
    private QuestionMapper questionMapper;

    @Autowired
    public void setExamMapper(ExamMapper examMapper) {
        this.examMapper = examMapper;
    }

    @Autowired
    public void setExamToQuestionMapper(ExamToQuestionMapper examToQuestionMapper) {
        this.examToQuestionMapper = examToQuestionMapper;
    }

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public boolean modifyById(Exam exam) {
        return examMapper.updateById(exam) > 0;
    }

    @Override
    public IPage<Exam> getPageByCondition(long currentPage, long pageSize, Exam exam) {
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(exam.getUid() != null, Exam::getUid, exam.getUid());
        lambdaQueryWrapper.like(StringUtils.hasLength(exam.getStartTime()), Exam::getStartTime, exam.getStartTime());
        IPage<Exam> iPage = new Page<>(currentPage, pageSize);
        return examMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public Exam beginExam(Exam exam) {
        LambdaQueryWrapper<Exam> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Exam::getUid, exam.getUid());
        List<Exam> examList = examMapper.selectList(lambdaQueryWrapper);
        if (examList.size() == 0) {
            examMapper.insert(exam);
            return exam;
        } else {
            lambdaQueryWrapper.orderByDesc(Exam::getStartTime);
            Exam examNearest = examMapper.selectList(lambdaQueryWrapper).get(0);
            if (!examNearest.getFinished())
                if (Long.parseLong(exam.getStartTime()) - Long.parseLong(examNearest.getStartTime()) < 60 * 60 * 1000)
                    return examNearest;
                else
                    endExam(examNearest.getId());
            examMapper.insert(exam);
            return exam;
        }
    }

    @Override
    public Integer endExam(Integer eid) {
        Exam exam = examMapper.selectById(eid);
        exam.setFinished(Boolean.TRUE);
        examMapper.updateById(exam);
        if (exam.getScore() < 8)
            checkCheat(eid);
        return exam.getScore();
    }

    private void checkCheat(Integer eid) {
        LambdaQueryWrapper<ExamToQuestion> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ExamToQuestion::getEid, eid);
        lambdaQueryWrapper.eq(ExamToQuestion::getScore, 2);
        List<ExamToQuestion> examToQuestionList = examToQuestionMapper.selectList(lambdaQueryWrapper);
        for (ExamToQuestion examToQuestion : examToQuestionList) {
            Question question = questionMapper.selectById(examToQuestion.getQid());
            if (question.getTotalScore().doubleValue() / question.getChosenTimes().doubleValue() < 0.2)
                question.setCheatTimes(question.getCheatTimes() + 1);
            questionMapper.updateById(question);
        }
    }
}
