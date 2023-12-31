package com.example.mycnblog_ssm.mapper;

import com.example.mycnblog_ssm.model.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.text.StringContent;
import java.util.List;

@Mapper
public interface ArticleMapper {

    public List<ArticleInfo> getMylist(@Param("uid")Integer uid);

    public ArticleInfo getDetail(@Param("aid")Integer aid);

    public int update(@Param("aid") Integer aid, @Param("title") String title, @Param("content")String Content,
    @Param("uid")Integer uid,@Param("time")String time);

    List<ArticleInfo> getlist(Integer offset, Integer psize);

    public int getTotal();

    int edit( @Param("title") String title, @Param("content")String Content,
             @Param("uid")Integer uid,@Param("time")String time);
}
