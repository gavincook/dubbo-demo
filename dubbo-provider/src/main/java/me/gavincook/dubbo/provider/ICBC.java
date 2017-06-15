/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;

import me.gavincook.dubbo.api.Bank;

/**
 * 工行
 * @author tanghong.th
 * @version $Id: ICBC.java, v 0.1 2017-06-14 下午4:39 tanghong.th Exp $$
 */
@Service(version = "2.0")
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

}