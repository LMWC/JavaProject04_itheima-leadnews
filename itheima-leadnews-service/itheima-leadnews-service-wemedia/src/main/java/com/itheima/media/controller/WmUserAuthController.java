package com.itheima.media.controller;


import com.itheima.media.pojo.WmUserAuth;
import com.itheima.media.service.WmUserAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 自媒体子账号权限信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体子账号权限信息表",tags = "WmUserAuthController")
@RestController
@RequestMapping("/wmUserAuth")
public class WmUserAuthController extends AbstractCoreController<WmUserAuth> {

    private WmUserAuthService wmUserAuthService;

    //注入
    @Autowired
    public WmUserAuthController(WmUserAuthService wmUserAuthService) {
        super(wmUserAuthService);
        this.wmUserAuthService=wmUserAuthService;
    }

}

