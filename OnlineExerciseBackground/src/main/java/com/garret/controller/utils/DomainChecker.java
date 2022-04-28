package com.garret.controller.utils;

import com.garret.entity.Param;
import com.garret.entity.Question;
import com.garret.entity.User;
import com.garret.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class DomainChecker {
    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    public String checkUser(User user) {
        if (!StringUtils.hasLength(user.getUsername())) return "Username can not be null.";
        else if (!StringUtils.hasLength(user.getPassword())) return "Password can not be null.";
        return null;
    }

    public String checkQuestion(Question question) {
        if (!StringUtils.hasLength(question.getStem())) return "Stem can not be null.";
        else if (question.getWeight() == null || question.getWeight() < 0 || question.getWeight() > 100)
            return "Weight must be in the range of 0~100.";

        else if (question.getChosenTimes() == null) return "ChosenTimes can not be null.";
        else if (question.getTotalScore() == null) return "TotalScore can not be null.";
        else if (question.getCheatTimes() == null) return "CheatTimes can not be null.";

        else if (!StringUtils.hasLength(question.getOption1())) return "Option1 can not be null.";
        else if (!StringUtils.hasLength(question.getOption2())) return "Option2 can not be null.";
        else if (!StringUtils.hasLength(question.getOption3())) return "Option3 can not be null.";
        else if (!StringUtils.hasLength(question.getOption4())) return "Option4 can not be null.";
        else if (!StringUtils.hasLength(question.getOption5())) return "Option5 can not be null.";
        return null;
    }

    public String checkParam(Param param) {
        if (param.getQid() == null) return "QuestionId can not be null.";
        else if (!StringUtils.hasLength(param.getParam())) return "Param can not be null";
        else if (param.getWeight() == null || param.getWeight() < 0 || param.getWeight() > 100)
            return "Weight must be in the range of 0~100.";
        else if (questionService.getById(param.getQid()) == null) return "QuestionId does not exist.";

        if (param.getAnswer1() == null) param.setAnswer1(Boolean.FALSE);
        if (param.getAnswer2() == null) param.setAnswer2(Boolean.FALSE);
        if (param.getAnswer3() == null) param.setAnswer3(Boolean.FALSE);
        if (param.getAnswer4() == null) param.setAnswer4(Boolean.FALSE);
        if (param.getAnswer5() == null) param.setAnswer5(Boolean.FALSE);

        return null;
    }
}
