package com.itheima.article.feign;

import com.itheima.article.pojo.ApAuthor;
import com.itheima.common.pojo.Result;
import com.itheima.core.feign.CoreFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="leadnews-article",path = "/apAuthor")
public interface ApAuthorFeign extends CoreFeign<ApAuthor> {

    //创建作者
   /* @PostMapping
    public Result<ApAuthor> save(@RequestBody ApAuthor apAuthor);*/

    @GetMapping("/one/{apUserId}")
    public ApAuthor getByApUserId(@PathVariable(name="apUserId")Integer apUserId);
}
