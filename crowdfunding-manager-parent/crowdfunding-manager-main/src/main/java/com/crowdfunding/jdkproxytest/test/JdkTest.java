package com.crowdfunding.jdkproxytest.test;

import com.crowdfunding.jdkproxytest.jdk.JdkProxy;
import com.crowdfunding.jdkproxytest.service.UserDao;
import com.crowdfunding.jdkproxytest.service.impl.UserDaoImpl;

public class JdkTest {
    public static void main(String[] args) {
        //1. 创建代理对象
        JdkProxy jdkProxy = new JdkProxy();

        //2. 创建被代理对象——目标对象
        UserDao userDao = new UserDaoImpl();

        //3. 从代理对象中获取增强后的被代理对象——目标对象
        UserDao user = (UserDao) jdkProxy.createProxy(userDao);

        //4. 执行方法
        user.addUser();
        System.out.println();
        user.delUser();
    }
}


