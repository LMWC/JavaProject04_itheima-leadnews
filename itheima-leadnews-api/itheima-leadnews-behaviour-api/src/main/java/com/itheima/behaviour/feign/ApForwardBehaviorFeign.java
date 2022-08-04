package com.itheima.behaviour.feign;

import com.itheima.behaviour.pojo.ApForwardBehavior;
import com.itheima.core.feign.CoreFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
* @author ljh
* @version 1.0
* @date 2021/4/6 15:49
* @description 标题
* @package
*/
@FeignClient(name="leadnews-behaviour",path = "/apForwardBehavior",contextId = "apForwardBehavior")
public interface ApForwardBehaviorFeign  extends  CoreFeign<ApForwardBehavior> {

}