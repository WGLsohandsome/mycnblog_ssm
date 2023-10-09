package com.example.mycnblog_ssm.service;

import com.example.mycnblog_ssm.mapper.ArticleMapper;
import com.example.mycnblog_ssm.model.ArticleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    public List<ArticleInfo> getMyList(Integer uid){
        return articleMapper.getMylist(uid);
    }
    public ArticleInfo getDetail(Integer aid){
        return articleMapper.getDetail(aid);
    }

    public int update(Integer aid,Integer uid,String title,String content){
        return articleMapper.update(aid,title,content,uid);
    }

    public List<ArticleInfo> getList(Integer offset, Integer psize) {
        return articleMapper.getlist(offset,psize);
    }

    public int getTotal(){
        return articleMapper.getTotal();
    }
}
