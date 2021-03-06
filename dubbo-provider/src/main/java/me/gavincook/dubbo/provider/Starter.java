/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;

/**
 * @author Gavincook
 * @version 1.0.0
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "me.gavincook")
public class Starter {

    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

    /**
     * 注册dubbo注解扫描特性【使用注解必须】
     * @return
     */
    @Bean
    public AnnotationBean annotationBean(){
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage("me.gavincook");
        return annotationBean;
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

    public static void main(String[] args) throws InterruptedException {
        logger.debug("start...");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new SpringApplicationBuilder(Starter.class).web(false).run();
        countDownLatch.await();
    }

}
