package com.itheima.common.pojo;

/**
 * 枚举类状态码
 *
 * @author ljh
 * @version 1.0
 * @date 2021/2/19 10:49
 * @description 标题
 * @package com.itheima.common.pojo
 */
public enum StatusCode {
    //区分与http状态码
    SUCCESS(20000, "操作成功"),
    PARAM_ERROR(40000, "参数异常"),
    NOT_FOUND(40004, "资源不存在"),
    FAILURE(50000, "系统异常"),
    CUSTOM_FAILURE(50001, "自定义异常错误"),
    NEED_LOGIN(50002,"需要登录");
    private final Integer code;

    private final String message;

    StatusCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    //获取状态码
    public Integer code() {
        return code;
    }

    //获取信息
    public String message() {
        return message;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }
}
