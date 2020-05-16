package com.crowdfunding.jdkproxytest.service.impl;

import com.crowdfunding.jdkproxytest.service.UserDao;

/**
 * 被代理对象——目标类
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUser() {
        System.out.println("添加用户");
    }

    @Override
    public void delUser() {
        System.out.println("删除用户");
    }
}




