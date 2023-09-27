package com.example.mycnblog_ssm.common;

import com.example.mycnblog_ssm.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    /**
     * 查询登录用户session
     * @param request
     * @return
     */
    public static UserInfo getLoginUser(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null &&
                session.getAttribute(Constant.session_userinfo_key)!=null){
            return (UserInfo) session.getAttribute(Constant.session_userinfo_key);
        }
        return null;
    }
}
