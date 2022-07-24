package com.itheima.media.feign;

import com.itheima.common.pojo.Result;
import com.itheima.core.feign.CoreFeign;
import com.itheima.media.pojo.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * feign 是一个声明式rest客户端  用来模拟浏览器发送请求的组件
 *
 *
 * 1.声明请求发给谁？
 * 2.声明请求路径是什么？
 * 3.声明请求参数（请求体）是什么？
 * 4.声明返回的数据类型是什么？
 *
 */
@FeignClient(name="leadnews-wemedia",path = "/wmUser")//name=服务名 将来请求发送给他

public interface WmUserFeign extends CoreFeign<WmUser> {


    /*@PostMapping
    public Result<WmUser> save(@RequestBody WmUser wmUser);*/

    //声明 添加

    //声明根据ID 获取

    //声明 更加ID 更新

    //....

    /**
     * 根据apUserId获取
     * @param apUserId
     * @return
     */
    @GetMapping("/one/{apUserId}")
    public WmUser getByApUserId(@PathVariable(name="apUserId") Integer apUserId);

}
