package com.example.mycnblog_ssm.controller;

import com.example.mycnblog_ssm.common.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/reg")
    public Object reg(String username, String password) {
//        todo:非空校验
        int num = 10 / 0;
        return AjaxResult.success("注册成功", 1);
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "Hi,blog";
    }
}
