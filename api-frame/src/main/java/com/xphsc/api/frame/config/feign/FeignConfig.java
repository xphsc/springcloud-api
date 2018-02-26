package com.xphsc.api.frame.config.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

public class FeignConfig {
    /**
     * 重写RequestInterceptor，实现客服端请求服务到微服务请求头一致
     * @return
     */
    @Bean
    public RequestInterceptor headerInterceptor() {
        return new HeaderInterceptor();
    }
}
