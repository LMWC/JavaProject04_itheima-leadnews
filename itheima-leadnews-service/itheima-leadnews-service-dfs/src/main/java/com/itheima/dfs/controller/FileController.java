package com.itheima.dfs.controller;

import com.github.tobato.fastdfs.domain.conn.FdfsWebServer;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.itheima.common.pojo.Result;
import com.itheima.common.util.GreenImageScan;
import com.itheima.common.util.GreenTextScan;
import com.itheima.dfs.pojo.BaseFileModel;
import com.itheima.dfs.pojo.DFSType;
import com.itheima.dfs.service.IFileService;
import com.itheima.dfs.strategy.FileServiceStrategyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.beans.ExceptionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dfs")
public class FileController {
    /*@Autowired
    private FastFileStorageClient client;

    @Autowired
    private FdfsWebServer fdfsWebServer;*/

    /*@PostMapping("/upload")
    public Result<Map<String,String>>  upload(MultipartFile file) throws Exception {

        //文件本身的流
        InputStream inputStream =file.getInputStream();
        //文件的大小
        long fileSzie = file.getSize();
        //文件的扩展名
        String extName = StringUtils.getFilenameExtension(file.getOriginalFilename());
        StorePath storePath = client.uploadFile(
                inputStream,//文件本身
                fileSzie,
                extName,
                null//元数据  图片的像素  图片的时间戳  图片的location 图片的高度 ..... 可以不写
        );

        //获取路径 返回给前端
        // group1/M00/00/01/wKjTiGLeVtCAcVKfAAB7DssY7IQ320.jpg
        String fullPath = storePath.getFullPath();

        // http://192.168.211.136/group1/M00/00/01/wKjTiGLeVtCAcVKfAAB7DssY7IQ320.jpg

        String url = fdfsWebServer.getWebServerUrl()+fullPath;

        Map<String,String> map = new HashMap<>();
        map.put("url",url);
        return Result.ok(map);
    }*/

    //需求： 既要支持fdfs 又要支持 oss 还要支持七牛

    //
   /* @Autowired
    private IFileService ossFileTemplate;*/

    @Autowired
    private FileServiceStrategyContext strategyContext;

    @Value("${fdfs.type}")
    private String type;

    @Autowired
    private GreenImageScan greenImageScan;

    @Autowired
    private GreenTextScan greenTextScan;

    @PostMapping("/upload")
    public Result<Map<String,String>>  upload(MultipartFile file) throws Exception {
        //这个是要上传的文件的封装对象 fastdfs
        BaseFileModel fileModel = new BaseFileModel(
                "zhangan",//应该是当前登录的用户 上传图片的作者
                file.getSize(),//文件大小
                file.getOriginalFilename(),//扩展名
                file.getBytes()//文件本身
        );
        IFileService iFleService = strategyContext.getIFleService(DFSType.valueOf(type));

        //测试
        List<byte[]> arr = new ArrayList<>();
        arr.add(file.getBytes());//图片本身
        Map map1 = greenImageScan.imageScan(arr);
        System.out.println(map1);
        //
        System.out.println("==========================");
        List<String> contents = new ArrayList<>();
        contents.add("海洛因");
        Map map2 = greenTextScan.greeTextScan(contents);
        System.out.println(map2);


        String url = iFleService.uploadFile(fileModel);
        Map<String,String> map = new HashMap<>();
        map.put("url",url);
        return Result.ok(map);
    }
}
