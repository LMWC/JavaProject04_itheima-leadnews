package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdRoleAuth;
import com.itheima.admin.service.AdRoleAuthService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 角色权限信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="角色权限信息表",tags = "AdRoleAuthController")
@RestController
@RequestMapping("/adRoleAuth")
public class AdRoleAuthController extends AbstractCoreController<AdRoleAuth> {

    private AdRoleAuthService adRoleAuthService;

    //注入
    @Autowired
    public AdRoleAuthController(AdRoleAuthService adRoleAuthService) {
        super(adRoleAuthService);
        this.adRoleAuthService=adRoleAuthService;
    }

}

