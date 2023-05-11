package org.javaboy.rememberme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@SpringBootTest
class RemembermeApplicationTests {

    @Test
    void contextLoads() throws UnsupportedEncodingException {
//        String s = new String(Base64.getDecoder().decode("YWRtaW46MTY1NDIzMTc0NTI5NjoxZDc5NmZjZjIxMGMxZTc2ZTBjM2ZmNWI3YmVjMzE5NA"),"UTF-8");
        String s = new String(Base64.getDecoder().decode("bWtLV2tKbVR2eXNYaUNwNlJTRnNRQSUzRCUzRDpkSkVnbDNxMCUyRnI0WEpEY3prRUFWWUElM0QlM0Q"),"UTF-8");
        System.out.println("s = " + s);

//        admin:1654231745296:1d796fcf210c1e76e0c3ff5b7bec3194
        /**
         * 第一部分：admin 用户名
         * 第二部分：时间戳 有效期（两周时间）
         * 第三部分：md5计算出来的散列函数的值  里面含有key
         */

        /**
         * 源码分析：
         * 客户端每次请求携带remember me ，服务端接收请求，提取用户名，过期时间和散列函数的值，根据用户名拿到用户密码，
         * 过期时间判断是否过期，根据key 用户名 密码 对散列函数进行比较，若相同，则直接无登录访问接口
         */

//        mkKWkJmTvysXiCp6RSFsQA%3D%3D:dJEgl3q0%2Fr4XJDczkEAVYA%3D%3D
//        token + serics
    }

    @Test
    void test1(){

    }

}
