/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;

import me.gavincook.dubbo.api.Bank;
import me.gavincook.dubbo.api.model.Card;

/**
 * 建行
 * @author Gavincook
 * @version 1.0.0
 */
@Service(version = "1.0")
public class CCB implements Bank {

    public CCB(){
        System.out.println();
    }

    @Override
    public Double getBalance(Long userId) {
       return 80D;
    }

    @Override
    public String getName() {
        return "CCB";
    }

    @Override
    public boolean openAccount(Card card) {
        return true;
    }

    @Override
    public boolean closeAccount(Card card) {
        return true;
    }

}