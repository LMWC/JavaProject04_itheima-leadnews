package com.itheima.media.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 自媒体子账号权限信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_user_auth")
@ApiModel(value="WmUserAuth", description="自媒体子账号权限信息表")
public class WmUserAuth implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "账号ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "资源类型	            0 菜单	            1 频道	            2 按钮")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "资源名称")
    @TableField("name")
    private String name;


}
