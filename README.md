# spring-cloud-demo
spring-cloud-demo
## 工程说明
- spring-cloud-config-server：Spring Cloud 中的统一配置中心服务器；

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