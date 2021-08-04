package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    //添加课程信息
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.addCourseInfo(courseInfoVo);
        return R.ok().message("添加成功");
    }

    //根据课程id查询课程
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfoById(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = eduCourseService.getCourseInfoById(courseId);
        return R.ok().data("courseInfo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("/updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok().message("课程修改成功");
    }

    //根据id查询课程最终信息
    @GetMapping("/getCoursePublishById/{courseId}")
    public R getCoursePublishById(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishById(courseId);
        return R.ok().data("info",coursePublishVo);
    }

}

