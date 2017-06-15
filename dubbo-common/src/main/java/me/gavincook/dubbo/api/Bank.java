/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.api;

/**
 * @author tanghong.th
 * @version $Id: Bank.java, v 0.1 2017-06-14 下午4:38 tanghong.th Exp $$
 */
public interface Bank {

    /**
     * 获取余额
     * @return
     */
    Double getBalance(Long userId);

}