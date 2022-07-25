package com.itheima.gatewaymedia.filter;

//全局过滤器：所有的请求都需要经过这个过滤器
//可以自定义业务逻辑进行处理 在转发请求之前先处理


//1.创建一个类实现接口globalFilter ordered

//2.类交给spring容器管理

//3.重写方法 实现自定义的业务逻辑


import com.alibaba.nacos.api.utils.StringUtils;
import com.itheima.common.constants.SystemConstants;
import com.itheima.common.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {


    //校验 当前用户发送过来的请求是否有权限去访问? 用户需要在请求头中携带之前生成颁发的令牌过来
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //1.获取请求和响应对象

        ServerHttpRequest request = exchange.getRequest();

        ServerHttpResponse response = exchange.getResponse();

        //2.判断当前的请求路径 如果是去自媒体用户登录 则放行
        String path = request.getURI().getPath();
        if(path.startsWith("/media/wmUser/login")){
            return chain.filter(exchange);
        }
        //3.获取请求头的内容 判断是否为null 如果null 则拦截 返回（401）
        String token = request.getHeaders().getFirst("token");
        if(StringUtils.isEmpty(token)){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();//响应回去
        }


        //4.获取令牌信息进行校验 校验不成功 拦截 返回(401)
        Integer code = AppJwtUtil.verifyToken(token);
        if(code!= SystemConstants.JWT_OK){
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();//响应回去
        }

        //在路由请求之前 将当前令牌中的用户的ID 解析出来 并[添加到请求头中 传递给下游]

        Claims claimsBody = AppJwtUtil.getClaimsBody(token);
        Object id = claimsBody.get("id");
        String id1 = claimsBody.getId();
        System.out.println(id);
        System.out.println(id1);

        request.mutate().header(SystemConstants.USER_HEADER_NAME,id.toString());
        //5.放行
        return chain.filter(exchange);
    }

    //执行过滤器的顺序 值越低 执行优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}
