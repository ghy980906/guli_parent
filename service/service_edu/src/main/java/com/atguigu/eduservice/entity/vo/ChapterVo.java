package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ghy
 * @create 2021-07-26 14:48
 */


@Data
public class ChapterVo {         //章节

    private String id;

    private String title;

    private List<VideoVo> children = new ArrayList<>();
}
