package com.itheima.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ljh
 * @version 1.0
 * @date 2021/2/20 11:38
 * @description 标题
 * @package com.itheima.common.pojo
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageRequestDto<T> implements Serializable {
    //当前页码
    private Long page = 1L;
    //每页显示的行
    private Long size = 10L;
    //请求体实体对象
    private T body;
}
