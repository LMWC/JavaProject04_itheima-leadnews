package com.itheima;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.itheima.search.mapper.ApAssociateWordsMapper;
import com.itheima.search.pojo.ApAssociateWords;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.trie4j.patricia.PatriciaTrie;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@MapperScan(basePackages = "com.itheima.search.mapper")
@EnableScheduling
@EnableAsync//开启多线程（线程池）
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class,args);

    }
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }


    @Autowired
    private ApAssociateWordsMapper apAssociateWordsMapper;

    @Autowired
    private PatriciaTrie patriciaTrie;

    //这个方法在系统启动的时候调用一次就可以了
    @PostConstruct//注解的作用就是当 该SearchApplication对象 被spring容器创建出来的时候会自动调用修饰的方法 调用一次
    public void initMethod(){

        List<ApAssociateWords> apAssociateWords = apAssociateWordsMapper.selectList(null);
        for (ApAssociateWords apAssociateWord : apAssociateWords) {
            patriciaTrie.insert(apAssociateWord.getAssociateWords());
        }
    }

    @Bean
    public PatriciaTrie patriciaTrie(){
        return new PatriciaTrie();
    }


    //自定义线程池的配置
    @Bean(name = "taskExecutor")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(20);
        // 设置最大线程数
        executor.setMaxPoolSize(40);
        // 设置队列容量
        executor.setQueueCapacity(20);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(60);
        // 设置默认线程名称
        executor.setThreadNamePrefix("taskExecutor123-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);

        executor.initialize();
        return executor;
    }
}
