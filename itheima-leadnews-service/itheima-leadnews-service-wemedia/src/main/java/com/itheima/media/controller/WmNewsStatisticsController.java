package com.itheima.media.controller;


import com.itheima.media.pojo.WmNewsStatistics;
import com.itheima.media.service.WmNewsStatisticsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;
import com.itheima.core.controller.AbstractCoreController;

/**
* <p>
* 自媒体图文数据统计表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体图文数据统计表",tags = "WmNewsStatisticsController")
@RestController
@RequestMapping("/wmNewsStatistics")
public class WmNewsStatisticsController extends AbstractCoreController<WmNewsStatistics> {

    private WmNewsStatisticsService wmNewsStatisticsService;

    //注入
    @Autowired
    public WmNewsStatisticsController(WmNewsStatisticsService wmNewsStatisticsService) {
        super(wmNewsStatisticsService);
        this.wmNewsStatisticsService=wmNewsStatisticsService;
    }

}

