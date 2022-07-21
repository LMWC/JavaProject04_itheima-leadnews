package com.itheima.common.pojo;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页相关的封装对象
 *
 * @author ljh
 * @version 1.0
 * @date 2021/2/19 11:10
 * @description 标题
 * @package com.itheima.common.pojo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    //当前页码
    private Long page;
    //每页显示行
    private Long size;
    //总记录数
    private Long total;
    //总页数
    private Long totalPages;
    //当前页记录
    private List<T> list;


}
