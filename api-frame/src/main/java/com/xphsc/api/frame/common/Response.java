package com.xphsc.api.frame.common;



import com.alibaba.fastjson.annotation.JSONField;
import com.github.xphsc.date.DateFormat;
import com.github.xphsc.json.JSONHelper;
import com.github.xphsc.json.JSONObject;
import com.github.xphsc.mutable.Integers;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public class Response {

    @JSONField(ordinal = 3)
    private Object data ;
    @JSONField(ordinal = 4)
    private Integer total;
    @JSONField(ordinal = 2)
    private Object msg;
    @JSONField(ordinal = 1)
    private int code;
    @JSONField(ordinal = 4)
    private JSONObject page;
    public Response(){}

    public Response(int status,Object msg,Object result) {
        this.setData(getData(result));
        this.setMsg(msg);
        this.setCode(status);

    }

    public Response(int status,Object msg,Object result,long total) {
        this.setData(getData(result));
        this.setMsg(msg);
        this.setCode(status);
        this.setTotal(Integers.toInteger(total));

    }

    public Response(int status, JSONObject page,Object result,long total) {
        this.setData(getData(result));
        this.setPage(page);
        this.setCode(status);
        this.setTotal(Integers.toInteger(total));
    }


    public Response(int status,Object msg) {
        this.setCode(status);
        this.setMsg(msg);

    }



    public static Response successResult() {
        return newInstance(200, ResponseCode.OK.getDesc());
    }
    public static Response successResult(Object result) {
        return newInstance(200, ResponseCode.OK.getDesc(),JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response successResult(Object result ,Integer total) {
        return newInstance(200,ResponseCode.OK.getDesc(), JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY),total);
    }

    public static Response successResult(Object result ,Integer total,String DateFormat) {
        return newInstance(200, ResponseCode.OK.getDesc(), JSONHelper.toJSON(result, DateFormat), total);
    }

    public static Response successResult(int status ,Object result) {
        return newInstance(status,ResponseCode.OK.getDesc(), JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response successResult(Object result,Object msg) {
        return newInstance(200, msg, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response successResult(Object result,String DateFormat) {
        return newInstance(200, ResponseCode.OK.getDesc(), JSONHelper.toJSON(result, DateFormat));
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        return newInstance(200, jsonObject, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY), total);
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,Integer totalPage)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("totalPage",totalPage);
        return newInstance(200,jsonObject,JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY));
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total,String DateFormat)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        return newInstance(200,jsonObject,JSONHelper.toJSON(result, DateFormat),total);
    }

    public static Response convertResult(Object result ,int currentPage,int pageSize ,long total,long totalPage)
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("currentPage",currentPage);
        jsonObject.put("pageSize",pageSize);
        jsonObject.put("totalPage",totalPage);
        return newInstance(200,jsonObject, JSONHelper.toJSON(result, DateFormat.DATE_FORMAT_DAY),total);
    }

    public static Response errorResult(Object msg) {
        return newInstance(ResponseCode.INTERNAL_SERVER_ERROR.getCode(),msg);

    }

    public static Response errorResult(int status,Object msg) {
        return newInstance(status,msg);
    }
    public static Response newInstance(int status,Object msg,Object result) {
        return new Response(status,msg,result);
    }

    public static Response newInstance(int status,Object msg,Object result,long total) {
        return new Response(status,msg,result,total);
    }
    public static Response newInstance(int status, JSONObject page,Object result,long total) {
        return new Response(status,page,result,total);
    }
    public static Response newInstance(int status,Object msg) {
        return new Response(status,msg);
    }

    private Object getData(Object result){
        Object data=null;
        data=JSONHelper.parse(result.toString());
        return data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getPage() {
        return page;
    }

    public void setPage(JSONObject page) {
        this.page = page;
    }
}
