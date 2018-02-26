package com.xphsc.api.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xphsc.api.frame.base.BaseService;
import com.xphsc.api.user.model.User;
import com.xphsc.api.user.model.request.BaseQuery;
import com.xphsc.api.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    public PageInfo<User> listUser(BaseQuery baseQuery){
        PageHelper.startPage(baseQuery.getPageNum(), baseQuery.getPageSize());
        return new PageInfo<>( this.findAll());
    }

}
