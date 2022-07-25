package com.itheima.dfs.strategy;

import com.itheima.dfs.pojo.DFSType;
import com.itheima.dfs.service.AbstractDfsTemplate;
import com.itheima.dfs.service.IFileService;
import com.itheima.dfs.service.impl.OssFileTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

//spring容器一旦启动了 就马上初始化 将dfsServices 赋值  key: 某一个枚举  value 某一个枚举对应的service
//从容器中获取到这两个类型 分别放入到 map中可以
@Component
public class FileServiceStrategyContext implements ApplicationContextAware {

    //key  就是某一个类型 value 就是接口对应的具体子类对象  key? value?
    private static final Map<DFSType, IFileService> dfsServices = new EnumMap<DFSType, IFileService>(DFSType.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        // applicationContext  他就是IOC容器

        //抽象类的所有的子类对象 从容器中获取所有的子类对象 key: beanName  value  bean对象
        Map<String, AbstractDfsTemplate> types = applicationContext.getBeansOfType(AbstractDfsTemplate.class);

        //循环抽象类的子类
        for (AbstractDfsTemplate value : types.values()) {
            DFSType support = value.support();
            dfsServices.put(support, value);
        }
    }

    /**
     * 提供方法获取具体实现类
     * @param dfsType  0/1  枚举作为key  枚举对应的实例模板 作为value
     * @return
     */
    public IFileService getIFleService(DFSType dfsType) {
        return dfsServices.get(dfsType);
    }

}