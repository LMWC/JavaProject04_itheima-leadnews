package com.itheima.admin.service;

import com.itheima.common.exception.LeadNewsException;

public interface WemediaNewsAutoScanService {

    void autoScanByMediaNewsId(Integer id) throws Exception;
}
