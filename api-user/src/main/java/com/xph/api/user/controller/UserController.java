package com.xph.api.user.controller;



import com.xph.api.user.repositories.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */
@RestController
@RequestMapping("/")
@Api(tags = "/user",description="user服务测试")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @ApiOperation(value="查询" )
    @RequestMapping(value = "/find",method = RequestMethod.GET)
    public Object find(){
        return userRepository.findAll();
    }
}
