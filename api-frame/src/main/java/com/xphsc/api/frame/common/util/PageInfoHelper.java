package com.xphsc.api.frame.common.util;

import com.github.pagehelper.PageInfo;
import com.github.xphsc.bean.BeanByRefMapper;

import java.util.List;

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


    public static <T > PageInfo<T> getPageInfo(List origList,Class<T> clazz){
        PageInfo pageOrig = new PageInfo(origList);
        List<T> targetList= BeanByRefMapper.copyByRefListMapper(origList, clazz);
        PageInfo<T> pageTarget = new PageInfo(targetList);
        pageTarget.setTotal(pageOrig.getTotal());
        pageTarget.setPages(pageOrig.getPages());
        pageTarget.setPageNum(pageOrig.getPageNum());
        pageTarget.setPageSize(pageOrig.getPageSize());
        return  pageTarget;
    }


}
