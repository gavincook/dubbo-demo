/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.api;

import javax.validation.GroupSequence;

import me.gavincook.dubbo.api.model.Card;

/**
 * @author Gavincook
 * @version 1.0.0
 */
public interface Bank {

    @interface OpenAccount{}

    @interface CloseAccount{}

    /**
     * 获取余额
     * @return
     */
    Double getBalance(Long userId);

    /**
     * 获取银行名称
     * @return
     */
    String getName();

    /**
     * 开户
     * @param card
     * @return
     */
    boolean openAccount(Card card);

    /**
     * 销户
     * @param card
     * @return
     */

    boolean closeAccount(Card card);


}