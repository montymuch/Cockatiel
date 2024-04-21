package org.example.cache;

import org.example.aop.SpringConfig;
import org.example.controller.EmpController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class testCacheAop {

    public static ThreadLocal threadLocal=  new ThreadLocal<>();
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmpController userDao=ac.getBean(EmpController.class);
        threadLocal.set("1");
        System.out.println(threadLocal.get());
        userDao.test();
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        threadLocal.remove();
    }
}
