package com.xph.api.gateway;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */

@SpringBootApplication
@EnableCircuitBreaker
@EnableDiscoveryClient
//@EnableSidecar
@EnableZuulProxy
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }


}