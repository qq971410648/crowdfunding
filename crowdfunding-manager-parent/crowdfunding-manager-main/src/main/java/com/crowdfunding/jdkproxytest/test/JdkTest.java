package com.crowdfunding.jdkproxytest.test;

import com.crowdfunding.jdkproxytest.dao.UserDao;
import com.crowdfunding.jdkproxytest.dao.impl.UserDaoImpl;
import com.crowdfunding.jdkproxytest.jdk.JdkProxy;

import java.util.Vector;

public class JdkTest {
    public static void main(String[] args) {
        //1. 创建代理对象
        JdkProxy jdkProxy = new JdkProxy();

        //2. 创建目标对象
        UserDao userDao = new UserDaoImpl();

        //3. 从代理对象中获取增强后的目标对象
        UserDao user = (UserDao) jdkProxy.createProxy(userDao);

        //4. 执行方法
        user.addUser();
        System.out.println();
        user.delUser();

        Vector<Integer> integers = new Vector<>();
    }
}
