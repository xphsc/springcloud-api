package com.xphsc.api.frame.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

public abstract class BaseService<T>  {

    @Autowired
    protected BaseMapper<T> baseMapper;

    private Class<T> modelClass;

    public BaseService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public T findById(Integer id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public List<T> findAll() {
        return baseMapper.select(null);
    }


    public T findOne(T record) {
        return baseMapper.selectOne(record);
    }


    public List<T> findByids(String ids) {
        return baseMapper.selectByIds(ids);
    }

    public List<T> findListByWhere(T record) {
        return baseMapper.select(record);
    }

    public List<T> findByExample(Example example) {
        return baseMapper.selectByExample(example);
    }

    public PageInfo<T> findPageExample(Integer page, Integer rows, Example example) {
        PageHelper.startPage(page, rows);
        List<T> list = this.findByExample(example);
        return new PageInfo<T>(list);
    }

    public PageInfo<T> findPageListByWhere(Integer page, Integer rows, T record) {
        PageHelper.startPage(page, rows);
        List<T> list = this.findListByWhere(record);
        return new PageInfo<T>(list);
    }

    public Integer save(T record) {
        return baseMapper.insert(record);
    }

    public Integer save(List<T> record) {
        return baseMapper.insertList(record);
    }

    public Integer saveSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    public Integer update(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }

    public Integer updateSelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }
    public Integer updateSelectiveByExample(T record, Example example) {
        return baseMapper.updateByExampleSelective(record, example);
    }
    public Integer deleteById(Integer id) {
        return baseMapper.deleteByPrimaryKey(id);
    }


    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return baseMapper.deleteByExample(example);
    }

    public Integer deleteByIds(String ids) {
        return baseMapper.deleteByIds(ids);
    }

    public Integer deleteByWhere(T record) {
        return baseMapper.delete(record);
    }

    public Integer count(T record) {
        return baseMapper.selectCount(record);
    }

    public Integer count(Example example) {
        return baseMapper.selectCountByExample(example);
    }

}
