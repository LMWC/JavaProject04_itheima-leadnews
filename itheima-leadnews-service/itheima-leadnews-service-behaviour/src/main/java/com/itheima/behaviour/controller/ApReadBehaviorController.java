package com.itheima.behaviour.controller;


import com.itheima.behaviour.dto.ReadBehaviorDto;
import com.itheima.behaviour.pojo.ApReadBehavior;
import com.itheima.behaviour.service.ApReadBehaviorService;
import com.itheima.common.pojo.Result;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP阅读行为表 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP阅读行为表",tags = "ApReadBehaviorController")
@RestController
@RequestMapping("/apReadBehavior")
public class ApReadBehaviorController extends AbstractCoreController<ApReadBehavior> {

    private ApReadBehaviorService apReadBehaviorService;

    //注入
    @Autowired
    public ApReadBehaviorController(ApReadBehaviorService apReadBehaviorService) {
        super(apReadBehaviorService);
        this.apReadBehaviorService=apReadBehaviorService;
    }

    //阅读
    @PostMapping("/read")
    public Result read(@RequestBody ReadBehaviorDto readBehaviorDto){
        apReadBehaviorService.read(readBehaviorDto);
        return Result.ok();
    }


}

