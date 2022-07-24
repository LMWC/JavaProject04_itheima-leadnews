package com.itheima.article.controller;


import com.itheima.article.pojo.ApDynamic;
import com.itheima.article.service.ApDynamicService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户动态信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户动态信息表",tags = "ApDynamicController")
@RestController
@RequestMapping("/apDynamic")
public class ApDynamicController extends AbstractCoreController<ApDynamic> {

    private ApDynamicService apDynamicService;

    //注入
    @Autowired
    public ApDynamicController(ApDynamicService apDynamicService) {
        super(apDynamicService);
        this.apDynamicService=apDynamicService;
    }

}

