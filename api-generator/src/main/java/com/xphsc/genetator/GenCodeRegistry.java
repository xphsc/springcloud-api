package com.xphsc.genetator;

import com.github.xphsc.util.StringUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${huipei.x} on 2016/8/8.
 */
@Slf4j
public class GenCodeRegistry {


    public static void genModelAndMapper(String tableName) {
        Context context = new Context(ModelType.FLAT);
        context.setId("xphsc");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(ProjectConstant.JDBC_URL);
        jdbcConnectionConfiguration.setUserId(ProjectConstant.JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(ProjectConstant.JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(ProjectConstant.JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", ProjectConstant.MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(CodeGenerator.PROJECT_PATH + ProjectConstant.JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(ProjectConstant.MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(CodeGenerator.PROJECT_PATH + ProjectConstant.RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mappers");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(CodeGenerator.PROJECT_PATH + ProjectConstant.JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(ProjectConstant.MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        tableConfiguration.setDomainObjectName(modelNameUpperCamel(tableName));
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<String>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }

        String modelName = modelNameUpperCamel(tableName);
        log.info(modelName + ".java 生成成功");
        log.info(modelName + "Mapper.java 生成成功");
        log.info(modelName + "Mapper.xml 生成成功");
    }

    public static void genService(String tableName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", ProjectConstant.DATE);
            data.put("author", ProjectConstant.AUTHOR);
            String modelNameUpperCamel = modelNameUpperCamel(tableName);
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel",  modelNameAllLowerCase(tableName));
            data.put("basePackage", CodeGenerator.PROIECT_PACKAGE);
            data.put("baseMapperPackage", ProjectConstant.BASE__MAPPER_PACKAGE);

            File file = new File(CodeGenerator.PROJECT_PATH + ProjectConstant.JAVA_PATH + ProjectConstant.PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            log.info(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(CodeGenerator.PROJECT_PATH + ProjectConstant.JAVA_PATH + ProjectConstant.PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            log.info(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }



    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(ProjectConstant.TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    private static String tableNameConvertLowerCamel(String tableName) {
        StringBuilder result = new StringBuilder();
        if (tableName != null && tableName.length() > 0) {
            tableName = tableName.toLowerCase();//兼容使用大写的表名
            boolean flag = false;
            for (int i = 0; i < tableName.length(); i++) {
                char ch = tableName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        String camel = tableNameConvertLowerCamel(tableName);
        return camel.substring(0, 1).toUpperCase() + camel.substring(1);

    }


    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }



    private static String modelNameUpperCamel(String tableName){
        String modelName="";
        String modelNameUpperCamel="";
        if(tableName.contains("_")){
            modelName= StringUtil.substringAfter(tableName, "_");
             modelNameUpperCamel = tableNameConvertUpperCamel(modelName);
        }else{
            modelNameUpperCamel=StringUtil.toCapitalizeCamelCase(tableName);
        }
        return modelNameUpperCamel;
    }

    private static String modelNameAllLowerCase(String tableName){
        String modelName= modelNameUpperCamel(tableName);
        modelName=StringUtil.substring(modelName, 0, 1).toLowerCase() + StringUtil.substring(modelName,1);
        return modelName;
    }


}
