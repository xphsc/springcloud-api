package com.xphsc.genetator;

import com.github.xphsc.util.StringUtil;
import freemarker.template.TemplateExceptionHandler;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ${huipei.x} on 2016/8/8.
 */
public class CodeGenerator {
    /**
    * PROIECT_PACKAGE 根据项目修改包名路径
    * 比如  com.xphsc.api
    */
    public static final String PROIECT_PACKAGE="com.xphsc.api.user";
    /**
     * 根据项目修改项目在硬盘上的基础路径
     *
     */
    public static final String PROJECT_PATH =ProjectConstant.getFilePath("api-generator")+"/api-user";


    /**
     * 根据项目 genCode("输入表名");
     * @param args
     */
    public static void main(String[] args) {
        genCode(
                "t_dept_copy"
        );
    }

    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            /**
             * 根据需求生成，不需要的注掉，模板有问题的话可以自己修改。
             */
            GenCodeRegistry.genModelAndMapper(tableName);
            GenCodeRegistry.genService(tableName);


        }
    }


}
