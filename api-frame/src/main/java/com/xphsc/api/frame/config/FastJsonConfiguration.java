package com.xphsc.api.frame.config;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */

@Configuration
@ConditionalOnClass({FastJsonHttpMessageConverter4.class})
@ConditionalOnProperty(
        name = {"spring.http.converters.preferred-json-mapper"},
        havingValue = "fastjson",
        matchIfMissing = true
)
public class FastJsonConfiguration {

    protected static class FastJson4HttpMessageConverterConfiguration {
        @Bean
        @ConditionalOnMissingBean({FastJsonHttpMessageConverter4.class})
        public FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter() {
            FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
            FastJsonConfig fastJsonConfig = new FastJsonConfig();
            fastJsonConfig.setSerializerFeatures(
                    SerializerFeature.PrettyFormat
            );
            converter.setFastJsonConfig(fastJsonConfig);
            return converter;
        }
    }
}

