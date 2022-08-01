package com.itheima.user.controller;

import com.itheima.common.exception.LeadNewsException;
import com.itheima.common.pojo.Result;
import com.itheima.common.util.au.JwtUtil;
import com.itheima.common.util.au.TokenJsonVo;
import com.itheima.common.util.au.UserTokenInfo;
import com.itheima.common.util.au.UserTokenInfoExp;
import com.itheima.media.vo.LoginMediaVo;
import com.itheima.user.dto.LoginDto;
import com.itheima.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/app")
public class LoginController {
    @Autowired
    private ApUserService apUserService;

    @PostMapping("/login")
    public Result<TokenJsonVo> login(@RequestBody LoginDto loginDto) throws LeadNewsException {
        //1.不登录先看看 直接生成令牌 固定是匿名用户
        if(loginDto.getFlag()==0){
            TokenJsonVo token = JwtUtil.createToken(UserTokenInfo.getAnonymous());
            return Result.ok(token);
        }else{
            //2.要登录 真正的用户登录成功才能生成令牌

            TokenJsonVo info = apUserService.login(loginDto);
            return Result.ok(info);
        }


    }

    //刷新令牌的接口
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
            return Result.errorMessage("令牌错误");
        }
        //重新生成
        TokenJsonVo token = JwtUtil.createToken(userTokenInfoExp);

        return Result.ok(token);
    }





}
