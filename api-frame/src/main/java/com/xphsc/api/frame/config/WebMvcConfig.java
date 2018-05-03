package com.xphsc.api.frame.config;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.*;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;


/**
 * Created by ${huipei.x} on 2017-9-1.
 */

@Configuration
@ComponentScan(value = "com.ovit.nswy", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class, Controller.class, ControllerAdvice.class, RestControllerAdvice.class})})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private Logger log = LogManager.getLogger(this.getClass());

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("1024000KB");
        factory.setMaxRequestSize("1024000KB");
        return factory.createMultipartConfig();
    }


    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd HH:mm:ss");
        dateFormatter.setLenient(true);
        registry.addFormatter(dateFormatter);
    }


}

