package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdRecommendStrategy;
import com.itheima.admin.service.AdRecommendStrategyService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 推荐策略信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="推荐策略信息表",tags = "AdRecommendStrategyController")
@RestController
@RequestMapping("/adRecommendStrategy")
public class AdRecommendStrategyController extends AbstractCoreController<AdRecommendStrategy> {

    private AdRecommendStrategyService adRecommendStrategyService;

    //注入
    @Autowired
    public AdRecommendStrategyController(AdRecommendStrategyService adRecommendStrategyService) {
        super(adRecommendStrategyService);
        this.adRecommendStrategyService=adRecommendStrategyService;
    }

}

