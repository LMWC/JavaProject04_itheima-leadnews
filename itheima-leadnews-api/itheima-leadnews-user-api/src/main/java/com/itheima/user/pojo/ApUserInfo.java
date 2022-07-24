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
 * APP用户信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_info")
@ApiModel(value="ApUserInfo", description="APP用户信息表")
public class ApUserInfo implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "真是姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "身份证号,aes加密")
    @TableField("idno")
    private String idno;

    @ApiModelProperty(value = "公司")
    @TableField("company")
    private String company;

    @ApiModelProperty(value = "职业")
    @TableField("occupation")
    private String occupation;

    @ApiModelProperty(value = "年龄")
    @TableField("age")
    private Integer age;

    @TableField("birthday")
    private LocalDateTime birthday;

    @ApiModelProperty(value = "个人格言")
    @TableField("introduction")
    private String introduction;

    @ApiModelProperty(value = "归属地")
    @TableField("location")
    private String location;

    @ApiModelProperty(value = "粉丝数量")
    @TableField("fans")
    private Integer fans;

    @ApiModelProperty(value = "关注数量")
    @TableField("follows")
    private Integer follows;

    @ApiModelProperty(value = "是否允许推荐我给好友")
    @TableField("is_recommend_me")
    private Integer isRecommendMe;

    @ApiModelProperty(value = "是否允许给我推荐好友")
    @TableField("is_recommend_friend")
    private Integer isRecommendFriend;

    @ApiModelProperty(value = "是否分享页面显示头像")
    @TableField("is_display_image")
    private Integer isDisplayImage;

    @ApiModelProperty(value = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;


}
