# spring-cloud-demo
spring-cloud-demo

## 疑问点
- @Qualifier注解是什么作用？

## 依赖的外部服务
- Consul服务注册与发现服务器
- MySQL关系型数据库

## 工程说明
- spring-cloud-config-server（7000端口）：Spring Cloud 中的统一配置中心服务器；
- spring-cloud-user-service（5001端口）：用户、角色相关服务提供者；
- spring-cloud-user-consumer（6001端口）：用户、角色相关服务消费者
- spring-cloud-authorization-server（7001端口）：授权服务器
- spring-cloud-api-gateway(端口8080)：微服务API网管服务器-Spring Cloud Gateway版本
- spring-cloud-zuul-server(端口8080)：微服务API网管服务器-Zuul版本

## spring-cloud-config-server
1. 在配置文件中配置监听端口及应用名称；
    ```
    server.port=8501
    spring.application.name=spring-cloud-config-server
    ```
2. 修改配置文件已读取本地配置文件，并指定配置文件发现位置:resources/config；
    ```
    spring.profiles.active=native
    spring.cloud.config.server.native.search-locations=classpath:/config
    ```
3. 修改配置文件建立默认的用户名和密码；
    ```
    spring.security.user.name=rengu
    spring.security.user.password=rengu123456
    ```
4. 在启动类上添加注解,启用服务注册及配置服务器功能；
    ```
    @EnableDiscoveryClient
    @EnableConfigServer
    ```
5. 按照{application}-{profile}.properties的格式保存配置文件,其中application部分对应于spring.application.name，profile对应于spring.cloud.config.profile；

## spring-cloud-user-service
1. 添加bootstrap.properties配置文件（其优先级高于application.properties），并删除原有的application.properties；
    ```
    spring.application.name=spring-cloud-user-service
    #配置服务器链接配置
    spring.cloud.config.uri=http://127.0.0.1:8501
    #获取的配置文件版本，可通过lable和profile区分
    spring.cloud.config.profile=dev
    #链接配置服务器时使用的用户名及密码
    spring.cloud.config.username=rengu
    spring.cloud.config.password=rengu123456
    ```
2. 在启动类上添加注解,启用服务注册及配置服务器功能；
    ```
    @EnableDiscoveryClient
    ```
3. 读取配置文件中的内容可以通过在属性上添加@Value注解的方式
    ```
    @Value("${config.defaultAdminRoleName}")
    ```  

## spring-cloud-user-consumer 
1. 基础配置同spring-cloud-user-service工程;
2. 在启动类上添加注解开启Feign功能
    ```
    @EnableFeignClients
    ```
3. 在配置文件中添加断路器相关配置；
    ```
    #在Feign中启用hystrix断路器
    feign.hystrix.enabled=true
    ```
3. 创建FeignClient接口，并在接口上添加注解，其中value参数为在服务注册中心上注册的服务Id，fallback为实现该接口的断路器处理方法
    ```
    @FeignClient(value = "spring-cloud-user-service", fallback = UserHystrix.class)
    ```

## spring-cloud-zuul-server
1. 修改配置文件，在配置文件中添加路由表；
    ```
    #配置路由服务-Oauth2认证服务
    #path字段用来描述路径
    zuul.routes.auth.path=/auth/**
    #sensitiveHeaders用来添加转发请求的过滤器
    zuul.routes.auth.sensitiveHeaders=
    #service-id用描述转发的服务
    zuul.routes.auth.service-id=spring-cloud-authorization-server
    ```
2. 在启动类上添加注解开启Zuul功能
    ```
    @EnableZuulProxy
    ```