package com.itheima.article.controller;


import com.itheima.article.pojo.ApArticle;
import com.itheima.article.service.ApArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 文章信息表，存储已发布的文章 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="文章信息表，存储已发布的文章",tags = "ApArticleController")
@RestController
@RequestMapping("/apArticle")
public class ApArticleController extends AbstractCoreController<ApArticle> {

    private ApArticleService apArticleService;

    //注入
    @Autowired
    public ApArticleController(ApArticleService apArticleService) {
        super(apArticleService);
        this.apArticleService=apArticleService;
    }

}

