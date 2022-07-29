package com.itheima.dfs.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="leadnews-dfs",path = "/dfs")
public interface DfsFeign {

    //方法（？？？？？？？？？？？？？？？我要干嘛）
    @PostMapping("/downLoad")
    public List<byte[]> downLoad(@RequestBody List<String> urls);
}
