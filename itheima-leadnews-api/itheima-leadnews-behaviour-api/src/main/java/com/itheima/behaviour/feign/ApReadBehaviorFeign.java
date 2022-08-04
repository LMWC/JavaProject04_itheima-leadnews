package com.itheima.behaviour.feign;

import com.itheima.behaviour.pojo.ApReadBehavior;
import com.itheima.core.feign.CoreFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
* @author ljh
* @version 1.0
* @date 2021/4/6 15:49
* @description 标题
* @package
*/
@FeignClient(name="leadnews-behaviour",path = "/apReadBehavior",contextId = "apReadBehavior")
public interface ApReadBehaviorFeign  extends  CoreFeign<ApReadBehavior> {

}