package com.xphsc.api.frame.common;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public enum ResponseCode {

    OK(200,"OK"),
    BAD_REQUEST(400,"ERROR"),
    NOT_FOUND(404,"Not Found"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    PARAM_ERROR(1000,"参数异常");
    private final int code;
    private  final String desc;

    ResponseCode(int code,String desc){
        this.code =code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
