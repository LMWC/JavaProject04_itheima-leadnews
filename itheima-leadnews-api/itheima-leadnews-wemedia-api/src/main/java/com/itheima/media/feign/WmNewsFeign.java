package com.itheima.media.feign;

import com.itheima.core.feign.CoreFeign;
import com.itheima.media.pojo.WmNews;
import com.itheima.media.pojo.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="leadnews-wemedia",path = "/wmNews",contextId = "wmNews")
public interface WmNewsFeign extends CoreFeign<WmNews> {

    //根据状态来查询自媒体文章的列表

    @GetMapping("/list/{status}")
    public List<WmNews> findByStatus(@PathVariable(name = "status") Integer status) ;

}
