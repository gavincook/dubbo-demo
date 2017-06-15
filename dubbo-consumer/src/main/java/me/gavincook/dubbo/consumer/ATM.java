/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;

import me.gavincook.dubbo.api.Bank;
import org.springframework.stereotype.Component;

/**
 * @author tanghong.th
 * @version $Id: ATM.java, v 0.1 2017-06-14 下午4:48 tanghong.th Exp $$
 */
@Component
public class ATM {

    @Reference(check = false)
    private Bank bank;


    public Double getBalance(){
        return bank.getBalance(null);
    }


}