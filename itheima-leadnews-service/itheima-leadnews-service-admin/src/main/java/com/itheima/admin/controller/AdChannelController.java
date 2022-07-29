package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdChannel;
import com.itheima.admin.service.AdChannelService;
import com.itheima.common.pojo.Result;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* <p>
* 频道信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="频道信息表",tags = "AdChannelController")
@RestController
@RequestMapping("/adChannel")
public class AdChannelController extends AbstractCoreController<AdChannel> {

    private AdChannelService adChannelService;

    //注入
    @Autowired
    public AdChannelController(AdChannelService adChannelService) {
        super(adChannelService);
        this.adChannelService=adChannelService;
    }

    @GetMapping("/test")
    public Result test(HttpServletRequest req){
        int i=1/0;
        return Result.ok();
    }

}

