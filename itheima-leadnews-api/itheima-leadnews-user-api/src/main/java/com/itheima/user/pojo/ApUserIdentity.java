package com.itheima.user.pojo;

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
 * APP身份认证信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_identity")
@ApiModel(value="ApUserIdentity", description="APP身份认证信息表")
public class ApUserIdentity implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账号ID")
    @TableField("user_id")
    private Integer userId;

    @TableField("name")
    private String name;

    @ApiModelProperty(value = "资源名称")
    @TableField("idno")
    private String idno;

    @ApiModelProperty(value = "正面照片")
    @TableField("font_image")
    private String fontImage;

    @ApiModelProperty(value = "背面照片")
    @TableField("back_image")
    private String backImage;

    @ApiModelProperty(value = "手持照片")
    @TableField("hold_image")
    private String holdImage;

    @ApiModelProperty(value = "行业")
    @TableField("industry")
    private String industry;

    @ApiModelProperty(value = "状态	            0 创建中	            1 待审核	            2 审核失败	            9 审核通过")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "拒绝原因")
    @TableField("reason")
    private String reason;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "提交时间")
    @TableField("submited_time")
    private LocalDateTime submitedTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;


}
