package com.garret.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garret.entity.Exam;
import com.garret.entity.controller.R;
import com.garret.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/background/exams")
public class BackgroundExamController {
    private ExamService examService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public R getAll() {
        return new R(0, "Get Success", examService.list());
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Exam exam = examService.getById(id);
        return new R(exam == null ? 1 : 0, exam == null ? "Get Fail" : "Get Success", exam);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPageByCondition(@PathVariable Long currentPage, @PathVariable Long pageSize, Exam exam) {
        IPage<Exam> iPage = examService.getPageByCondition(currentPage, pageSize, exam);
        if (iPage.getPages() < currentPage)
            iPage = examService.getPageByCondition(iPage.getPages(), pageSize, exam);
        return new R(0, "Get Success", iPage);
    }
}
