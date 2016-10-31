package com.xph.api.user.config.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import java.util.Map;

/**实现抽象类 AbstractRoutingDataSource
 * 动态获取datasource
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Primary
@Configuration
public class DynamicDatasource extends AbstractRoutingDataSource {

    @Autowired
    private AbstractDataSourceConfig dataSourceConfig;

    @PostConstruct
    private void initDataSourceMap() {
        Map<Object,Object> dataSourceMap = dataSourceConfig.getDataSourceMap();
        setTargetDataSources(dataSourceMap);
        setDefaultTargetDataSource(dataSourceMap.get(DbKey.READ));
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getCurrentDbKey();
    }
}
