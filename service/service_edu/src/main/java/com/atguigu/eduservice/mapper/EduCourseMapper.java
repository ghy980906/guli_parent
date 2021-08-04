package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    //根据id查询课程最终信息
    public CoursePublishVo getCourseInfoById(String courseId);


}
