package com.itheima.admin.controller;


import com.itheima.admin.pojo.AdStrategyGroup;
import com.itheima.admin.service.AdStrategyGroupService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* 推荐策略分组信息表 控制器</p>
* @author ljh
* @since 2022-07-22
*/
@Api(value="推荐策略分组信息表",tags = "AdStrategyGroupController")
@RestController
@RequestMapping("/adStrategyGroup")
public class AdStrategyGroupController extends AbstractCoreController<AdStrategyGroup> {

    private AdStrategyGroupService adStrategyGroupService;

    //注入
    @Autowired
    public AdStrategyGroupController(AdStrategyGroupService adStrategyGroupService) {
        super(adStrategyGroupService);
        this.adStrategyGroupService=adStrategyGroupService;
    }

}

