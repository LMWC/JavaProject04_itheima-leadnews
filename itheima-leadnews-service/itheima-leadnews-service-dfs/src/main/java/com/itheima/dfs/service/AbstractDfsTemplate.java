package com.itheima.dfs.service;

import com.itheima.dfs.pojo.DFSType;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractDfsTemplate implements IFileService, InitializingBean {


    //1.创建连接对象
    //2.设置用户和密码
    //3.创建客户端对象
    //4.特殊的步骤（需要子类告诉父类）


    /**
     * 定义支持的类型 必须设置值  在父类去根据子类的具体的行为实现不同的业务逻辑
     *
     * 检查 子类是否支持我规定的数据类型（枚举）
     *
     * @return
     */
    public abstract DFSType support();

    //写一个方法  由子类来提供

    @Override
    public void afterPropertiesSet() throws Exception {

        //调用上边写的步骤 初始化



        //检查
        DFSType[] values = DFSType.values();

        boolean flag = false;
        for (DFSType value : values) {
            DFSType support = support();
            if(support==value){
                flag=true;
                break;
            }
        }
        if(!flag){
            throw new java.lang.RuntimeException("不支持的dfs类型");
        }
    }
}