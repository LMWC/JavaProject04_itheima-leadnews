package com.itheima.behaviour.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.behaviour.pojo.ApUnlikesBehavior;
import com.itheima.behaviour.service.ApUnlikesBehaviorService;
import com.itheima.core.controller.AbstractCoreController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
* APP不喜欢行为表 控制器</p>
* @author ljh
* @since 2022-08-03
*/
@Api(value="APP不喜欢行为表",tags = "ApUnlikesBehaviorController")
@RestController
@RequestMapping("/apUnlikesBehavior")
public class ApUnlikesBehaviorController extends AbstractCoreController<ApUnlikesBehavior> {

    private ApUnlikesBehaviorService apUnlikesBehaviorService;

    //注入
    @Autowired
    public ApUnlikesBehaviorController(ApUnlikesBehaviorService apUnlikesBehaviorService) {
        super(apUnlikesBehaviorService);
        this.apUnlikesBehaviorService=apUnlikesBehaviorService;
    }

    @GetMapping("/getUnlikesBehavior")
    public ApUnlikesBehavior getUnlikesBehavior(@RequestParam(name="articleId") Long articleId,
                                                @RequestParam(name="entryId")Integer entryId){
        QueryWrapper<ApUnlikesBehavior> querywraper = new QueryWrapper<ApUnlikesBehavior>();
        querywraper.eq("entry_id",entryId);
        querywraper.eq("article_id",articleId);
        ApUnlikesBehavior apUnlikesBehavior = apUnlikesBehaviorService.getOne(querywraper);
        return apUnlikesBehavior;
    }

}

