package com.xphsc.api.admin.service.impl;

import com.github.xphsc.http.Response;
import com.xphsc.api.admin.feign.UserFeign;
import com.xphsc.api.admin.service.UserHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Service
@Slf4j
public class UserHystrixServcieImpl implements UserHystrixService,
        UserFeign {

    @Override
    public String getFallbackListUser() {
        log.info("listAllUser方法故障，启用断路器");
        return "listAllUser方法故障，启用断路器";
    }

    @Override
    public Response listAllUser() {
        log.info("listAllUser方法故障，启用断路器");
        return Response.errorResult("listAllUser方法故障，启用断路器");
    }
}
