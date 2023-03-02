package com.example.examinedemo.entity;

public enum ExceptionResultEnum {

    SUCCESS("0","成功"),

    SERVER_ERROR("1002","服务异常"),

    PARAMETER_ERROR("1004","参数不合法"),

    FILE_ERROR("1003","文件不存在"),

    FAILED("1001","失败");

    private String Code;
    private String Message;

    ExceptionResultEnum(String Code,String Message){
        this.Code = Code;
        this.Message = Message;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
