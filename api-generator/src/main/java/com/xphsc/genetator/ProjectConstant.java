package com.xphsc.genetator;

import com.github.xphsc.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ${huipei.x} on 2016/8/8.
 * 项目常量
 */
public final class ProjectConstant {
    /**
     * 项目基础包名称，根据自己公司的项目
     */
    public static final String BASE_PACKAGE = "com.xphsc.api";//项目基础包名称，根据自己公司的项目修�?
    /**
     * baseMapper 包名路径
     */
    public static final String BASE__MAPPER_PACKAGE = "com.xphsc.api.frame.base";
    /**
     *Model所在包
     */
    public static final String MODEL_PACKAGE = CodeGenerator.PROIECT_PACKAGE + ".model";

    /**
     * Mapper所在包
     */
    public static final String MAPPER_PACKAGE = CodeGenerator.PROIECT_PACKAGE + ".mapper";
    /**
     * Service所在包
     */
    public static final String SERVICE_PACKAGE = CodeGenerator.PROIECT_PACKAGE + ".service";
    /**
     * ServiceImpl所在包
     */
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";
    /**
     * Mapper插件基础接口的完全限定名
     */
    public static final String MAPPER_INTERFACE_REFERENCE =BASE__MAPPER_PACKAGE+ ".BaseMapper";

    /**
     * 模板位置
     */
    public static final String TEMPLATE_FILE_PATH = ProjectConstant.getFilePath("api-generator")+"/api-generator"+ "\\src\\main\\resources\\generator\\template";//模板位置

    public static final String JAVA_PATH = "\\src\\main\\java"; //java文件路径
    public static final String RESOURCES_PATH = "\\src\\main\\resources";//资源文件路径

    /**
     * 生成的Service存放路径
     */
    public static final String PACKAGE_PATH_SERVICE = packageConvertPath(ProjectConstant.SERVICE_PACKAGE);
    /**
     * 生成的Service实现存放路径
     */
    public static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(ProjectConstant.SERVICE_IMPL_PACKAGE);
    /**
     * 生成注释
     */
    public static final String AUTHOR = "CodeGenerator";//@author
    public static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
    /**
     * JDBC配置，请修改为你项目的实际配置
     */
    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_mysql";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "root";
    public static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";


    public static String  getFilePath(String  projectName){
        String path= ClassLoader.getSystemResource("").getPath();
        String url= StringUtil.substringBefore(path, projectName);
        return url;
    }
    private static String packageConvertPath(String packageName) {
        return String.format("\\%s\\", packageName.contains(".") ? packageName.replaceAll("\\.", "\\\\") : packageName);
    }

}
