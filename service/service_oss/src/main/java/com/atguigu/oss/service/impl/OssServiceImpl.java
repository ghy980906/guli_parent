package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author ghy
 * @create 2021-07-14 11:28
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
//        String endpoint = "yourEndpoint";
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
//        String accessKeyId = "yourAccessKeyId";
//        String accessKeySecret = "yourAccessKeySecret";
        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
//        System.out.println(endPoint);
//        System.out.println(accessKeyId);
//        System.out.println(accessKeySecret);
//        System.out.println(bucketName);

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
//        InputStream inputStream = new FileInputStream("D:\\localpath\\examplefile.txt");
        try {
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();

            //uuid
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            filename = uuid+filename;
            //日期
            String datePath = new DateTime().toString("yyyy/MM/dd");
            filename = datePath+"/"+filename;


            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filename, inputStream);

            //https://sunnycat.oss-cn-beijing.aliyuncs.com/pic/1.png
            String url = "https://"+bucketName+"."+endPoint+"/"+filename;


// 关闭OSSClient。
            ossClient.shutdown();
            inputStream.close();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }

    }
}
