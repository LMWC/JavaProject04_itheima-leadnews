package com.itheima.user.controller;


import com.itheima.user.pojo.ApUserSearch;
import com.itheima.user.service.ApUserSearchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP用户搜索信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP用户搜索信息表",tags = "ApUserSearchController")
@RestController
@RequestMapping("/apUserSearch")
public class ApUserSearchController extends AbstractCoreController<ApUserSearch> {

    private ApUserSearchService apUserSearchService;

    //注入
    @Autowired
    public ApUserSearchController(ApUserSearchService apUserSearchService) {
        super(apUserSearchService);
        this.apUserSearchService=apUserSearchService;
    }

}

