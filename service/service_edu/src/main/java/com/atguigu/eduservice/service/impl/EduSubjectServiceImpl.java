package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-15
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
//
//    @Autowired
//    private EduSubjectMapper eduSubjectMapper;

    @Override
    public void addSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            EasyExcel.read(inputStream,SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {


        }
    }

    ////课程列表(树形展示)
    @Override
    public List<OneSubject> getAllSubject() {
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id","0");
        //查询所有一级分类
//        List<EduSubject> oneSubject = eduSubjectMapper.selectList(queryWrapper);
        List<EduSubject> oneSubject = baseMapper.selectList(queryWrapper);
        //查看所有的二级分类
        QueryWrapper<EduSubject> twoQuery = new QueryWrapper<>();
        twoQuery.ne("parent_id", "0");
        List<EduSubject> twoSubject = baseMapper.selectList(twoQuery);

//        封装一级分类
        List<OneSubject> oneSubjectsList = new ArrayList<>();

        //获取每个oneSubject里的edusubject对象
        for (EduSubject subject : oneSubject) {

            OneSubject OSubject = new OneSubject();
            BeanUtils.copyProperties(subject,OSubject);
            oneSubjectsList.add(OSubject);

            //封装二集分类
            List<TwoSubject> twoSubjectList = new ArrayList<>();

            for (EduSubject subject2 : twoSubject) {
                TwoSubject TSubject = new TwoSubject();

                if (subject2.getParentId().equals(subject.getId())){
                    BeanUtils.copyProperties(subject2,TSubject);
                    twoSubjectList.add(TSubject);
                }
            }
            OSubject.setChildrens(twoSubjectList);
        }

        return oneSubjectsList;
    }
}
