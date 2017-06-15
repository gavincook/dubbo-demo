/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;

import me.gavincook.dubbo.api.Bank;
import org.springframework.stereotype.Component;

/**
 * @author Gavincook
 * @version 1.0.0
 */
@Component
public class ATM {

    @Reference(check = false)
    private Bank bank;


    public Double getBalance(){
        return bank.getBalance(null);
    }


}