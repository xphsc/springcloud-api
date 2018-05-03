## springcloud-api
## 概述
  ```集成的框架有spring cloud，spring boot，spring data jpa,
 - api文档工具Swagger的使用
 - mybatis.Mapper插件,pagehelper分页插件
 - api-eureka注册中心
 - api-config配置中心
 - api-monitor监控中心
 - api-frame 公共配置
 - api-gateway 网关
 - api-dashboard 负载熔断监控服务
 - api-zipkin  链路跟踪服务
 - api-user 用户管理服务
 - api-admin 资源管理服务
 - api-generator 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码,根据需求在```CodeGenerator.genCode(tableName)```方法中自己选择，
 代码模板可根据实际项目的需求来扩展
 -使用双数据源的使用
```
## 此项目可作为spring cloud脚手架 开发使用

## 开发建议
 ```
~~~
   请遵循项目规范
    -映射数据库对象（DO) 放在业务服务项目包目录下 比如com.vrv.vap.业务服务名.model
      Model内成员变量建议与表字段数量对应，
    - 表名，建议使用小写，多个单词使用下划线拼接
    -数据传输层对象（DTO,VO,BO）如需扩展成员变量（比如连表查询）建议创建DTO，
    -如果数据层使用mybatis可以使用代码生成器（vap-generator）使用方式vap-generator项目下的md文件已详细说明
    -mybatis使用Mapper插件，详情见通用Mapper插件文档说明
    -大量工具都在eglsc-helper中
      对象拷贝工具BeanByRefMapper
    -如果数据层使用jpa 请遵循jpa规范，使用spring-data-jpa，映射数据库对象（DO)model 在实体类上增加@Entity
    @Table,业务数据层包规范om.vrv.vap.业务服务名.repository.dao
    dao层接口需继承BaseRepository，
    -如果数据层使用hibernate 请遵循规范
     建议使用注解对应数据库映射
      如果数据库映射使用XML，请把生成的*.hbm.xml 放在业务服务resources下的mapping下
    -hibernate,jpa，尽量不要建外键关联，不要用关联关系映射，会增加复杂度
    -建议在公司内部使用SSpringFox-Swagger2开源项目来编写、管理API文档
     目前已集成Swagger
~~~
```
## 技术选型&文档
 ```
   spring cloud快速微服务开发框架
    SpringBoot 快速的java开发框架，大大提高程序员的开发效率
    MyBatis PageHelper分页插件（查看官方中文文档）
```

小弟才疏学浅
望能和各位爱好技术伙伴相互学习与探讨
qq群593802274
