package com.itheima.common.util;

import com.itheima.common.constants.SystemConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestContextUtil {
    /**
     * 获取登录的用户的ID 可以是自媒体账号 也可以是 平台账号 也可以是app账号
     * @return
     */
    public static String getUserInfo(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //获取路由转发的头信息
        String headerValue = request.getHeader(SystemConstants.USER_HEADER_NAME);

        //加很多其他的业务逻辑在里面
        return headerValue;
    }
}