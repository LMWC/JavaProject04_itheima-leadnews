package com.itheima;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.itheima.admin.mapper.AdSensitiveMapper;
import com.itheima.common.util.SensitiveWordUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
* @author ljh
* @version 1.0
* @date 2021/5/2 11:36
* @description 标题
* @package
*/
@SpringBootApplication
@MapperScan(basePackages = "com.itheima.admin.mapper")//扫描mapper接口所在的包即可
@EnableDiscoveryClient//启用注册与发现
@EnableFeignClients(basePackages = "com.itheima.*.feign")
public class AdminApplication {

//    @Autowired
//    private AdSensitiveMapper adSensitiveMapper;


    public static void main(String[] args) {
        //返回值就是spring IOC容器本身
        ConfigurableApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        AdSensitiveMapper adSensitiveMapper = context.getBean(AdSensitiveMapper.class);
        List<String> adSensitives = adSensitiveMapper.selectAdSensitive();
        SensitiveWordUtil.initMap(adSensitives);

    }
    //添加一个mybatis-plus的插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}