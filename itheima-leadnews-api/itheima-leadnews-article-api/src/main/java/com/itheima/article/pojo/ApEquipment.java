package com.itheima.article.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * APP设备信息表
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_equipment")
@ApiModel(value="ApEquipment", description="APP设备信息表")
public class ApEquipment implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
