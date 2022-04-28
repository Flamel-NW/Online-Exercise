package com.garret.controller.utils;

import com.garret.entity.controller.R;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler
    public R doDataIntegrityViolationException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return new R(101, "Database Error", null);
    }

    @ExceptionHandler
    public R doException(Exception e) {
        e.printStackTrace();
        return new R(100, "Server Error", null);
    }
}
