package com.xphsc.api.frame.config.orm;

import com.github.xphsc.util.FileUtil;
import com.xphsc.api.frame.config.datasource.DynamicDatasource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(HibernateProperties.class)
public class HibernateConfig {

    @Autowired
    private DynamicDatasource dynamicDatasource;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dynamicDatasource);
        factory.setPackagesToScan(new String[]{hibernateProperties.getPackagesToScan()});
        if(FileUtil.exist(hibernateProperties.getMapping())){
            factory.setMappingDirectoryLocations(new PathMatchingResourcePatternResolver().getResources(hibernateProperties.getMapping()));
        }
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",hibernateProperties.getDialect());
        properties.put("hibernate.show_sql", hibernateProperties.getShowSql());
        properties.put("hibernate.format_sql", hibernateProperties.getFormatSql());
      //  properties.put("hibernate.hbm2ddl.auto",hibernateProperties.getDdlAuto());
        return properties;
    }

    @Bean("hibernateTemplate")
    public HibernateTemplate  hibernateTemplate() throws IOException {
        HibernateTemplate template=new HibernateTemplate();
        template.setSessionFactory(sessionFactory().getObject());
        return template;
    }



}
