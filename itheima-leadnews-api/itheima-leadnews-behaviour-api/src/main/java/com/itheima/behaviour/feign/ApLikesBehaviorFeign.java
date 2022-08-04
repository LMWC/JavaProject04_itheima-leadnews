package com.itheima.behaviour.feign;

import com.itheima.behaviour.pojo.ApLikesBehavior;
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
@FeignClient(name="leadnews-behaviour",path = "/apLikesBehavior",contextId = "apLikesBehavior")
public interface ApLikesBehaviorFeign  extends  CoreFeign<ApLikesBehavior> {

    //根据文章ID 和 实体的ID 获取点赞记录
    @GetMapping("/getLikesBehavior")
    public ApLikesBehavior getLikesBehavior(@RequestParam(name="articleId") Long articleId,
                                            @RequestParam(name="entryId")Integer entryId);


}