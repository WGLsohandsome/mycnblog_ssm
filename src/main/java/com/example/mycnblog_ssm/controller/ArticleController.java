package com.example.mycnblog_ssm.controller;

import com.example.mycnblog_ssm.common.AjaxResult;
import com.example.mycnblog_ssm.common.Constant;
import com.example.mycnblog_ssm.common.SessionUtil;
import com.example.mycnblog_ssm.model.ArticleInfo;
import com.example.mycnblog_ssm.model.UserInfo;
import com.example.mycnblog_ssm.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public List<ArticleInfo> getList(Integer pindex,Integer psize){
        if(pindex==null || psize==null){
            return null;
        }
        int offset = (pindex-1)*psize;
        return articleService.getList(offset,psize);
    }

    @RequestMapping("/mylist")
    public List<ArticleInfo> getMyList(HttpServletRequest request) {
        UserInfo userInfo = SessionUtil.getLoginUser(request);
        if (userInfo != null) {
            return articleService.getMyList(userInfo.getId());
        }
        return null;
    }

    @RequestMapping("detail")
    public Object getDetail(Integer aid) {
        if (aid != null && aid >= 0) {
            return AjaxResult.success(articleService.getDetail(aid));
        }
        return AjaxResult.fail(-1, "查询失败");
    }

    @RequestMapping("/detailbyid")
    public Object getDetailById(Integer aid, HttpServletRequest request) {
        if (aid != null && aid >= 0) {
            ArticleInfo articleInfo = articleService.getDetail(aid);
//            归属人验证
            UserInfo userInfo = SessionUtil.getLoginUser(request);
            if (userInfo != null && articleInfo != null
                    && userInfo.getId() == articleInfo.getUid()) {
                return AjaxResult.success(articleInfo);
            }
        }
        return AjaxResult.fail(-1, "查询失败");
    }
    @RequestMapping("/update")
    public int update(HttpServletRequest request,String title,Integer aid,String content,String time){
        return articleService.update(aid, Objects.requireNonNull(SessionUtil.getLoginUser(request)).getId(),title,content,time);
    }

    @RequestMapping("/getTotal")
    public Integer getTotal(Integer psize){
        if(psize==null){
            return null;
        }
        int total = articleService.getTotal();
        if(total <= 0){
            return null;
        }
        return (int) Math.ceil(total*1.0/psize);
    }
    @RequestMapping("/edit")
    public int edit(HttpServletRequest request,String title,String content,String time){
        return articleService.edit(Objects.requireNonNull(SessionUtil.getLoginUser(request)).getId(),title,content,time);
    }
}
