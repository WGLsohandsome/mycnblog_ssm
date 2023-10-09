package com.example.mycnblog_ssm.service;

import com.example.mycnblog_ssm.mapper.UserMapper;
import com.example.mycnblog_ssm.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int add(String username,String password){
        return userMapper.add(username,password);
    }

    public UserInfo login(String username,String password){
        return userMapper.login(username,password);
    }

    public UserInfo getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
