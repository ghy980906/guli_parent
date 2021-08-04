package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程信息
    void addCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程
    CourseInfoVo getCourseInfoById(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //查询课程最终信息
    CoursePublishVo getCoursePublishById(String courseId);
}
