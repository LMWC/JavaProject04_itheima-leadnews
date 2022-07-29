package com.itheima.article.service.impl;

import com.itheima.article.dto.ArticleInfoDto;
import com.itheima.article.mapper.ApArticleConfigMapper;
import com.itheima.article.mapper.ApArticleContentMapper;
import com.itheima.article.pojo.ApArticle;
import com.itheima.article.mapper.ApArticleMapper;
import com.itheima.article.pojo.ApArticleConfig;
import com.itheima.article.pojo.ApArticleContent;
import com.itheima.article.service.ApArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文章信息表，存储已发布的文章 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2021-12-22
 */
@Service
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Override
    @Transactional
    public ApArticle saveArticle(ArticleInfoDto dto) {
        //插入3个表即可
        ApArticle apArticle = dto.getApArticle();
        Long id1 = apArticle.getId();
        System.out.println(id1);
        apArticleMapper.insert(apArticle);
        Long id = apArticle.getId();
        System.out.println(id);

        ApArticleConfig apArticleConfig = dto.getApArticleConfig();
        apArticleConfig.setArticleId(id);
        apArticleConfigMapper.insert(apArticleConfig);

        ApArticleContent apArticleContent = dto.getApArticleContent();
        apArticleContent.setArticleId(id);
        apArticleContentMapper.insert(apArticleContent);

        return apArticle;
    }
}
