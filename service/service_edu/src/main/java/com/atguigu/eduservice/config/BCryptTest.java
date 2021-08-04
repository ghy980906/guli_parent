package com.atguigu.eduservice.config;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @author ghy
 * @create 2021-08-02 17:50
 */
public class BCryptTest {


    String pass = "123456";

    //密码加密
    public static String encode(String pass){
        return BCrypt.hashpw(pass,BCrypt.gensalt());
    }

    //密码校验
    public static boolean match(String pass,String encodePass){
        return BCrypt.checkpw(pass,encodePass);
    }
}
