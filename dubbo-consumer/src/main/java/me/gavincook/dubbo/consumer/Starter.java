/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.config.spring.AnnotationBean;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

/**
 * @author tanghong.th
 * @version $Id: Starter.java, v 0.1 2017-06-14 下午5:08 tanghong.th Exp $$
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "me.gavincook")
public class Starter {

    /**
     * 注册dubbo注解扫描特性【使用注解必须】
     * @return
     */
    @Bean
    public AnnotationBean annotationBean(){
        return new AnnotationBean();
    }

    /**
     * 注册dubbo应用配置【必须】
     * @return
     */
    @Bean
    public ApplicationConfig applicationConfig(){
        ApplicationConfig config = new ApplicationConfig();
        config.setName("dubbo-demo");
        return config;
    }

    /**
     * dubbo注册中心配置【必须】
     * @return
     */
    @Bean
    public RegistryConfig registryConfig(){
        RegistryConfig config = new RegistryConfig();
        config.setAddress("N/A");
        return config;
    }
}