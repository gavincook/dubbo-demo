/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.service.EchoService;
import com.alibaba.dubbo.rpc.service.GenericService;

import me.gavincook.dubbo.api.Bank;
import me.gavincook.dubbo.api.model.Card;

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

    /**
     * 泛化调用，因为一个标识的服务只能有一种类型，因此这里使用与前面不同的版本。
     * <p>
     *  服务标识：reference.group() + "/" + interfaceName + ":" + reference.version()，具体参见{@link com.alibaba.dubbo.config.spring.AnnotationBean#refer(Reference, Class)}
     * </p>
     */
    @Reference(version = "3.0", generic = true, interfaceClass = Bank.class)
    private GenericService genericService;

    /**
     * 访问服务端泛化服务实现，注意reference的url必须带上generic=true，详见dubbo-resolve.properties
     */
    @Reference(interfaceClass = Bank.class, version = "4.0")
    private Bank genericBank;

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

    /**
     * 泛化调用远端正常服务，可用于编写通用的测试工具。
     * <p>
     *     这里用map的参数去调用远端的需要card参数的接口
     * </p>
     * <p>
     *     可用于实现统一的服务测试框架，如配置好应用信息、注册中心信息，然后可指定接口和方法、参数来调用目标接口
     * </p>
     */
    @Test
    public void testGenericInvoke(){
        Map<String, Object> card = new HashMap<>();
        card.put("userName", "GavinCook");
        card.put("number", "12345678901234567");

        assert (Boolean)genericService.$invoke("openAccount",
            new String[]{"me.gavincook.dubbo.api.model.Card"}, new Object[]{card});
    }

    /**
     * 服务端泛化接口测试，服务端的GenericBank是泛化服务暴露，简单的返回方法名。
     */
    @Test
    public void testInvokeGeneric() {
        Card card = new Card();
        card.setNumber("12345678901234567");
        //对于基础类型直接传递，对于其他对象会序列化为List或者Map
        assert Boolean.valueOf(genericBank.openAccount(card));
    }
}