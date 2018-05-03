package com.xphsc.api.frame.config.orm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by vrv on 2018/4/8.
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(HibernateProperties.class)
public class HibernateConfig {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean("sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan(new String[]{hibernateProperties.getPackagesToScan()});
        factory.setMappingResources(new String[]{hibernateProperties.getMappingResources()});
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",hibernateProperties.getDialect());
        properties.put("hibernate.show_sql", hibernateProperties.getShowSql());
        properties.put("hibernate.format_sql", hibernateProperties.getFormatSql());
        properties.put("hibernate.current_session_context_class", hibernateProperties.getCurrentSessionContextClass());
        properties.put("hibernate.hbm2ddl.auto",hibernateProperties.getDdlAuto());
        return properties;
    }

    @Bean("hibernateTemplate")
    public HibernateTemplate  hibernateTemplate(){
        HibernateTemplate template=new HibernateTemplate();
        template.setSessionFactory(sessionFactory().getObject());
        return template;
    }
}
