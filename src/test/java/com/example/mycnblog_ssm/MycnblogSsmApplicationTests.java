package com.example.mycnblog_ssm;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@SpringBootTest
class MycnblogSsmApplicationTests {

    @Test
    void contextLoads() {
        String password ="20030820@Wgl";
        String salt1 = String.valueOf(UUID.randomUUID());
        String salt2 = String.valueOf(UUID.randomUUID());
        String md5 = DigestUtils.md5DigestAsHex(password.getBytes());
        String md4 = DigestUtils.md5DigestAsHex((password+salt2).getBytes());


        System.out.println(md5);
    }

}
