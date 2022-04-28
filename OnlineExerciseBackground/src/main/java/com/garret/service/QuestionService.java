package com.garret.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garret.entity.Question;

public interface QuestionService extends IService<Question> {
    boolean modifyById(Question question);
    IPage<Question> getPage(Long currentPage, Long pageSize);
}
