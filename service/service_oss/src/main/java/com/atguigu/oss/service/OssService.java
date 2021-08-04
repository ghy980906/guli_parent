package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author ghy
 * @create 2021-07-14 11:28
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
