/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author tanghong.th
 * @version $Id: ATMTest.java, v 0.1 2017-06-14 下午5:06 tanghong.th Exp $$
 */
public class ATMTest {

    @Test
    public void testGetBalance(){
        String resolveFile = ATMTest.class.getClassLoader().getResource("dubbo-resolve.properties").getFile();
        System.setProperty("dubbo.resolve.file", resolveFile);
        ConfigurableApplicationContext context = SpringApplication.run(Starter.class);
        ATM atm = context.getBean(ATM.class);
        assert atm.getBalance() > 0;
    }

}