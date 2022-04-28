package com.garret.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garret.entity.Exam;

public interface ExamService extends IService<Exam> {
    boolean modifyById(Exam exam);
    IPage<Exam> getPageByCondition(long currentPage, long pageSize, Exam exam);

    Exam beginExam(Exam exam);
    Integer endExam(Integer eid);
}
