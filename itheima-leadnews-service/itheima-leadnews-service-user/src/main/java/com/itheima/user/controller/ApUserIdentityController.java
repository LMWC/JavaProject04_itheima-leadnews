package com.itheima.user.controller;


import com.itheima.user.pojo.ApUserIdentity;
import com.itheima.user.service.ApUserIdentityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP身份认证信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP身份认证信息表",tags = "ApUserIdentityController")
@RestController
@RequestMapping("/apUserIdentity")
public class ApUserIdentityController extends AbstractCoreController<ApUserIdentity> {

    private ApUserIdentityService apUserIdentityService;

    //注入
    @Autowired
    public ApUserIdentityController(ApUserIdentityService apUserIdentityService) {
        super(apUserIdentityService);
        this.apUserIdentityService=apUserIdentityService;
    }

}

