package com.xphsc.api.user.controller;



import com.github.pagehelper.PageInfo;
import com.xphsc.api.frame.common.Response;
import com.xphsc.api.user.model.User;
import com.xphsc.api.user.model.request.BaseQuery;
import com.xphsc.api.user.repository.dao.UserRepository;
import com.xphsc.api.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@RestController
@RequestMapping("/")
@Api(tags = "/user",description="user服务测试")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @ApiOperation(value="查询" )
    @GetMapping("find")
    public Object find(){
        return userRepository.findAll();
    }

    @ApiOperation(value="用户查询列表" )
    @PostMapping("listUser")
    public Response listUser(BaseQuery baseQuery){
        PageInfo<User> pageInfo= userService.listUser(baseQuery);
        return Response.convertResult(pageInfo.getList(),
                pageInfo.getPageNum(),
                pageInfo.getPageSize(),
                pageInfo.getTotal());
    }
}
