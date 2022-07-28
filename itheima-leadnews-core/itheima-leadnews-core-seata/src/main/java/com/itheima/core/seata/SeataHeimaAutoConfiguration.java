package com.itheima.core.seata;

import io.seata.rm.datasource.DataSourceProxy;

import io.seata.spring.boot.autoconfigure.SeataAutoConfiguration;
import io.seata.spring.boot.autoconfigure.properties.SeataProperties;
import io.seata.spring.boot.autoconfigure.properties.client.ServiceProperties;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;


/**
 * --加载文件properties
 * https://www.cnblogs.com/huanzi-qch/p/11122107.html
 * --加载yaml
 * https://blog.csdn.net/baidu_28523317/article/details/108701391
 *
 * --顺序
 * https://blog.csdn.net/f641385712/article/details/105596178
 *
 * --可能存在的问题
 * https://segmentfault.com/q/1010000040364236
 */
@Configuration
@ConditionalOnClass(DataSourceProxy.class)
public class SeataHeimaAutoConfiguration {

    //仅仅用作测试
    @Bean
    public Map<String,String> seatax(SeataProperties seataProperties,
                                     ServiceProperties serviceProperties){
        String txServiceGroup = seataProperties.getTxServiceGroup();
        System.out.println(txServiceGroup);
        System.out.println(serviceProperties.getGrouplist());
        return new HashMap<>();
    }
}
