package com.itheima.sync.repository;

import com.itheima.search.document.ArticleInfoDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DocumentRepository extends ElasticsearchRepository<ArticleInfoDocument, Long> {
}
