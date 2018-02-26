package com.xphsc.api.user.model.request;


import io.swagger.annotations.ApiModelProperty;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

public class BaseDTO {
    @ApiModelProperty("当前页")
    private Integer pageNum=1;
    @ApiModelProperty("当前页显示数")
    private Integer pageSize=10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
