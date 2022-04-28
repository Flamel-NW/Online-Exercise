package com.garret.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.garret.entity.User;
import com.garret.mapper.UserMapper;
import com.garret.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean modifyById(User user) {
        return userMapper.updateById(user) > 0;
    }

    @Override
    public IPage<User> getPageByCondition(long currentPage, long pageSize, User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.hasLength(user.getUsername()), User::getUsername, user.getUsername());
        lambdaQueryWrapper.like(StringUtils.hasLength(user.getPassword()), User::getPassword, user.getPassword());
        IPage<User> iPage = new Page<>(currentPage, pageSize);
        return userMapper.selectPage(iPage, lambdaQueryWrapper);
    }

    @Override
    public User userLogin(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        lambdaQueryWrapper.eq(User::getPassword, user.getPassword());
        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        return userList.isEmpty() ? null : userList.get(0);
    }
}
