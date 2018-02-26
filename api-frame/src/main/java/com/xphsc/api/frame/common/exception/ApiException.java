package com.xphsc.api.frame.common.exception;


import com.xphsc.api.frame.common.ResponseCode;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public class ApiException  extends RuntimeException {
    private ResponseCode code = ResponseCode.INTERNAL_SERVER_ERROR;
    public ApiException(){
        super();
    }

    public ApiException(String msg){
        super(msg);
    }

    public ApiException(Throwable throwable){
        super(throwable);
    }

    public ApiException(ResponseCode code, String msg){
        super(msg);
        this.code = code;
    }

    public ApiException(ResponseCode code, Throwable throwable, String msg){
        super(msg, throwable);
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }
}
