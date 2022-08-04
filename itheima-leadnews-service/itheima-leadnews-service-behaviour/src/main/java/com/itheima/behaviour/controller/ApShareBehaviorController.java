package com.itheima.behaviour.controller;


import com.itheima.behaviour.pojo.ApShareBehavior;
import com.itheima.behaviour.service.ApShareBehaviorService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP分享行为表 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP分享行为表",tags = "ApShareBehaviorController")
@RestController
@RequestMapping("/apShareBehavior")
public class ApShareBehaviorController extends AbstractCoreController<ApShareBehavior> {

    private ApShareBehaviorService apShareBehaviorService;

    //注入
    @Autowired
    public ApShareBehaviorController(ApShareBehaviorService apShareBehaviorService) {
        super(apShareBehaviorService);
        this.apShareBehaviorService=apShareBehaviorService;
    }

}

