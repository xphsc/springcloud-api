## api-generator
## 概述
  ```代码生成器 api-generatorr进行配置，主要是JDBC，因为要根据表名来生成代码
  ```

## 开发建议
 ```
  a. JDBC配置 在ProjectConstant项目常量，请修改为你项目的实际配置
  b. Mapper插件基础接口的完全限定名 在ProjectConstant.MAPPER_INTERFACE_REFERENCE
  c.根据项目修改包名路径 在CodeGenerator类中PROIECT_PACKAGE静态常量修改修改包名，比如【com.xphsc.api.user】
  d.根据项目修改项目在硬盘上的基础路径 在CodeGenerator类中PROJECT_PATH静态常量修改代码生成器项目名称
  ，比如【ProjectConstant.getFilePath("api-generator")+"/api-user"】，
   api-generator代码生成器项目名称，api-user生成代码项目名称
  e.生成service或controller层模板位置(可选)
  在ProjectConstant项目常量类中的TEMPLATE_FILE_PATH静态常量
  比如【ProjectConstant.getFilePath("api-generator")+"/api-generator"+ "\\src\\main\\resources\\generator\\template";】//模板位置
  ```