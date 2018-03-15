package com.xphsc.api.user.service;


import com.github.pagehelper.PageInfo;
import com.xphsc.api.user.model.request.BaseDTO;
import com.xphsc.api.user.model.response.UserDTO;

import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public interface UserService {

    public PageInfo<UserDTO> listUser(BaseDTO baseDTO);
    public List<UserDTO> listAllUser();


}
