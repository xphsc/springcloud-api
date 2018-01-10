# springcloud-api
集成的框架有spring cloud，spring boot，spring data jpa,
api文档工具Swagger的使用
mybatis.Mapper插件,pagehelper分页插件
api-eureka注册中心
api-config配置中心
api-monitor监控中心
api-frame 公共配置
api-gateway 网关
api-user 用户管理服务
使用双数据源的使用

此项目可作为spring cloud脚手架 开发使用

开发建议

表名，建议使用小写，多个单词使用下划线拼接
Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加@Transient注解，详情见通用Mapper插件文档说明
建议业务失败直接使用ApiException("message")抛出，由统一异常处理器来封装业务失败的响应结果，比如throw new ServiceException("该手机号已被注册")，会直接被封装为{"code":400,"message":"该手机号已被注册"}返回，无需自己处理，尽情抛出
需要工具类的话建议先从guava中找，实在没有再造轮子或引入类库，尽量精简项目
开发规范建议遵循阿里巴巴Java开发手册（最新版下载)
建议在公司内部使用ShowDoc、SpringFox-Swagger2 、RAP等开源项目来编写、管理API文档

技术选型&文档
SpringBoot 快速的java开发框架，大大提高程序员的开发效率
MyBatis PageHelper分页插件（查看官方中文文档）


小弟才疏学浅
望能和各位爱好技术伙伴相互学习与探讨
qq群593802274
