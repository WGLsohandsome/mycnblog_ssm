package com.example.mycnblog_ssm.controller;

import com.example.mycnblog_ssm.common.AjaxResult;
import com.example.mycnblog_ssm.common.Constant;
import com.example.mycnblog_ssm.common.SecurityUtil;
import com.example.mycnblog_ssm.common.SessionUtil;
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
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法请求");
        }
        int result = userService.add(username, SecurityUtil.encrypt(password));
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

        UserInfo userInfo = userService.getUserByName(username);
        if (userInfo == null || userInfo.getId() <= 0) {
            return 0;
        } else {
            boolean result = SecurityUtil.decrypt(password, userInfo.getPassword());
            if (result) {
                //            成功登录
//            保存到session
                HttpSession session = request.getSession();
                session.setAttribute(Constant.session_userinfo_key, userInfo);
                return 1;
            }
        }
        return -1;
    }

    @RequestMapping("/logout")
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null &&
                session.getAttribute(Constant.session_userinfo_key) != null) {
            session.removeAttribute(Constant.session_userinfo_key);
        }
        return true;
    }

    @RequestMapping("/hi")
    public String sayHi() {
        return "Hi,blog";
    }

    @RequestMapping("/myinfo")
    public UserInfo myInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(Constant.session_userinfo_key) != null) {
            return (UserInfo) session.getAttribute(Constant.session_userinfo_key);
        }
        return null;
    }


}
