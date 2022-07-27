package com.itheima.common.util;

import com.alibaba.fastjson.JSON;
import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.au.TokenRole;
import com.itheima.common.util.au.UserTokenInfoExp;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class RequestContextUtil {
    /**
     * 获取访问令牌信息解析之后的信息
     *
     * @return
     */
    public static UserTokenInfoExp getRequestUserTokenInfo() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String json = null;
        try {
            //解码
            json = URLDecoder.decode(request.getHeader(SystemConstants.USER_HEADER_NAME), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        UserTokenInfoExp userTokenInfoExp = JSON.parseObject(json, UserTokenInfoExp.class);

        return userTokenInfoExp;
    }


    //判断是否为匿名用户
    public static boolean isAnonymous() {
        return TokenRole.ROLE_ANONYMOUS == getRequestUserTokenInfo().getRole();
    }

    //获取用户ID值
    public static Integer getUserId() {
        return getRequestUserTokenInfo().getUserId().intValue();
    }


}