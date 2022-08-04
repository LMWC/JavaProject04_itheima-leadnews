package com.itheima.search.repository;

import com.itheima.search.document.ArticleInfoDocument;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * <ArticleInfoDocument,Long>
 *     ArticleInfoDocument 指定POJO类型--操作哪一个索引
 *     Long ：指定唯一标识的数据类型
 */

public interface ArticleInfoDocumentRepository extends ElasticsearchRepository<ArticleInfoDocument,Long> {

    List<ArticleInfoDocument> findByTitle(String title);
}
