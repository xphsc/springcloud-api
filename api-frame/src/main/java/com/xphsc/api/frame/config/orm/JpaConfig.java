package com.xphsc.api.frame.config.orm;

import com.vrv.vap.base.impl.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by ${huipei.x} on 2018/4/9.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactory",
        transactionManagerRef="transactionManager",
        repositoryBaseClass = BaseRepositoryImpl.class,
        basePackages= {"com.vrv.vap.*.repository.dao" })
@EnableConfigurationProperties(HibernateProperties.class)
public class JpaConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan(new String[] { hibernateProperties.getPackagesToScan() });
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());

        return factoryBean;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }


    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",hibernateProperties.getDialect());
        properties.put("hibernate.show_sql", hibernateProperties.getShowSql());
        properties.put("hibernate.format_sql", hibernateProperties.getFormatSql());
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }
}
