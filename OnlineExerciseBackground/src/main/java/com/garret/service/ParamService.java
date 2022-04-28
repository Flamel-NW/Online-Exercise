package com.garret.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garret.entity.Param;

public interface ParamService extends IService<Param> {
    boolean modifyById(Param param);
    IPage<Param> getPageByCondition(long currentPage, long pageSize, Param param);
}
