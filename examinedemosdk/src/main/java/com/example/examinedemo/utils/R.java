package com.example.examinedemo.utils;

import com.example.examinedemo.entity.ExceptionResult;
import com.example.examinedemo.entity.ExceptionResultEnum;

public class R {

    //成功有返回
    public static ExceptionResult success(String action , Object obj){
        ExceptionResult result = new ExceptionResult(ExceptionResultEnum.SUCCESS.getCode(),action + ExceptionResultEnum.SUCCESS.getMessage());
        result.setData(obj);
        return result;
    }

    //成功无返回
    public static ExceptionResult success(String action){
        return success(action,null);
    }

    //服务异常
    public static ExceptionResult serverError(){
        ExceptionResult result = new ExceptionResult(ExceptionResultEnum.SERVER_ERROR.getCode(), ExceptionResultEnum.SERVER_ERROR.getMessage());
        return result;
    }

    //失败有返回
    public static ExceptionResult failed(String action , Object obj) {
        ExceptionResult result = new ExceptionResult(ExceptionResultEnum.FAILED.getCode(), action + ExceptionResultEnum.FAILED.getMessage());
        result.setData(obj);
        return result;
    }

    //失败无返回
    public static ExceptionResult failed(String action){
        return failed(action, null);
    }

    //参数异常
    public static ExceptionResult parameterError(Object obj){
        ExceptionResult result = new ExceptionResult(ExceptionResultEnum.PARAMETER_ERROR.getCode(), ExceptionResultEnum.PARAMETER_ERROR.getMessage());
        result.setData(obj);
        return result;
    }

    //参数异常
    public static ExceptionResult fileIsNotExist(Object obj){
        ExceptionResult result = new ExceptionResult(ExceptionResultEnum.FILE_ERROR.getCode(), ExceptionResultEnum.FILE_ERROR.getMessage());
        result.setData(obj);
        return result;
    }

    //自定义返回
    public static ExceptionResult customReturn(String code , String message , Object obj){
        ExceptionResult result = new ExceptionResult(code, message);
        result.setData(obj);
        return result;
    }
}
