package com.itheima.admin.pojo;

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
 * 推荐策略信息表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_recommend_strategy")
@ApiModel(value="AdRecommendStrategy", description="推荐策略信息表")
public class AdRecommendStrategy implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "策略名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "策略描述")
    @TableField("description")
    private String description;

    @ApiModelProperty(value = "是否有效")
    @TableField("is_enable")
    private Integer isEnable;

    @ApiModelProperty(value = "分组ID")
    @TableField("group_id")
    private Integer groupId;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
