package com.itheima.behaviour.pojo;

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
 * APP行为实体表,一个行为实体可能是用户或者设备，或者其它
 * </p>
 *
 * @author ljh
 * @since 2022-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ap_behavior_entry")
@ApiModel(value="ApBehaviorEntry", description="APP行为实体表,一个行为实体可能是用户或者设备，或者其它")
public class ApBehaviorEntry implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "实体类型	            0终端设备	            1用户")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "实体ID")
    @TableField("entry_id")
    private Integer entryId;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
