package com.itheima.behaviour.controller;


import com.itheima.behaviour.pojo.ApFollowBehavior;
import com.itheima.behaviour.service.ApFollowBehaviorService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP关注行为表 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP关注行为表",tags = "ApFollowBehaviorController")
@RestController
@RequestMapping("/apFollowBehavior")
public class ApFollowBehaviorController extends AbstractCoreController<ApFollowBehavior> {

    private ApFollowBehaviorService apFollowBehaviorService;

    //注入
    @Autowired
    public ApFollowBehaviorController(ApFollowBehaviorService apFollowBehaviorService) {
        super(apFollowBehaviorService);
        this.apFollowBehaviorService=apFollowBehaviorService;
    }

}

