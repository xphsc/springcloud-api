package com.xphsc.api.frame.config.orm;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.jpa.hibernate")
public class HibernateProperties {
        private String dialect;
        private  String ddlAuto;
        private  String showSql;
        private  String formatSql;
        private  String currentSessionContextClass;
        private  String packagesToScan;
        private  String mapping;

}
