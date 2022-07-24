package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 标签信息表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_label")
@ApiModel(value="AdLabel", description="标签信息表")
public class AdLabel implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "频道名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "0系统增加	            1人工增加")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
