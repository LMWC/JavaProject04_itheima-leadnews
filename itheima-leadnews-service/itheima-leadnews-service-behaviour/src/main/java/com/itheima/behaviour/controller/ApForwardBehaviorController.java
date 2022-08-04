package com.itheima.behaviour.controller;


import com.itheima.behaviour.pojo.ApForwardBehavior;
import com.itheima.behaviour.service.ApForwardBehaviorService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP转发行为表 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP转发行为表",tags = "ApForwardBehaviorController")
@RestController
@RequestMapping("/apForwardBehavior")
public class ApForwardBehaviorController extends AbstractCoreController<ApForwardBehavior> {

    private ApForwardBehaviorService apForwardBehaviorService;

    //注入
    @Autowired
    public ApForwardBehaviorController(ApForwardBehaviorService apForwardBehaviorService) {
        super(apForwardBehaviorService);
        this.apForwardBehaviorService=apForwardBehaviorService;
    }

}

