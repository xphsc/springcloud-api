package com.xphsc.api.admin.controller;

import com.github.xphsc.http.Response;
import com.xphsc.api.admin.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@RestController
@RequestMapping("/")
public class UserController {
      @Autowired
    private UserFeign userFeign;

    @GetMapping("/listAllUser")
    public Response listAllUser(){
        return userFeign.listAllUser();
    }


}
