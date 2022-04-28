package com.garret.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garret.controller.utils.DomainChecker;
import com.garret.entity.controller.R;
import com.garret.entity.User;
import com.garret.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background/users")
public class BackgroundUserController {
    private UserService userService;
    private DomainChecker domainChecker;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setDomainChecker(DomainChecker domainChecker) {
        this.domainChecker = domainChecker;
    }

    @GetMapping
    public R getAll() {
        return new R(0, "Get Success", userService.list());
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new R(user == null ? 1 : 0, user == null ? "Get Fail" : "Get Success", user);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPageByCondition(@PathVariable Long currentPage, @PathVariable Long pageSize, User user) {
        IPage<User> iPage = userService.getPageByCondition(currentPage, pageSize, user);
        if (iPage.getPages() < currentPage)
            iPage = userService.getPageByCondition(iPage.getPages(), pageSize, user);
        return new R(0, "Get Success", iPage);
    }

    @PostMapping
    public R save(@RequestBody User user) {
        String checkUser = domainChecker.checkUser(user);
        if (checkUser != null)
            return new R(2, checkUser, null);
        boolean flag = userService.save(user);
        return new R(flag ? 0 : 1, flag ? "Save Success" : "Save Fail", null);
    }

    @PutMapping
    public R modifyById(@RequestBody User user) {
        String checkUser = domainChecker.checkUser(user);
        if (checkUser != null)
            return new R(2, checkUser, null);
        boolean flag = userService.modifyById(user);
        return new R(flag ? 0 : 1, flag ? "Modify Success" : "Modify Fail", null);
    }

    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        boolean flag = userService.removeById(id);
        return new R(flag ? 0 : 1, flag ? "Delete Success" : "Delete Fail", null);
    }
}
