package com.garret.controller.foreground;

import com.garret.entity.Exam;
import com.garret.entity.ExamToQuestion;
import com.garret.entity.controller.R;
import com.garret.service.ExamService;
import com.garret.service.ExamToQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foreground")
public class ForegroundController {
    private ExamService examService;
    private ExamToQuestionService examToQuestionService;

    @Autowired
    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    @Autowired
    public void setExamToQuestionService(ExamToQuestionService examToQuestionService) {
        this.examToQuestionService = examToQuestionService;
    }

    @PostMapping()
    public R beginExam(@RequestBody Exam exam) {
        exam = examService.beginExam(exam);
        return new R(0, "Begin Success", exam);
    }

    @PutMapping
    public R nextQuestion(@RequestBody ExamToQuestion examToQuestion) {
        if (examToQuestion.getQid() == 0)
            return new R(0, "Submit Success", examToQuestionService.getRandom(examToQuestion.getEid()));
        boolean flag = examToQuestionService.save(examToQuestion);
        return new R(flag ? 0 : 1, flag ? "Submit Success" : "Submit Fail"
                , examToQuestionService.getRandom(examToQuestion.getEid()));
    }

    @DeleteMapping("/{eid}")
    public R endExam(@PathVariable Integer eid) {
        return new R(0, "End Success", examService.endExam(eid));
    }
}
