package com.xphsc.api.frame.config;

import com.github.xphsc.json.JSONObject;
import com.xphsc.api.frame.common.Response;
import com.xphsc.api.frame.common.ResponseCode;
import com.xphsc.api.frame.common.exception.ApiException;
import com.xphsc.api.frame.common.exception.ParamValidException;
import com.xphsc.api.frame.common.util.ContextHolderUtil;
import org.apache.log4j.LogManager;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private Logger logger = LogManager.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Object messageNotReadable(HttpMessageNotReadableException exception){
        JSONObject jsonpObject= new JSONObject();
        jsonpObject.put("code", ResponseCode.BAD_REQUEST);
        jsonpObject.put("msg", exception.getMessage());
        return jsonpObject;
    }


    @ExceptionHandler(ParamValidException.class)
    public Object ParamValidException(ParamValidException exception){
        JSONObject jsonpObject= new JSONObject();
        jsonpObject.put("code", ResponseCode.PARAM_ERROR.getCode());
        jsonpObject.put("msg", exception.getMessage());
        logger.info("参数摘要：%s"+exception.getMessage());
        return jsonpObject;
    }

    @ExceptionHandler(ApiException.class)
    public Object apiAxception(ApiException exception){
        return  Response.errorResult(ResponseCode.PARAM_ERROR.getCode(), exception.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Object defaultErrorHandler(Exception e)  {
        JSONObject jsonpObject= new JSONObject();
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            jsonpObject.put("code", ResponseCode.NOT_FOUND.getCode());
        } else {
            jsonpObject.put("code",ResponseCode.INTERNAL_SERVER_ERROR.getCode());
        }
        jsonpObject.put("msg",e.getMessage());
        String message = String.format("接口 [%s] 出现异常，异常摘要：%s",
                ContextHolderUtil.getRequest().getRequestURI(),
                e.getMessage());
           logger.error(message,e);
        return jsonpObject;
    }
}
