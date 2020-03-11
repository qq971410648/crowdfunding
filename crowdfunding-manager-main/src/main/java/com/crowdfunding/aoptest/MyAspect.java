package com.crowdfunding.aoptest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 切面类
 */
public class MyAspect implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        check_Login();//检查是否登录

        // 执行目标对象类的方法
        Object proceed = invocation.proceed();

        log();//记录日志

        return proceed;

    }

    public void check_Login(){
        System.out.println("检查是否登录");
    }

    public void log(){
        System.out.println("记录日志");
    }
}
