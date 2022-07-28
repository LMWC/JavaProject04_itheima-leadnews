package com.itheima.core.knif4j;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/5/30 11:46
 * @description 标题
 * @package com.itheima.core.knif4j
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@EnableConfigurationProperties(MyKnife4jProperties.class)//交给spring管理
public class MyKnife4jAutoConfiguration {

    @Autowired
    private MyKnife4jProperties myKnife4jProperties;

    @Bean
    @ConditionalOnMissingBean(Docket.class)//干嘛：当 其他人没有配置docket的对象交给spring容器管理的时候。我这个方法就会执行
    public Docket defaultApi2() {
        HashSet<String> strings = new HashSet<>();
        strings.add("application/json");
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(myKnife4jProperties.getTitle())
                        .description(myKnife4jProperties.getDescription())
                        .termsOfServiceUrl(myKnife4jProperties.getServiceUrl())
                        .contact(myKnife4jProperties.getContact())
                        .version(myKnife4jProperties.getVersion())
                        .build())
                .produces(strings)
                //分组名称
                .select()

                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(myKnife4jProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
