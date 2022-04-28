package com.garret.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.garret.entity.ExamToQuestion;
import com.garret.entity.QuestionAnswer;

public interface ExamToQuestionService extends IService<ExamToQuestion> {
    QuestionAnswer getRandom(Integer eid);
}
