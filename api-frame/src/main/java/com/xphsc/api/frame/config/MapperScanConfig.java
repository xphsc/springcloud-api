package com.xphsc.api.frame.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

@Configuration
@MapperScan(basePackages = "com.xphsc.api.*.mapper",sqlSessionFactoryRef = "sqlSessionFactory")
public class MapperScanConfig {
}
