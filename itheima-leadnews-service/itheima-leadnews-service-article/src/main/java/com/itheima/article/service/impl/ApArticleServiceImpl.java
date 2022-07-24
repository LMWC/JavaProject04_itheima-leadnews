package com.itheima.article.service.impl;

import com.itheima.article.pojo.ApArticle;
import com.itheima.article.mapper.ApArticleMapper;
import com.itheima.article.service.ApArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
