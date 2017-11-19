package com.xph.api.user;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication app=new SpringApplication(UserApplication.class);
        app.run(args);
    }
}
