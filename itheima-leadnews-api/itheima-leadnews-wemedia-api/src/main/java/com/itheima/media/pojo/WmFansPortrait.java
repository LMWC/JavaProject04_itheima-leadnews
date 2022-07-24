package com.itheima.media.pojo;

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
 * 自媒体粉丝画像信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_fans_portrait")
@ApiModel(value="WmFansPortrait", description="自媒体粉丝画像信息表")
public class WmFansPortrait implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "账号ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "画像项目")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "资源名称")
    @TableField("value")
    private String value;

    @TableField("burst")
    private String burst;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
