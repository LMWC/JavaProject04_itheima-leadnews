package com.itheima;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
* @author ljh
* @version 1.0
* @date 2021/5/2 11:36
* @description 标题
* @package
*/
@SpringBootApplication
@MapperScan(basePackages = "com.itheima.user.mapper")//扫描mapper接口所在的包即可
@EnableDiscoveryClient//启用注册与发现
@EnableFeignClients(basePackages = "com.itheima.*.feign")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
    //添加一个mybatis-plus的插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}