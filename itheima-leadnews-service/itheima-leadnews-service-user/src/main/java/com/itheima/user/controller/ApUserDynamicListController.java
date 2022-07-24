package com.itheima.user.controller;


import com.itheima.user.pojo.ApUserDynamicList;
import com.itheima.user.service.ApUserDynamicListService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户动态列表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户动态列表",tags = "ApUserDynamicListController")
@RestController
@RequestMapping("/apUserDynamicList")
public class ApUserDynamicListController extends AbstractCoreController<ApUserDynamicList> {

    private ApUserDynamicListService apUserDynamicListService;

    //注入
    @Autowired
    public ApUserDynamicListController(ApUserDynamicListService apUserDynamicListService) {
        super(apUserDynamicListService);
        this.apUserDynamicListService=apUserDynamicListService;
    }

}

