package com.itheima.media.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import com.itheima.common.util.au.JwtUtil;
import com.itheima.common.util.au.TokenJsonVo;
import com.itheima.common.util.au.UserTokenInfoExp;
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

   /* @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody LoginMediaVo loginMediaVo) throws LeadNewsException {
        Map<String,Object> info = wmUserService.login(loginMediaVo);
        return Result.ok(info);
    }*/
   @PostMapping("/login")
   public Result<TokenJsonVo> login(@RequestBody LoginMediaVo loginMediaVo) throws LeadNewsException {
       TokenJsonVo info = wmUserService.login(loginMediaVo);
       return Result.ok(info);
   }


   //刷新令牌 {“refreshToken”:“value”,"key1":"value1","key2":"value2"}

    @PostMapping("/refreshToken")
    public Result refreshToken(@RequestBody Map<String, String> map) {
        String refreshToken = map.get("refreshToken");

        UserTokenInfoExp userTokenInfoExp = null;
        try {
            userTokenInfoExp = JwtUtil.parseJwtUserToken(refreshToken);

            Long exp = userTokenInfoExp.getExp();
            long now = System.currentTimeMillis();
            long chazhi = exp - now;

            //续约的要求是： 必须在 访问令牌的过期时间点 到 刷新令牌的过期时间点 之间  防止 出现过久的令牌来恶意刷新令牌
            if(chazhi>(JwtUtil.TOKEN_TIME_OUT*1000)){
                return Result.errorMessage("令牌续约时间不在有效范围之内");
            }

        } catch (Exception e) {
            return Result.errorMessage("令牌错误");
        }
        if (JwtUtil.isExpire(userTokenInfoExp)) {
            return Result.errorMessage("刷新令牌（长令牌）也已经过期");
        }
        TokenJsonVo token = JwtUtil.createToken(userTokenInfoExp);
        return Result.ok(token);
    }





}

