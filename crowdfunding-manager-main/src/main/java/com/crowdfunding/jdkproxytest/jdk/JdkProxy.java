package com.crowdfunding.jdkproxytest.jdk;

import com.crowdfunding.jdkproxytest.aspect.MyAspect;
import com.crowdfunding.jdkproxytest.dao.UserDao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理类
 */
public class JdkProxy implements InvocationHandler {

    //1. 声明目标类接口
    private UserDao userDao;

    //2. 创建代理方法
    public Object createProxy(UserDao userDao){
        this.userDao = userDao;

        //1. 类加载器
        ClassLoader classLoader = JdkProxy.class.getClassLoader();
        //2. 获取目标对象实现的所有接口
        Class<?>[] interfaces = userDao.getClass().getInterfaces();
        //3. 使用代理类Proxy，进行增强   classLoader：当前类加载器  interfaces：目标类所实现的所有接口  this：代理类JdkProxy本身
        Object o = Proxy.newProxyInstance(classLoader, interfaces, this);
        //4. 返回的是代理后的对象.
        return o;
    }

    /**
     * 实现InvocationHandler  必须实现的方法
     *
     * 所有动态代理类的方法的调用，都交给invoke()方法处理
     * @param proxy     被代理后的对象
     * @param method    将要被执行的方法
     * @param args      执行方法时需要的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 声明切面类
        MyAspect myAspect = new MyAspect();
        //2. 前增强
        myAspect.check_Login();//检查是否登录
        //3. 在目标类上调用方法，并传入参数
        Object obj = method.invoke(userDao, args);
        //4. 后增强
        myAspect.do_Log();//日志处理
        return obj;
    }
}
