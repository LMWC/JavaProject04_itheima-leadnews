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
 * 管理员设备信息表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_user_equipment")
@ApiModel(value="AdUserEquipment", description="管理员设备信息表")
public class AdUserEquipment implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "0PC	            1ANDROID	            2IOS	            3PAD	            9 其他")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "设备版本")
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "设备系统")
    @TableField("sys")
    private String sys;

    @ApiModelProperty(value = "设备唯一号，MD5加密")
    @TableField("no")
    private String no;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
