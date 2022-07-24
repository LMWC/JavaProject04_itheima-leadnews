package com.itheima.admin.pojo;

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
 * 访问数据统计表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_vistor_statistics")
@ApiModel(value="AdVistorStatistics", description="访问数据统计表")
public class AdVistorStatistics implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "日活")
    @TableField("activity")
    private Integer activity;

    @ApiModelProperty(value = "访问量")
    @TableField("vistor")
    private Integer vistor;

    @ApiModelProperty(value = "IP量")
    @TableField("ip")
    private Integer ip;

    @ApiModelProperty(value = "注册量")
    @TableField("register")
    private Integer register;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
