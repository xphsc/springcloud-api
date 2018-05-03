package com.xphsc.api.frame.base;

import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public interface BaseService<T> {

    public Integer save(T record);

    public Integer save(List<T> record);

    public T findById(Integer id);

    public List<T> findAll();

    public T findOne(T record);

    public List<T> findByids(String ids);


    public List<T> findByExample(Example example);

    public PageInfo<T> findPageExample(Integer page, Integer rows, Example example) ;

    public Integer saveSelective(T record);

    public Integer update(T record);

    public Integer updateSelective(T record);

    public Integer updateSelectiveByExample(T record, Example example);
    public Integer deleteById(Integer id);

    public Integer deleteByIds(String ids);

    public Integer count(T record);

    public Integer count(Example example);


}
