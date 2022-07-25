package com.itheima.dfs.service.impl;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.itheima.dfs.pojo.BaseFileModel;
import com.itheima.dfs.pojo.DFSType;
import com.itheima.dfs.service.AbstractDfsTemplate;
import com.itheima.dfs.service.IFileService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FdfsFileTemplate extends AbstractDfsTemplate {

    @Autowired
    private FastFileStorageClient client;

    @Autowired
    private FdfsWebServer fdfsWebServer;


    //图片的地址
    @Override
    public String uploadFile(BaseFileModel fileModel) {




        HashSet<MetaData> metaData = new HashSet<>();
        //设置md5作为设置图片的签名
        metaData.add(new MetaData("md5", fileModel.getMd5()));//图片的地址 ，图片的像素 拍摄日期等
        metaData.add(new MetaData("author", fileModel.getAuthor()));//图片的地址 ，图片的像素 拍摄日期等

        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(fileModel.getContent());
        StorePath storePath = client.uploadFile(
                byteInputStream,
                fileModel.getSize(),
                StringUtils.getFilenameExtension(fileModel.getName()),
                metaData);
        String webServerUrl = fdfsWebServer.getWebServerUrl();
        return webServerUrl + storePath.getFullPath();
    }

    @Override
    public boolean delete(String fullPath) {



        try {
            client.deleteFile(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<byte[]> download(List<String> fullPaths) {




        List<byte[]> picList = fullPaths.stream().map(
                fullpath -> {
                    try {
                        StorePath storePath = StorePath.parseFromUrl(fullpath);
                        byte[] bytes = client.downloadFile(storePath.getGroup(), storePath.getPath(), ins -> IOUtils.toByteArray(ins));
                        return bytes;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
        ).filter(bytes -> bytes != null).collect(Collectors.toList());
        return picList;
    }

    @Override
    public DFSType support() {
        return DFSType.FASTDFS;
    }
}