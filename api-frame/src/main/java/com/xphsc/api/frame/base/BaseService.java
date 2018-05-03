package com.xphsc.api.frame.base;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * Created by ${huipei.x} on 2018-3-15.
 */
public interface BaseService<T> {
    public T findById(Integer id);

    public List<T> findAll();

    public T findOne(T record);

    public List<T> findByids(String ids);

    public List<T> findListByWhere(T record);

    public List<T> findByExample(Example example);

    public PageInfo<T> findPageExample(Integer page, Integer rows, Example example) ;
    public Integer save(T record);

    public Integer save(List<T> record);

    public Integer saveSelective(T record);

    public Integer update(T record);

    public Integer updateSelective(T record);

    public Integer updateSelectiveByExample(T record, Example example);
    public Integer deleteById(Integer id);

    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) ;

    public Integer deleteByIds(String ids);


    public Integer count(T record);

    public Integer count(Example example);


}
