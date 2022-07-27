package com.itheima.media.dto;

import com.itheima.media.pojo.WmNews;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WmNewsDto extends WmNews {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}