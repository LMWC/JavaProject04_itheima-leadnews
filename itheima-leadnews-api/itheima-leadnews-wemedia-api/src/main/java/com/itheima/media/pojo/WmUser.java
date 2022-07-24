package com.itheima.media.pojo;

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
 * 自媒体用户信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_user")
@ApiModel(value="WmUser", description="自媒体用户信息表")
public class WmUser implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("ap_user_id")
    private Integer apUserId;

    @TableField("ap_author_id")
    private Integer apAuthorId;

    @ApiModelProperty(value = "登录用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty(value = "盐")
    @TableField("salt")
    private String salt;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "头像")
    @TableField("image")
    private String image;

    @ApiModelProperty(value = "归属地")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "状态	            0 暂时不可用	            1 永久不可用	            9 正常可用")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty(value = "账号类型	            0 个人 	            1 企业	            2 子账号")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "运营评分")
    @TableField("score")
    private Integer score;

    @ApiModelProperty(value = "最后一次登录时间")
    @TableField("login_time")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
