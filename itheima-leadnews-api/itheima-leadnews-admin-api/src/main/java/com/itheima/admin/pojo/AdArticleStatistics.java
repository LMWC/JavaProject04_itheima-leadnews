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
 * 文章数据统计表
 * </p>
 *
 * @author ljh
 * @since 2022-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_article_statistics")
@ApiModel(value="AdArticleStatistics", description="文章数据统计表")
public class AdArticleStatistics implements Serializable {


    @ApiModelProperty(value = "主键")
    @TableId("id")
    private Integer id;

    @ApiModelProperty(value = "主账号ID")
    @TableField("article_we_media")
    private Integer articleWeMedia;

    @ApiModelProperty(value = "子账号ID")
    @TableField("article_crawlers")
    private Integer articleCrawlers;

    @ApiModelProperty(value = "频道ID")
    @TableField("channel_id")
    private Integer channelId;

    @ApiModelProperty(value = "草读量")
    @TableField("read_20")
    private Integer read20;

    @ApiModelProperty(value = "读完量")
    @TableField("read_100")
    private Integer read100;

    @ApiModelProperty(value = "阅读量")
    @TableField("read_count")
    private Integer readCount;

    @ApiModelProperty(value = "评论量")
    @TableField("comment")
    private Integer comment;

    @ApiModelProperty(value = "关注量")
    @TableField("follow")
    private Integer follow;

    @ApiModelProperty(value = "收藏量")
    @TableField("collection")
    private Integer collection;

    @ApiModelProperty(value = "转发量")
    @TableField("forward")
    private Integer forward;

    @ApiModelProperty(value = "点赞量")
    @TableField("likes")
    private Integer likes;

    @ApiModelProperty(value = "不喜欢")
    @TableField("unlikes")
    private Integer unlikes;

    @ApiModelProperty(value = "unfollow")
    @TableField("unfollow")
    private Integer unfollow;

    @ApiModelProperty(value = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;


}
