package com.xph.api.frame.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ${huipei.x} on 2017-9-1.
 */
@Configuration
@MapperScan(basePackages = "com.ovit.nswy.*.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class MapperScanConfig {
}
