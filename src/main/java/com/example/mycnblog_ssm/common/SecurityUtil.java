package com.example.mycnblog_ssm.common;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * 密码加盐
 */
public class SecurityUtil {
    public static String encrypt(String password) {
        String salt = UUID.randomUUID().toString().replace("-", "");
        String finalPassword = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        return salt + finalPassword;
    }

    public static boolean decrypt(String password, String finalPassword) {
        if (!StringUtils.hasLength(password) || !StringUtils.hasLength(finalPassword)) {
            return false;
        }
        if (finalPassword.length() != 64) {
            return false;
        }
        String salt = finalPassword.substring(0,32);
        String securityPassword = DigestUtils.md5DigestAsHex((password + salt).getBytes());
        return (salt+securityPassword).equals(finalPassword);
    }

    public static void main(String[] args) {
        String password = "123";
        String finalPassword = encrypt(password);
        System.out.println(decrypt("123", finalPassword));
    }
}
