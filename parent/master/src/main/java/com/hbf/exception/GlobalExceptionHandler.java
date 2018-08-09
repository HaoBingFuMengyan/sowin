package com.hbf.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 全局捕获异常通知
 * 1.捕获返回json格式
 * 2.捕获返回页面错误
 * Created by haobingfu on 2018/8/8.
 */

@ControllerAdvice(basePackages = "com.hbf")
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> errorResult(){
        Map<String,Object> error = new LinkedHashMap<>();
        error.put("code",404);
        error.put("msg","系统错误，请联系管理员");
        return error;
    }
}
