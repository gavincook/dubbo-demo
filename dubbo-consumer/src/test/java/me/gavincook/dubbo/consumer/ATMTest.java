/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.EchoService;

import me.gavincook.dubbo.api.Bank;
import me.gavincook.dubbo.api.model.Card;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Gavincook
 * @version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Starter.class)
public class ATMTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Reference(check = true, version = "2.0")
    private Bank icbc;

    @Reference(check = true, version = "1.0", validation = "true")
    private Bank ccb;

    @BeforeClass
    public static void init(){
        String resolveFile = ATMTest.class.getClassLoader().getResource("dubbo-resolve.properties").getFile();
        System.setProperty("dubbo.resolve.file", resolveFile);
    }

    /**
     * 服务接口调用测试
     */
    @Test
    public void testGetBalance(){
        ATM atm = context.getBean(ATM.class);
        assert atm.getBalance() > 0;
    }

    /**
     * 多版本测试
     */
    @Test
    public void testMultiVersion(){
       assert  "ICBC".equals(icbc.getName());
       assert  "CCB".equals(ccb.getName());
    }

    /**
     * 回声测试
     */
    @Test
    public void testEcho(){
        String result = (String)((EchoService)icbc).$echo("OK");
        assert "OK".equals(result);
    }

    /**
     * 参数验证
     * <p>
     *     其中icbc为服务端校验，ccb为客户端校验
     * </p>
     */
    @Test
    public void testValidate(){
        Card card = new Card();
        card.setNumber("12345678901234567");
        card.setUserName("GavinCook");
        icbc.openAccount(card);
        ccb.openAccount(card);
    }

    /**
     * 分场景验证，其中{@link Bank#closeAccount(Card)}只需要卡号
     * {@link Bank#openAccount(Card)} 需要卡号和用户名
     */
    @Test
    public void testValidateInDiffGroups(){
        Card card = new Card();
        card.setNumber("12345678901234567");
        assert icbc.closeAccount(card);

        card.setUserName("GavinCook");
        assert icbc.openAccount(card);
    }
}