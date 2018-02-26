package com.xphsc.api.frame.common.util;

import com.github.pagehelper.PageInfo;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public  class PageInfoHelper {

    public static  PageInfo getPageInfo(PageInfo orig,PageInfo target){
        target.setTotal(orig.getTotal());
        target.setPages(orig.getPages());
        target.setPageNum(orig.getPageNum());
        target.setPageSize(orig.getPageSize());
        return  target;
    }
}
