package com.garret.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.garret.controller.utils.DomainChecker;
import com.garret.entity.controller.R;
import com.garret.entity.Param;
import com.garret.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/background/params")
public class BackgroundParamController {
    private ParamService paramService;
    private DomainChecker domainChecker;

    @Autowired
    public void setParamService(ParamService paramService) {
        this.paramService = paramService;
    }

    @Autowired
    public void setDomainChecker(DomainChecker domainChecker) {
        this.domainChecker = domainChecker;
    }

    @GetMapping
    public R getAll() {
        return new R(0, "Get Success", paramService.list());
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        Param param = paramService.getById(id);
        return new R(param == null ? 1 : 0, param == null ? "Get Fail" : "Get Success", param);
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getPageByCondition(@PathVariable Long currentPage, @PathVariable Long pageSize, Param param) {
        IPage<Param> iPage = paramService.getPageByCondition(currentPage, pageSize, param);
        if (iPage.getPages() < currentPage)
            iPage = paramService.getPageByCondition(iPage.getPages(), pageSize, param);
        return new R(0, "Get Success", iPage);
    }

    @PostMapping
    public R save(@RequestBody Param param) {
        String checkParam = domainChecker.checkParam(param);
        if (checkParam != null)
            return new R(2, checkParam, null);
        boolean flag = paramService.save(param);
        return new R(flag ? 0 : 1, flag ? "Save Success" : "Save Fail", null);
    }

    @PutMapping
    public R modifyById(@RequestBody Param param) {
        String checkParam = domainChecker.checkParam(param);
        if (checkParam != null)
            return new R(2, checkParam, null);
        boolean flag = paramService.modifyById(param);
        return new R(flag ? 0 : 1, flag ? "Modify Success" : "Modify Fail", null);
    }

    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        if (id <= 20)
            return new R(3, "Can not delete default param.", null);
        boolean flag = paramService.removeById(id);
        return new R(flag ? 0 : 1, flag ? "Delete Success" : "Delete Fail", null);
    }
}
