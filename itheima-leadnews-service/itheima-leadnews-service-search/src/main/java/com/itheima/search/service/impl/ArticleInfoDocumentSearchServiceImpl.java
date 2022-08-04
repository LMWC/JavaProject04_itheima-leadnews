package com.itheima.search.service.impl;

import com.itheima.common.pojo.PageInfo;
import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.search.dto.SearchDto;
import com.itheima.search.service.ArticleInfoDocumentSearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service//注册
public class ArticleInfoDocumentSearchServiceImpl implements ArticleInfoDocumentSearchService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public PageInfo<ArticleInfoDocument> search(SearchDto searchDto) {

        //1.获取页面传递的关键字
        String keywords = searchDto.getKeywords();
        if (StringUtils.isEmpty(keywords)) {
            //2.判断关键字是否为null 如果为null 设置一个默认值 黑马
            keywords = "黑马";
        }
        //3. 创建查询对象 的构建对象
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();

        //4. 设置【各种查询条件】
        //4.1 使用match查询 （先分词 再执行查询）
        builder.withQuery(QueryBuilders.matchQuery("title", keywords));

        //4.2 设置分页查询条件  参数1 指定当前的页码 0 表示第一个页  参数2 指定每页显示的行
        Integer page = searchDto.getPage();//最好判断
        Integer size = searchDto.getSize();//最好判断
        Pageable pageable = PageRequest.of(page - 1, size);
        builder.withPageable(pageable);

        //4.3 设置高亮的条件（设置高亮的字段，设置前缀和后缀）

        builder.withHighlightFields(new HighlightBuilder.Field("title"));

        builder.withHighlightBuilder(new HighlightBuilder().preTags("<em style='color:red'>").postTags("</em>"));


        //4.4 排序
        builder.withSort(SortBuilders.fieldSort("publishTime").order(SortOrder.DESC));

        //5. 构建 查询对象
        NativeSearchQuery searchQuery = builder.build();
        //6. 执行查询
        //参数1 指定 要查询的对象（封装了所有的条件）
        //参数2 指定查询到的结果 要返回的数据类型字节码对象
        SearchHits<ArticleInfoDocument> searchResult = elasticsearchRestTemplate.search(searchQuery, ArticleInfoDocument.class);

        //7. 获取结果集 封装 返回
        Long totalHits = searchResult.getTotalHits();
        Long totalPages =totalHits%size>0?totalHits/size+1:totalHits/size;

        List<ArticleInfoDocument> list = new ArrayList<>();
        for (SearchHit<ArticleInfoDocument> searchHit : searchResult) {
            ArticleInfoDocument doc = searchHit.getContent();//一行一行的数据

            //获取高亮的数据替换没高亮的数据

            List<String> highlightField = searchHit.getHighlightField("title");
            if(highlightField!=null && highlightField.size()>0){
                doc.setTitle(highlightField.get(0));
            }

            list.add(doc);
        }


        return new PageInfo<ArticleInfoDocument>(Long.valueOf(page),Long.valueOf(size),totalHits,totalPages,list);
    }
}
