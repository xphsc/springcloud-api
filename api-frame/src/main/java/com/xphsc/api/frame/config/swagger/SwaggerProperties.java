package com.xphsc.api.frame.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */


@Configuration
@ConfigurationProperties(prefix = "springfox")
public class SwaggerProperties {
    private String   name;
    private String   title;
    private String   description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
