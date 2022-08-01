package com.itheima.gatewayapp.filter;

//全局过滤器：所有的请求都需要经过这个过滤器
//可以自定义业务逻辑进行处理 在转发请求之前先处理


//1.创建一个类实现接口globalFilter ordered

//2.类交给spring容器管理

//3.重写方法 实现自定义的业务逻辑


import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.utils.StringUtils;
import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.au.JwtUtil;
import com.itheima.common.util.au.TokenRole;
import com.itheima.common.util.au.UserTokenInfoExp;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URLEncoder;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {


    //校验 当前用户发送过来的请求是否有权限去访问? 用户需要在请求头中携带之前生成颁发的令牌过来
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取请求和响应对象

        ServerHttpRequest request = exchange.getRequest();

        ServerHttpResponse response = exchange.getResponse();

        //2.判断当前的请求路径 如果是去自媒体用户登录 则放行 校验 增加一个
        String path = request.getURI().getPath();
        if(path.startsWith("/user/app/login") || path.startsWith("/user/app/refreshToken") ){
            return chain.filter(exchange);
        }
        //3.获取请求头的内容 判断是否为null 如果null 则拦截 返回（401）   这个令牌 是短令牌（accesstoken）
        String token = request.getHeaders().getFirst("token");
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();//响应回去
        }


        //4.获取令牌信息进行解析
        try {
            //4.1 解析成功 令牌有效
            UserTokenInfoExp userTokenInfoExp = JwtUtil.parseJwtUserToken(token);
            //4.2 判断角色是否正确（网关是APP网关 来的令牌必须是APP或者匿名用户）
            if(JwtUtil.isValidRole(userTokenInfoExp, TokenRole.ROLE_MEDIA) || JwtUtil.isValidRole(userTokenInfoExp, TokenRole.ROLE_ADMIN)){
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();//响应回去
            }
            //4.3 判断是否过期  如果过期 返回403
            if (JwtUtil.isExpire(userTokenInfoExp)) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();//响应回去
            }

            //4.4 成功了 将用户的令牌解析之后的所有的数据 放到请求头中  传递给下游  放行  默认是 iso8859-1
            String encode = URLEncoder.encode(JSON.toJSONString(userTokenInfoExp), "utf-8");
            request.mutate().header(SystemConstants.USER_HEADER_NAME,encode);

        } catch (Exception e) {
            e.printStackTrace();
            //4.2 解析失败 令牌有问题 直接报错
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();//响应回去
        }
        return chain.filter(exchange);
    }

    //执行过滤器的顺序 值越低 执行优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}
