package com.itheima.common.exception;

import com.itheima.common.pojo.StatusCode;

public class LeadNewsException extends Exception{

    //错误的状态码
    private Integer code;

    //错误信息
    private String message;

    public LeadNewsException(){

    }
    public LeadNewsException(Integer code, String message){
        this.code=code;
        this.message=message;
    }

    public LeadNewsException(String message){
        //自定义的异常的状态
        this.code= StatusCode.CUSTOM_FAILURE.code();
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}