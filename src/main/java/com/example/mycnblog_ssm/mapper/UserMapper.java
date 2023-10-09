package com.example.mycnblog_ssm.mapper;

import com.example.mycnblog_ssm.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public int add(@Param("username") String username,@Param("password") String password);
    public UserInfo login(@Param("username") String username, @Param("password") String password);

    UserInfo getUserByName(@Param("username")String username);
}
