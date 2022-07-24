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
 * 自媒体子账号信息表
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_sub_user")
@ApiModel(value="WmSubUser", description="自媒体子账号信息表")
public class WmSubUser implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "主账号ID")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty(value = "子账号ID")
    @TableField("children_id")
    private Integer childrenId;


}
