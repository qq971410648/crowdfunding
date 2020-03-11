package com.crowdfunding.jdkproxytest.aspect;

/**
 * 切面类
 */
public class MyAspect {
    public void check_Login(){
        System.out.println("检查是否登录");
    }

    public void do_Log(){
        System.out.println("记录日志");
    }
}
