package com.itheima.media.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import com.itheima.media.pojo.WmUser;
import com.itheima.media.service.WmUserService;
import com.itheima.media.vo.LoginMediaVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.Api;
import com.itheima.core.controller.AbstractCoreController;

import java.util.Map;

/**
* <p>
* 自媒体用户信息表 控制器</p>
* @author ljh
* @since 2021-12-22
*/
@Api(value="自媒体用户信息表",tags = "WmUserController")
@RestController
@RequestMapping("/wmUser")
public class WmUserController extends AbstractCoreController<WmUser>{

    @Autowired
    private WmUserService wmUserService;

    //注入
    @Autowired
    public WmUserController(WmUserService wmUserService) {
        super(wmUserService);

    }

    @GetMapping("/one/{apUserId}")
    public WmUser getByApUserId(@PathVariable(name="apUserId") Integer apUserId){
        //根据ap_userid 从wm_user表查询是否有记录
        QueryWrapper<WmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ap_user_id",apUserId);

        return wmUserService.getOne(queryWrapper);
    }

    //登录

    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody LoginMediaVo loginMediaVo) throws LeadNewsException {
        Map<String,Object> info = wmUserService.login(loginMediaVo);
        return Result.ok(info);
    }




}

