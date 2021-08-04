package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
@RestController
@RequestMapping("/eduservice/educhapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    //根据课程id查询
    @GetMapping("getCharpterVo/{courseid}")
    public R getCharpterVo(@PathVariable String courseid){
        List<ChapterVo> list =  eduChapterService.getCharpterById(courseid);
        return R.ok().data("list",list);
    }

    //添加
    @PostMapping("/addCharpter")
    public R addCharpter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("/getCharpter/{charpterId}")
    public R getCharpter(@PathVariable String charpterId){
        EduChapter eduChapter = eduChapterService.getById(charpterId);
        return R.ok().data("charpter",eduChapter);
    }

    //修改
    @PostMapping("/updateCharpter")
    public R updateCharpter(@RequestBody EduChapter eduChapter){
        boolean update = eduChapterService.updateById(eduChapter);
        return update == true ? R.ok() : R.error();
    }

    //删除
    @DeleteMapping("/deleteCharpter/{charpterId}")
    public R deleteCharpter(@PathVariable String charpterId){
        boolean flag = eduChapterService.updateCharpter(charpterId);
        return flag==true ? R.ok() : R.error();
    }

}

