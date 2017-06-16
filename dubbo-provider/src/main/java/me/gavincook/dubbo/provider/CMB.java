/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;

import me.gavincook.dubbo.api.Bank;
import me.gavincook.dubbo.api.model.Card;

/**
 * 招行
 * @author Gavincook
 * @version 1.0.0
 */
@Service(version = "3.0")
public class CMB implements Bank{
    @Override
    public Double getBalance(Long userId) {
        return 22.0D;
    }

    @Override
    public String getName() {
        return "CMB";
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