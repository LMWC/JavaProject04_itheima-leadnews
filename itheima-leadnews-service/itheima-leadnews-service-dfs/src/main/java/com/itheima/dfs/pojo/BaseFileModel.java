package com.itheima.dfs.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

@Data
@NoArgsConstructor
public class BaseFileModel {
    //上传文件的作者
    private String author = "default";


    //文件大小
    private Long size;

    //文件源文件名称
    private String name;

    //上传文件的md5值
    private String md5;
    //上传文件本身数据
    private byte[] content;

    public BaseFileModel(String author, Long size, String name, byte[] content) {
        this.size = size;
        this.name = name;
        this.content = content;
        this.author = author;
        StringBuilder stringBuilder = new StringBuilder();
        this.md5 = DigestUtils.md5DigestAsHex(stringBuilder.append(this.author).append(this.name).append(this.size).toString().getBytes());

    }
}