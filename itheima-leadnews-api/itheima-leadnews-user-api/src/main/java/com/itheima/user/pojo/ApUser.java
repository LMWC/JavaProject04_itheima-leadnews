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
 * APP用户信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user")
@ApiModel(value="ApUser", description="APP用户信息表")
public class ApUser implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "密码、通信等加密盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "密码,md5加密")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "头像")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "0 男	            1 女	            2 未知")
    @TableField("sex")
    private Integer sex;

    @ApiModelProperty(value = "0 未	            1 是")
    @TableField("is_certification")
    private Integer isCertification;

    @ApiModelProperty(value = "是否身份认证")
    @TableField("is_identity_authentication")
    private Integer isIdentityAuthentication;

    @ApiModelProperty(value = "0正常	            1锁定")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "0 普通用户	            1 自媒体人	            2 大V")
    @TableField("flag")
    private Integer flag;

    @ApiModelProperty(value = "注册时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
