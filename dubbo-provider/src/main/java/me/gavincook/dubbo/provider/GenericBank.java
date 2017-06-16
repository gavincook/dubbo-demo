/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package me.gavincook.dubbo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

/**
 * 泛化服务暴露
 * @author Gavincook
 * @version 1.0.0
 */
@Service(version = "4.0", interfaceName = "me.gavincook.dubbo.api.Bank")
public class GenericBank implements GenericService{
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args)
        throws GenericException {
        if(method.equals("openAccount")){
            return true;
        }
        //否则简单的把方法名返回就好了
        return method;
    }
}