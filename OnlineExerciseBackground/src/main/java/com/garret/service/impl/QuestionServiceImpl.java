package com.garret.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garret.entity.Question;
import com.garret.mapper.QuestionMapper;
import com.garret.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {
    private QuestionMapper questionMapper;

    @Autowired
    public void setQuestionMapper(QuestionMapper questionMapper) {
        this.questionMapper = questionMapper;
    }

    @Override
    public boolean modifyById(Question question) {
        return questionMapper.updateById(question) > 0;
    }

    @Override
    public IPage<Question> getPage(Long currentPage, Long pageSize) {
        LambdaQueryWrapper<Question> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        IPage<Question> iPage = new Page<>(currentPage, pageSize);
        return questionMapper.selectPage(iPage, lambdaQueryWrapper);
    }
}
