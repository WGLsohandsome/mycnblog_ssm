package com.example.mycnblog_ssm.controller;

import com.example.mycnblog_ssm.common.AjaxResult;
import com.example.mycnblog_ssm.common.Constant;
import com.example.mycnblog_ssm.model.UserInfo;
import com.example.mycnblog_ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/reg")
    public Object reg(String username, String password) {
//        todo:非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法请求");
        }
        int result = userService.add(username, password);
        if (result == 1) {
            return AjaxResult.success("注册成功", result);
        }
//        int num = 10 / 0;
        return AjaxResult.fail(-1, "注册失败");
    }

    @RequestMapping("/login")
    public int login(String username, String password, HttpServletRequest request) {
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return 0;
        }
        UserInfo userInfo = userService.login(username, password);
        if (userInfo == null || userInfo.getId() <= 0) {
            return 0;
        } else {
//            成功登录
//            保存到session
            HttpSession session = request.getSession();
            session.setAttribute(Constant.session_userinfo_key,userInfo);
            return 1;
        }
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "Hi,blog";
    }


}
