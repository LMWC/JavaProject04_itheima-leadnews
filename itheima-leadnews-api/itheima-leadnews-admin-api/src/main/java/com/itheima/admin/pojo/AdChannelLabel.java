package com.itheima.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 频道标签信息表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_channel_label")
@ApiModel(value="AdChannelLabel", description="频道标签信息表")
public class AdChannelLabel implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("channel_id")
    private Integer channelId;

    @ApiModelProperty(value = "标签ID")
    @TableField("label_id")
    private Integer labelId;

    @ApiModelProperty(value = "排序")
    @TableField("ord")
    private Integer ord;


}
