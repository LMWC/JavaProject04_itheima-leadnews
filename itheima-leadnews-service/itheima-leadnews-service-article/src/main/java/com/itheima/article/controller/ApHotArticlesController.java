package com.itheima.article.controller;


import com.itheima.article.pojo.ApHotArticles;
import com.itheima.article.service.ApHotArticlesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 热文章表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="热文章表",tags = "ApHotArticlesController")
@RestController
@RequestMapping("/apHotArticles")
public class ApHotArticlesController extends AbstractCoreController<ApHotArticles> {

    private ApHotArticlesService apHotArticlesService;

    //注入
    @Autowired
    public ApHotArticlesController(ApHotArticlesService apHotArticlesService) {
        super(apHotArticlesService);
        this.apHotArticlesService=apHotArticlesService;
    }

}

