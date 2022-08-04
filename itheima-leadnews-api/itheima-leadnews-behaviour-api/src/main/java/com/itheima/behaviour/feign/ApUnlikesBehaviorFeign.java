package com.itheima.behaviour.feign;

import com.itheima.behaviour.pojo.ApUnlikesBehavior;
import com.itheima.core.feign.CoreFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author ljh
* @version 1.0
* @date 2021/4/6 15:49
* @description 标题
* @package
*/
@FeignClient(name="leadnews-behaviour",path = "/apUnlikesBehavior",contextId = "apUnlikesBehavior")
public interface ApUnlikesBehaviorFeign  extends  CoreFeign<ApUnlikesBehavior> {
    @GetMapping("/getUnlikesBehavior")
    public ApUnlikesBehavior getUnlikesBehavior(@RequestParam(name="articleId") Long articleId,
                                                @RequestParam(name="entryId")Integer entryId);
}