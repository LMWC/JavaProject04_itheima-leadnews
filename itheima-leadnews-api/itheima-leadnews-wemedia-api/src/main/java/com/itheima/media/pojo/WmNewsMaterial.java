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

/**
 * <p>
 * 自媒体图文引用素材信息表  【暂时不用】
 * </p>
 *
 * @author ljh
 * @since 2021-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("wm_news_material")
@ApiModel(value="WmNewsMaterial", description="自媒体图文引用素材信息表  【暂时不用】")
public class WmNewsMaterial implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "素材ID")
    @TableField("material_id")
    private Integer materialId;

    @ApiModelProperty(value = "图文ID")
    @TableField("news_id")
    private Integer newsId;

    @ApiModelProperty(value = "引用类型	            0 内容引用	            1 主图引用")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "引用排序")
    @TableField("ord")
    private Integer ord;


}
