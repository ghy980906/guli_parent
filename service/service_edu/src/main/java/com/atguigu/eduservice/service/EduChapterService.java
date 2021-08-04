package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
public interface EduChapterService extends IService<EduChapter> {

    //根据课程id查询
    List<ChapterVo> getCharpterById(String courseid);

    boolean updateCharpter(String charpterId);
}
