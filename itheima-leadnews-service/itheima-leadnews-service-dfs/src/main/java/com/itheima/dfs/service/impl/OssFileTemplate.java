package com.itheima.dfs.service.impl;

import com.itheima.dfs.pojo.BaseFileModel;
import com.itheima.dfs.pojo.DFSType;
import com.itheima.dfs.service.AbstractDfsTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OssFileTemplate extends AbstractDfsTemplate {
    @Override
    public String uploadFile(BaseFileModel fileModel) {


        return null;
    }

    @Override
    public boolean delete(String fullPath) {

        return false;
    }

    @Override
    public List<byte[]> download(List<String> fullPath) {


        return null;
    }

    @Override
    public DFSType support() {
        return DFSType.OSSDFS;
    }
}
