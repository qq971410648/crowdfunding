package com.crowdfunding.jdkproxytest.aspect;

/**
 * 切面类
 */
public class MyAspect {

    //前置通知
    public void Before(){
        System.out.println("前置通知：正在模拟执行权限检查");
    }
}


