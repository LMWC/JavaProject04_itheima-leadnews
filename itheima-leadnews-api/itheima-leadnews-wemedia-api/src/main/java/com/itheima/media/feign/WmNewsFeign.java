package com.itheima.media.feign;

import com.itheima.core.feign.CoreFeign;
import com.itheima.media.pojo.WmNews;
import com.itheima.media.pojo.WmUser;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="leadnews-wemedia",path = "/wmNews",contextId = "wmNews")
public interface WmNewsFeign extends CoreFeign<WmNews> {
}
