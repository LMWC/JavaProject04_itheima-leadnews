package com.itheima.search;

import com.itheima.search.document.ArticleInfoDocument;
import com.itheima.search.repository.ArticleInfoDocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class SearchTest {

    @Autowired
    private ArticleInfoDocumentRepository repository;

    //实现对文档的CRUD
    @Test
    public void addDoc(){
        ArticleInfoDocument article = new ArticleInfoDocument();
        article.setTitle("测试文档标题 129");
        article.setId(123L);
        article.setCreatedTime(LocalDateTime.now());


        repository.save(article);
    }
    @Test
    public void get(){
        repository.findById(123L);
    }
    @Test
    public void delete(){
        repository.deleteById(123L);
    }
    @Test
    public void find(){
        Iterable<ArticleInfoDocument> all = repository.findAll();
        //根据某一个条件来查
        List<ArticleInfoDocument> iter = repository.findByTitle("测试");
        for (ArticleInfoDocument document : iter) {
            System.out.println(document.getTitle());
        }
    }
}
