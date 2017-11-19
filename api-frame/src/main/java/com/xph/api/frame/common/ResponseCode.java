package com.xph.api.frame.common;

/**
 *  Created by ${huipei.x} on 2017-10-16.
 */
public enum ResponseCode {

    OK(200,"OK"),
    ERROR(400,"ERROR"),
    NEED_LONGIN(404,"Not Found"),
    ILLEGLE_ARGUMENT(500,"Internal Server Error"),
    PARAM_ERROR(10000,"参数异常");
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
