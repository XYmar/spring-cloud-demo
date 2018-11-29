# spring-cloud-demo
spring-cloud-demo
## 工程说明
- spring-cloud-config-server（8501端口）：Spring Cloud 中的统一配置中心服务器；
- spring-cloud-user-service（5001端口）：用户相关服务提供者；

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