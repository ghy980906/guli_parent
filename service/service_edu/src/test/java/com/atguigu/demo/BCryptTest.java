package com.atguigu.demo;

import cn.hutool.crypto.digest.BCrypt;
import org.junit.Test;

/**
 * @author ghy
 * @create 2021-08-02 17:50
 */
public class BCryptTest {


    String pass = "123456";

    @Test
    //密码加密
    public void encode(){
        String pass = "123456";
        String hashpw = BCrypt.hashpw(pass, BCrypt.gensalt());
        System.out.println(hashpw);
    }

    //密码校验
    @Test
    public void match(){
        String pass = "123456";
        String hashpw = BCrypt.hashpw(pass, BCrypt.gensalt());
        boolean checkpw = BCrypt.checkpw("12345", hashpw);
        System.out.println(pass);
        System.out.println(hashpw);
        System.out.println(checkpw);
    }
}
