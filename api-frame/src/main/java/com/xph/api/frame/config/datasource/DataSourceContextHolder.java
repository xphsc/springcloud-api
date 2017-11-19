package com.xph.api.frame.config.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** 动态获取数据源，本地线程变量
 * Created by ${huipei.x} on 2016/8/8.
 * qq群593802274
 */
public class DataSourceContextHolder {

    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> dbContext = new ThreadLocal<>();
    private static final Set<String> supportedDbKeySet = new HashSet<>();
    public static void appendDbKey2Set(String... dbKey){
        supportedDbKeySet.addAll(Arrays.asList(dbKey));
    }

    public static String getCurrentDbKey(){
        return dbContext.get();
    }
    public static void initDbContext(String dbKey){
        if(!isValid(dbKey)) {
            dbKey = DbKey.READ;
            log.warn("dbKey[{}]不合法,初始化默认数据源=>{}",dbKey, DbKey.READ);
        }
        dbContext.set(dbKey);
        log.info("use db [{}]",dbKey);
    }

    public static void destroyDbContext(){
        dbContext.remove();
    }

    private static boolean isValid(String dbKey){
        return supportedDbKeySet.contains(dbKey);
    }
}
