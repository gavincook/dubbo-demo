/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.api.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import me.gavincook.dubbo.api.Bank;
import org.hibernate.validator.constraints.Length;

/**
 * 银行卡片
 * @author Gavincook
 * @version 1.0.0
 */
public class Card implements Serializable{

    @Length(min = 16, max = 19, groups = {Bank.OpenAccount.class})
    private String number;

    @NotNull(groups = Bank.OpenAccount.class)
    private String userName;


    /**
     * Getter method for property number.
     *
     * @return property value of number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter method for property number.
     *
     * @param number value to be assigned to property number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Getter method for property userName.
     *
     * @return property value of userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter method for property userName.
     *
     * @param userName value to be assigned to property userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}