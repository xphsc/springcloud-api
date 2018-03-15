package com.xphsc.api.admin.feign;

import com.github.xphsc.http.Response;
import com.xphsc.api.admin.service.UserHystrixService;
import com.xphsc.api.admin.service.impl.UserHystrixServcieImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@FeignClient(name = "user",fallback = UserHystrixServcieImpl.class)
public interface UserFeign {
    @RequestMapping(value = "/listAllUser",method = RequestMethod.GET)
    public Response listAllUser();
}
