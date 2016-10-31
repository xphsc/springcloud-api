package com.xph.api.user.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
@Component
@Aspect
@Order(1)
public class TarDataSourceAspect {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 定义
     */
    @Pointcut(value = "execution(public * com.jht.openapi.service..*.*(..))")
    public void anyMethod(){}//定义一个切入点

 //   @Before("anyMethod()")
 @Around(
            value = "anyMethod()"
    )
    public Object dataSourceChange(/*JoinPoint joinPoint*/ ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();
        if (method.startsWith("add")
                || method.startsWith("create")
                || method.startsWith("save")
                || method.startsWith("edit")
                || method.startsWith("update")
                || method.startsWith("del")
                || method.startsWith("insert")
                || method.startsWith("delete")
                || method.startsWith("remove")) {
            DataSourceContextHolder.initDbContext(DbKey.WRITE);
            Object result = proceedingJoinPoint.proceed();
            DataSourceContextHolder.destroyDbContext();
            return result;
        } else {
            DataSourceContextHolder.initDbContext(DbKey.READ);
            Object result = proceedingJoinPoint.proceed();
            DataSourceContextHolder.destroyDbContext();
            return result;
        }

    }

    // 抛出Exception之后被调用
    @AfterThrowing("anyMethod()")
    public void afterThrowing(JoinPoint joinPoint) throws Throwable {
        DataSourceContextHolder.initDbContext(DbKey.READ);
        logger.info("抛出Exception之后被调用:切入点: " + joinPoint.getSignature().getDeclaringTypeName() + "类中" + joinPoint.getSignature().getName() + "方法");
        logger.info("出现异常,切换到: default");
    }

}
