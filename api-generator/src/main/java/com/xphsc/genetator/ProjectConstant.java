package com.xphsc.genetator;

/**
 * 项目常量
 */
public final class ProjectConstant {
    public static final String BASE_PACKAGE = "com.ovit.nswy.portal";//项目基础包名称，根据自己公司的项目修�?

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".model";//Model�?在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";//Mapper�?在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service�?在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl�?在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";//Controller�?在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".core.Mapper";//Mapper插件基础接口的完全限定名
}
