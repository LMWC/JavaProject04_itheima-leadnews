package com.itheima.admin.service;

import com.itheima.common.exception.LeadNewsException;
import com.itheima.media.pojo.WmNews;

public interface WemediaNewsAutoScanService {

    void autoScanByMediaNewsId(Integer id) throws Exception;


    public void createArticleInfoData(WmNews wmNews);
}
