package com.xph.api.frame.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object getBean(String name, Class requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }


    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }


    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    @SuppressWarnings("rawtypes")
    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
