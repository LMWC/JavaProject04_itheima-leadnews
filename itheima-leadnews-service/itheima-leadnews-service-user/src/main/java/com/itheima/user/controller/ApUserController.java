package com.itheima.user.controller;


import com.itheima.user.pojo.ApUser;
import com.itheima.user.service.ApUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户信息表",tags = "ApUserController")
@RestController
@RequestMapping("/apUser")
public class ApUserController extends AbstractCoreController<ApUser> {

    private ApUserService apUserService;

    //注入
    @Autowired
    public ApUserController(ApUserService apUserService) {
        super(apUserService);
        this.apUserService=apUserService;
    }

}

