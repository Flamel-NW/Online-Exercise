package com.garret.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.garret.entity.User;

public interface UserService extends IService<User> {
    boolean modifyById(User user);
    IPage<User> getPageByCondition(long currentPage, long pageSize, User user);

    User userLogin(User user);
}
