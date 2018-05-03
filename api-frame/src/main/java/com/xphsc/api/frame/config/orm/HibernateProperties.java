package com.xphsc.api.frame.config.orm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vrv on 2018/4/8.
 */
@Configuration
@ConfigurationProperties(prefix = "spring.jpa.hibernate")
public class HibernateProperties {
        private String dialect;
        private  String ddlAuto;
        private  String showSql;
        private  String formatSql;
        private  String currentSessionContextClass;
    private  String packagesToScan;
    private  String mappingResources;
    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getDdlAuto() {
        return ddlAuto;
    }

    public void setDdlAuto(String ddlAuto) {
        this.ddlAuto = ddlAuto;
    }

    public String getShowSql() {
        return showSql;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }

    public String getFormatSql() {
        return formatSql;
    }

    public void setFormatSql(String formatSql) {
        this.formatSql = formatSql;
    }

    public String getCurrentSessionContextClass() {
        return currentSessionContextClass;
    }

    public void setCurrentSessionContextClass(String currentSessionContextClass) {
        this.currentSessionContextClass = currentSessionContextClass;
    }

    public String getPackagesToScan() {
        return packagesToScan;
    }

    public void setPackagesToScan(String packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    public String getMappingResources() {
        return mappingResources;
    }

    public void setMappingResources(String mappingResources) {
        this.mappingResources = mappingResources;
    }
}
