package com.itheima.user.pojo;

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
 * APP用户频道信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_channel")
@ApiModel(value="ApUserChannel", description="APP用户频道信息表")
public class ApUserChannel implements Serializable {


    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("channel_id")
    private Integer channelId;

    @ApiModelProperty(value = "文章ID")
    @TableField("user_id")
    private Integer userId;

    @TableField("name")
    private String name;

    @ApiModelProperty(value = "频道排序")
    @TableField("ord")
    private Integer ord;

    @ApiModelProperty(value = "登录时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
