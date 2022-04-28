package com.garret.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garret.entity.Param;
import com.garret.mapper.ParamMapper;
import com.garret.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ParamServiceImpl extends ServiceImpl<ParamMapper, Param> implements ParamService {
    private ParamMapper paramMapper;

    @Autowired
    public void setParamMapper(ParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    @Override
    public boolean modifyById(Param param) {
        return paramMapper.updateById(param) > 0;
    }

    @Override
    public IPage<Param> getPageByCondition(long currentPage, long pageSize, Param param) {
        LambdaQueryWrapper<Param> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(param.getQid() != null, Param::getQid, param.getQid());
        lambdaQueryWrapper.like(StringUtils.hasLength(param.getParam()), Param::getParam, param.getParam());
        lambdaQueryWrapper.eq(param.getWeight() != null, Param::getWeight, param.getWeight());
        IPage<Param> iPage = new Page<>(currentPage, pageSize);
        return paramMapper.selectPage(iPage, lambdaQueryWrapper);
    }
}
