package com.itheima.media.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

@Configuration
@EnableSwagger2//启用swagger
@EnableKnife4j//启用Knife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

   @Bean
   public Docket buildDocket() {
      HashSet<String> strings = new HashSet<>();
      strings.add("application/json");
      Docket docket=new Docket(DocumentationType.SWAGGER_2)
              .apiInfo(buildApiInfo())
              //设置返回数据类型
              .produces(strings)
              //分组名称
              //.groupName("1.0")
              .select()
              //这里指定Controller扫描包路径
              .apis(RequestHandlerSelectors.basePackage("com.itheima.media.controller"))
              //**
              .paths(PathSelectors.any())
              .build();
      return docket;
   }
   private ApiInfo buildApiInfo() {
      Contact contact = new Contact("steven","","");
      return new ApiInfoBuilder()
              .title("steven管理API文档")
              .description("steven管理API文档")
              .contact(contact)
              .version("1.0.0").build();
   }
}