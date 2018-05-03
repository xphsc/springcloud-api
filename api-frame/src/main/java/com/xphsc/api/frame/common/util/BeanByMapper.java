package com.xphsc.api.frame.common.util;

import com.baidu.unbiz.easymapper.Mapper;
import com.baidu.unbiz.easymapper.MapperFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 高性能对象拷贝工具（BeanUtils和PropertyUtils性能均不理想，Dozer的性能则更为不好）
 * Created by ${huipei.x} on 2016/8/8.
 */
public class BeanByMapper {

    public static  <T> T  getCopyByRefMapper(Object origClass,Object orig, Class<T> targetClass){
        T entity;
        entity=getMapper().mapClass((Class<Object>) origClass, targetClass)
                .register()
                .map(orig, targetClass);
       return entity;
    }

    public static  <T> T  getCopyByMapper(Object origClass,Object orig, Class<T> targetClass){
        T entity;
        entity=getMapper().mapClass((Class<Object>) origClass, targetClass).registerAndMap(orig, targetClass);
        return  entity;
    }


    public static Mapper getMapper(){
        Mapper mapper = MapperFactory.getCopyByRefMapper();
        return mapper;
    }

    public static <T> List<T> copyByRefListMapper(Class origClass,List orig, Class<T> targetClass) {
        List<T> list = new ArrayList(orig.size());
        Iterator i = orig.iterator();
        while(i.hasNext()) {
            Object object = i.next();
            list.add(getCopyByMapper(origClass,object, targetClass));
        }

        return list;
    }

}
