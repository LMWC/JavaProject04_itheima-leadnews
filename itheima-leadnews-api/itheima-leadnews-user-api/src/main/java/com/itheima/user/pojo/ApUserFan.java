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
 * APP用户粉丝信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_user_fan")
@ApiModel(value="ApUserFan", description="APP用户粉丝信息表")
public class ApUserFan implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "粉丝ID")
    @TableField("fans_id")
    private Integer fansId;

    @ApiModelProperty(value = "粉丝昵称")
    @TableField("fans_name")
    private String fansName;

    @ApiModelProperty(value = "粉丝忠实度	            0 正常	            1 潜力股	            2 勇士	            3 铁杆	            4 老铁")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "是否可见我动态")
    @TableField("is_display")
    private Integer isDisplay;

    @ApiModelProperty(value = "是否屏蔽私信")
    @TableField("is_shield_letter")
    private Integer isShieldLetter;

    @ApiModelProperty(value = "是否屏蔽评论")
    @TableField("is_shield_comment")
    private Integer isShieldComment;


}
