package com.xphsc.api.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xphsc.bean.BeanByRefMapper;
import com.github.xphsc.collect.Lists;
import com.xphsc.api.frame.base.BaseService;
import com.xphsc.api.frame.common.util.PageInfoHelper;
import com.xphsc.api.user.model.User;
import com.xphsc.api.user.model.request.BaseDTO;
import com.xphsc.api.user.model.response.UserDTO;
import com.xphsc.api.user.repository.dao.UserDao;
import com.xphsc.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {
     @Autowired
    private UserDao userDao;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public PageInfo<UserDTO> listUser(BaseDTO baseDTO){
        PageHelper.startPage(baseDTO.getPageNum(), baseDTO.getPageSize());
        List<User> userList= this.findAll();
        PageInfo<User> pageOrig = new PageInfo(userList);
        List<UserDTO> userDTOList= BeanByRefMapper.copyByRefListMapper(userList,UserDTO.class);
        PageInfo<UserDTO> pageInfo = new PageInfo(userDTOList);
        pageInfo = PageInfoHelper.getPageInfo(pageOrig, pageInfo);
        return pageInfo;
    }

    @Override
    public List<UserDTO> listAllUser(){
        List<UserDTO> userDTOList=Lists.newArrayList();
        List<User> userList = userDao.findAll();
        if(Lists.isNotEmpty(userList)){
            userDTOList= BeanByRefMapper.copyByRefListMapper(userList,UserDTO.class);
        }
        return userDTOList;
    }



}
