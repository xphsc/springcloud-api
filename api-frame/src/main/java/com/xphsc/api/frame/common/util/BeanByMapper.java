package com.xphsc.api.frame.common.util;

import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;

/**
 * Created by ${huipei.x} on 2018/4/12.
 */
public class BeanByMapper {

    public   <T> T  getCopyByRefMapper(Class origClass,Object orig, Class<T> targetClass){
        targetClass=getMapper().mapClass(origClass.getClass(), targetClass.getClass())
                .register()
                .map(orig, targetClass.getClass());
       return (T) targetClass;
    }

    public   <T> T  getCopyByMapper(Class origClass,Object orig, Class<T> targetClass){
        targetClass=getMapper().mapClass(origClass.getClass(), targetClass).registerAndMap(orig, targetClass.getClass());
        return (T) targetClass;
    }


    public Mapper getMapper(){
        Mapper mapper = MapperFactory.getCopyByRefMapper();
        return mapper;
    }


}
