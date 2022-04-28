package com.garret.controller;

import com.garret.entity.User;
import com.garret.entity.controller.R;
import com.garret.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Value("${background.admin-name}")
    private String adminName;

    @Value("${background.admin-pwd}")
    private String adminPwd;

    @PostMapping
    public R login(@RequestBody User user) {
        if (adminName.equals(user.getUsername()) && adminPwd.equals(user.getPassword()))
            return new R(0, "Admin Login Success", null);
        user = userService.userLogin(user);
        if (user != null)
            return new R(0, "User Login Success", user);
        return new R(1, "Login Fail", null);
    }
}
