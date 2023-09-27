package com.example.mycnblog_ssm.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    List<String> excludesPath = Arrays.asList("/**/*.html","/js/**"
            ,"/editor.md/**","/css/**","/img/**","/user/login","/user/reg","/art/detail");
    @Autowired LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       InterceptorRegistration registration =  registry.addInterceptor(loginInterceptor).addPathPatterns();
       registration.addPathPatterns("/**");
       registration.excludePathPatterns(excludesPath);

    }
}
