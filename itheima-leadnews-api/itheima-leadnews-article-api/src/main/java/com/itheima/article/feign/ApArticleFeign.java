package com.itheima.article.feign;

import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.pojo.ApArticle;
import com.itheima.common.pojo.Result;
import com.itheima.core.feign.CoreFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="leadnews-article",path = "/apArticle",contextId ="apArticle" )
public interface ApArticleFeign extends CoreFeign<ApArticle> {
    //声明一个新的方法
    @PostMapping("/articleInfo/save")
    public Result<ApArticle> save(@RequestBody ArticleInfoDto dto);
}
