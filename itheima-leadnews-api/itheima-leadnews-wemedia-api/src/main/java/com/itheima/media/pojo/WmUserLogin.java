package com.itheima.media.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 自媒体用户登录行为信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_user_login")
@ApiModel(value="WmUserLogin", description="自媒体用户登录行为信息表")
public class WmUserLogin implements Serializable {


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

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private BigDecimal latitude;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
