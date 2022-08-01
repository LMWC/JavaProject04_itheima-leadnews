package com.itheima.article.controller;


import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.pojo.ApArticle;
import com.itheima.article.service.ApArticleService;
import com.itheima.common.pojo.PageInfo;
import com.itheima.common.pojo.PageRequestDto;
import com.itheima.common.pojo.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import com.itheima.core.controller.AbstractCoreController;

import java.io.Serializable;

/**
* <p>
* 文章信息表，存储已发布的文章 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="文章信息表，存储已发布的文章",tags = "ApArticleController")
@RestController
@RequestMapping("/apArticle")
@CrossOrigin//可以了 直接支持跨域
public class ApArticleController extends AbstractCoreController<ApArticle> {

    private ApArticleService apArticleService;

    //注入
    @Autowired
    public ApArticleController(ApArticleService apArticleService) {
        super(apArticleService);
        this.apArticleService=apArticleService;
    }

    @PostMapping("/articleInfo/save")
    public Result<ApArticle> save(@RequestBody ArticleInfoDto dto){
        ApArticle apArticle = apArticleService.saveArticle(dto);
        return Result.ok(apArticle);
    }


    @PostMapping("/searchOrder")
    public Result<PageInfo<ApArticle>> searchOrder(@RequestBody PageRequestDto<ApArticle> pageRequestDto){
        PageInfo<ApArticle> pageInfo = apArticleService.pageByOrder(pageRequestDto);
        return Result.ok(pageInfo);

    }

    //restcontroller  responsebody 就是将一个对象转成JSON
    // jackson 组件  自动将对象转成JSON格式 响应的时候设置content-type:application/json;
    // 序列化  将一个对象 转成JSON 就是序列化
    // 反序列化 将一个JSON 转成对象 就是反序列化
    @GetMapping("/detail/{articleId}")
    public Result<ArticleInfoDto> detailByArticleId(@PathVariable(name="articleId") Long articleId){
        ArticleInfoDto articleInfoDto =  apArticleService.detailByArticleId(articleId);
        return Result.ok(articleInfoDto);
    }

}

