package com.itheima.search.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * 通过注解来实现创建索引和建立映射
 *
 * @Document(indexName = "article")
 *
 *  document 就是和ES建立映射关系的注解
 *
 *       indexName 指定索引名
 *   @Id  指定文档的唯一标识 将来会自动将数字转成string
 *
 *   @Field(type = FieldType.Text,index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
 *     field注解用于建立ES中的字段的映射
 *      type 指定该字段的数据类型
 *      index :标识是否要索引 默认是true
 *      analyzer 指定建立倒排索引的时候使用分词器
 *      searchAnalyzer： 指定搜索的时候使用分词器 这个可以不用配置，如果不配置默认使用的是同一个分词器
 *
 */
@Document(indexName = "article")
@Data
public class ArticleInfoDocument {

    @Id//文档唯一标识也是 字段
    private Long id;

    @Field(type = FieldType.Text,index = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart")
    private String title;

    private Integer authorId;

    private String authorName;

    private Integer channelId;

    private String channelName;

    private Integer layout;

    private String images;


    //自己写一个转换器 将es中的long 转成localdatetime
    private LocalDateTime createdTime;

    private LocalDateTime publishTime;
}
