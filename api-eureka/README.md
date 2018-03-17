## api-eureka
## 概述
 ``` eureka 注册中心
 ```
## 开发建议
 ```
  服务注册端
  a. 注册中心需要的maven包
     		<dependency>
     			<groupId>org.springframework.cloud</groupId>
     			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
     		</dependency>
  b. application.xml或bootstrap.yml文件配置信息
  server:
    port: 8761
  eureka:
    instance:
      hostname: eurekaservicer
    client:
      registerWithEureka: false //表示示是否将自己注册到Eureka Server，默认为true。
      fetchRegistry: false  //表示是否从Eureka Server获取注册信息，默认为true
      serviceUrl:
        defaultZone: http://eurekaservicer:${server.port}/eureka/  //设置与Eureka Server交互的地址，查询服务和注册服务都需要依赖这个地址。默认是http://localhost:8761/eureka ；多个地址可使用 , 分隔。
  服务客户端
  a.要注册的客户端的maven包
  <dependency>
  	<groupId>org.springframework.cloud</groupId>
  	<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  b.application.xml文件配置信息
  spring.application.name=spring-cloud-producer //服务名称
  server.port=8081
  eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
  c.@EnableDiscoveryClient 开始注解，注册到到服务端

服务注册端集群配置
a.主要是在application.xml或bootstrap.yml进行配置
spring:
  application:
    name: server-eureka
spring:
  profiles: eureka1                                # 指定profile=eureka1
server:
  port: 8761
eureka:
  instance:
    hostname: eureka1                               # 指定当profile=eureka1时，主机名是peer1
    statusPageUrlPath: /info
    healthCheckUrlPath: /health
  client:
    register-with-eureka: true
    #配置服务注册中心是否以自己为客户端进行注册(配置false)
    fetch-registry: true
    #是否取得注册信息(配置false)
    serviceUrl:
      defaultZone: http://eureka2:8762/eureka/      # 将自己注册到peer2这个Eureka上面去


spring:
  profiles: eureka2
server:
  port: 8762
eureka:
  instance:
    hostname: eureka2
    statusPageUrlPath: /info
    healthCheckUrlPath: /health
  client:
    register-with-eureka: true
    #配置服务注册中心是否以自己为客户端进行注册(配置false)
    fetch-registry: true
    #是否取得注册信息(配置false)
    serviceUrl:
      defaultZone: http://peer1:8761/eureka/