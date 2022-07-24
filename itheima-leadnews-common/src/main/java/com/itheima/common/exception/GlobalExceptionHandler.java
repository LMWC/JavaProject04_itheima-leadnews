package com.itheima.common.exception;

import com.itheima.common.pojo.Result;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//日志框架
@RestControllerAdvice//是一个全局异常处理器 标识

public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    //1.处理系统异常的
    @ExceptionHandler(value=Exception.class)
    public Result handlerException(Exception e){
        //异常捕获 放到日志文件中 先堆栈
//        e.printStackTrace();
        logger.error("系统的异常信息",e);
        return Result.error();
    }

    //2.处理业务异常
    @ExceptionHandler(value=LeadNewsException.class)
    public Result handlerLeadNewsException(LeadNewsException e){
//        e.printStackTrace();
        logger.error("系统的异常信息",e);
        return Result.errorMessage(e.getMessage(),e.getCode(),null);
    }
}
