package com.itheima.article.controller;


import com.itheima.article.pojo.ApArticleContent;
import com.itheima.article.service.ApArticleContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* APP已发布文章内容表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="APP已发布文章内容表",tags = "ApArticleContentController")
@RestController
@RequestMapping("/apArticleContent")
public class ApArticleContentController extends AbstractCoreController<ApArticleContent> {

    private ApArticleContentService apArticleContentService;

    //注入
    @Autowired
    public ApArticleContentController(ApArticleContentService apArticleContentService) {
        super(apArticleContentService);
        this.apArticleContentService=apArticleContentService;
    }

}

