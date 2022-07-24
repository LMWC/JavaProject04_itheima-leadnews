package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdUserLogin;
import com.itheima.admin.service.AdUserLoginService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 管理员登录行为信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="管理员登录行为信息表",tags = "AdUserLoginController")
@RestController
@RequestMapping("/adUserLogin")
public class AdUserLoginController extends AbstractCoreController<AdUserLogin> {

    private AdUserLoginService adUserLoginService;

    //注入
    @Autowired
    public AdUserLoginController(AdUserLoginService adUserLoginService) {
        super(adUserLoginService);
        this.adUserLoginService=adUserLoginService;
    }

}

