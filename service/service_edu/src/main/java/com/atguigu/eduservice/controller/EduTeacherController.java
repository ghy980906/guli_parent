package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
@RestController
@RequestMapping("/eduservice/teacher")
//@Transactional
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;
//
//    @Autowired
//    private EduTeacherMapper teacherMapper;

    @GetMapping("/findAll")
    public R findAll(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    @DeleteMapping("{id}")
    public R remove(@PathVariable String id){
        boolean b = teacherService.removeById(id);
//        int i = teacherMapper.deleteById(id);
//        List<String> list = Arrays.asList(ids);
//        teacherService.removeByIds(list);
        if(b){
            return R.ok();

        }else {
            return R.error();
        }
    }

    @DeleteMapping("/deleteByIds")
    public R deleteByIds(String[] ids){
        List<String> list = Arrays.asList(ids);
        boolean remove = teacherService.removeByIds(list);
        return  remove == true? R.ok():R.error();
    }

    //分页查询
    @GetMapping("pageTeacher/{current}/{size}")
    public R pageTeacher(@PathVariable Integer current,@PathVariable Integer size){
        Page<EduTeacher> page = new Page<>(current,size);
//        Page<EduTeacher> teacherPage = teacherService.page(page,null);
//        long total = page.getTotal();
//        List<EduTeacher> records = page.getRecords();
//        return R.ok().data("total",total).data("records",records);
        IPage<EduTeacher> iPage = teacherService.page(page, null);
        long total = iPage.getTotal();
        List<EduTeacher> records = iPage.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //条件分页查询
    @PostMapping("pageCondition/{current}/{size}")
    public R pageCon(@PathVariable Integer current, @PathVariable Integer size,
                     @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        IPage<EduTeacher> iPage = teacherService.page(page, wrapper);
        long total = iPage.getTotal();
        List<EduTeacher> records = iPage.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //添加讲师
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = teacherService.save(eduTeacher);
        return save == true ? R.ok() : R.error();

    }

    //根据id查询讲师
    @GetMapping("findone/{id}")
    public R findOne(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
//        try {                  //测试异常
//            int i = 10/0;
//        }catch (Exception e){
//            throw new GuliException(500,"执行了自定义异常");
//        }
        return R.ok().data("teacher",teacher);
    }
    //修改老师
    @PostMapping("update")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = teacherService.updateById(eduTeacher);
        if(b){
            return R.ok();

        }else {
            return R.error();
        }
    }

}

