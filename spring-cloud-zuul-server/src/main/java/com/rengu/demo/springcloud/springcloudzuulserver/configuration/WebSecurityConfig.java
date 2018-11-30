package com.rengu.demo.springcloud.springcloudzuulserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // 放行用户注册接口
        http.authorizeRequests().antMatchers("/actuator/*").permitAll();
        http.authorizeRequests().antMatchers("/user-api/*").permitAll();
//        http.authorizeRequests().anyRequest().authenticated();
    }
}
