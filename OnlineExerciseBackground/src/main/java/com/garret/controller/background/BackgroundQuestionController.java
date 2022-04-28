package com.garret.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garret.controller.utils.DomainChecker;
import com.garret.entity.controller.R;
import com.garret.entity.Question;
import com.garret.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background/questions")
public class BackgroundQuestionController {
    private QuestionService questionService;
    private DomainChecker domainChecker;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setDomainChecker(DomainChecker domainChecker) {
        this.domainChecker = domainChecker;
    }
    
    @GetMapping
    public R getAll() {
        return new R(0, "Get Success", questionService.list());
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Question question = questionService.getById(id);
        return new R(question == null ? 1 : 0, question == null ? "Get Fail" : "Get Success", question);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPage(@PathVariable Long currentPage, @PathVariable Long pageSize) {
        IPage<Question> iPage = questionService.getPage(currentPage, pageSize);
        if (iPage.getPages() < currentPage)
            iPage = questionService.getPage(iPage.getPages(), pageSize);
        return new R(0, "Get Success", iPage);
    }

    @PutMapping
    public R modifyById(@RequestBody Question question) {
        String checkQuestion = domainChecker.checkQuestion(question);
        if (checkQuestion != null)
            return new R(2, checkQuestion, null);
        boolean flag = questionService.modifyById(question);
        return new R(flag ? 0 : 1, flag ? "Modify Success" : "Modify Fail", null);
    }
}
