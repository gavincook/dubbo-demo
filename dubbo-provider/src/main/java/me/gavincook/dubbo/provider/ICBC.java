/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;

import me.gavincook.dubbo.api.Bank;
import me.gavincook.dubbo.api.model.Card;

/**
 * 工行
 * @author Gavincook
 * @version 1.0.0
 */
@Service(version = "2.0", validation = "true")
public class ICBC implements Bank {

    public ICBC() {
        System.out.println();
    }

    @Override
    public Double getBalance(Long userId) {
        return 100D;
    }

    @Override
    public String getName() {
        return "ICBC";
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