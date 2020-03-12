package com.crowdfunding.jdkproxytest.dao.impl;

import com.crowdfunding.jdkproxytest.dao.UserDao;

/**
 * 目标对象
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
