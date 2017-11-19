package com.xph.api.user.service;

import com.github.pagehelper.PageInfo;
import com.xph.api.user.model.User;
import com.xph.api.user.model.request.BaseQuery;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public interface UserService {

    public PageInfo<User> listUser(BaseQuery baseQuery);
}
