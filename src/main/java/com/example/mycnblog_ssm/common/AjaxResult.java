package com.example.mycnblog_ssm.common;

import java.util.HashMap;

/**
 * 自定义同一返回
 */
public class AjaxResult {
    public static HashMap<String,Object> success(Object data){
        HashMap<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg","");
        result.put("data",data);
        return result;
    }

    /**
     *
     * @param msg
     * @param data
     * @return
     */
    public static HashMap<String,Object> success(String msg,Object data){
        HashMap<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("msg",msg);
        result.put("data",data);
        return result;
    }

    /**
     * 执行失败的返回格式
     * @param code
     * @param msg
     * @return
     */
    public static HashMap<String,Object> fail(int code,String msg){
        HashMap<String,Object> result = new HashMap<>();
        result.put("code",code);
        result.put("msg",msg);
        result.put("data","");
        return result;
    }
}
