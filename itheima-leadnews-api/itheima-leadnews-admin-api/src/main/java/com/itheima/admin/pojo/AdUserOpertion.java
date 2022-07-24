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
 * 管理员操作行为信息表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_user_opertion")
@ApiModel(value="AdUserOpertion", description="管理员操作行为信息表")
public class AdUserOpertion implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "登录设备ID")
    @TableField("equipment_id")
    private Integer equipmentId;

    @ApiModelProperty(value = "登录IP")
    @TableField("ip")
    private String ip;

    @ApiModelProperty(value = "登录地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "操作类型")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "操作描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
