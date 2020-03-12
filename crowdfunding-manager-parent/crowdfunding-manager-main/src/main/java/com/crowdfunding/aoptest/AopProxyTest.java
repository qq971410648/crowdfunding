package com.crowdfunding.aoptest;

import com.crowdfunding.jdkproxytest.dao.UserDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.TreeSet;

public class AopProxyTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-context.xml");
        UserDao userDao = (UserDao) applicationContext.getBean("userDaoProxy");
        userDao.addUser();
        userDao.delUser();


        TreeSet<Integer> integers = new TreeSet<>();
        integers.add(1);
    }
}
