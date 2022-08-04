package com.itheima.user.feign;

import com.itheima.core.feign.CoreFeign;
import com.itheima.user.pojo.ApUserFollow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="leadnews-user",path = "/apUserFollow",contextId ="apUserFollow" )
public interface ApUserFollowFeign extends CoreFeign<ApUserFollow> {

    //根据userid和folow_id查询记录 ?id=1&name=a&k=b
    @GetMapping("/getApUserFollow")
    ApUserFollow getApUserFollow(@RequestParam(name="followId")Integer followId,
                                 @RequestParam(name="userId")Integer userId);
}
