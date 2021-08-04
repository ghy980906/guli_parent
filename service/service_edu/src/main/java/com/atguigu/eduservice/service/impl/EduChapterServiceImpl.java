package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.entity.vo.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-22
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getCharpterById(String courseid) {

        //查询所有章节
        QueryWrapper<EduChapter> wrapperCharpter = new QueryWrapper<>();
        wrapperCharpter.eq("course_id", courseid);
        List<EduChapter> chapterList = baseMapper.selectList(wrapperCharpter);

        //查询所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseid);
        List<EduVideo> videoList = eduVideoService.list(wrapperVideo);

        //最终返回结果
        List<ChapterVo> finalList = new ArrayList<>();

        //封装章节
        for (EduChapter eduChapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> eduVideos = new ArrayList<>();

            for (EduVideo eduVideo : videoList) {
                VideoVo videoVo = new VideoVo();
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    eduVideos.add(videoVo);
                }
            }
            chapterVo.setChildren(eduVideos);
        }

        return finalList;
    }

    //删除章节
    @Override
    public boolean updateCharpter(String charpterId) {
        //先查询该章节目录下是否用小节，如果有，不允许删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", charpterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0) {
            throw new GuliException(20001, "不允许删除");
        }
        int i = baseMapper.deleteById(charpterId);
        return i > 0;
    }
}