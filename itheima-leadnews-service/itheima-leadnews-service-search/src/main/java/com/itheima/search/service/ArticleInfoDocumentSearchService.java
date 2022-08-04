package com.itheima.search.service;

import com.itheima.common.pojo.PageInfo;
import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.search.dto.SearchDto;

public interface ArticleInfoDocumentSearchService {

    PageInfo<ArticleInfoDocument> search(SearchDto searchDto);
}
