package com.itheima.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.search.dto.SearchDto;
import com.itheima.search.mapper.ApAssociateWordsMapper;
import com.itheima.search.pojo.ApAssociateWords;
import com.itheima.search.service.ApAssociateWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.trie4j.patricia.PatriciaTrie;

import java.util.List;

/**
 * <p>
 * 联想词表 服务实现类
 * </p>
 *
 * @author ljh
 * @since 2022-08-04
 */
@Service
public class ApAssociateWordsServiceImpl extends ServiceImpl<ApAssociateWordsMapper, ApAssociateWords> implements ApAssociateWordsService {

    @Autowired
    private PatriciaTrie patriciaTrie;

    @Override
    public List<String> searchTen(SearchDto searchDto) {
        //1.从数据库中查询出所有的联想词

        //2.初始化树  应该只需要初始化一次 系统启动的时候


        //3.从树中查询 截取10条
        List<String> strings =(List<String>) patriciaTrie.predictiveSearch(searchDto.getKeywords());
        if(strings!=null && strings.size()>10){
            strings = strings.subList(0,9);
        }

        return strings;
    }
}
