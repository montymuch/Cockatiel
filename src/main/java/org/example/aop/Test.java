package org.example.aop;


import org.example.common.OutObject;
import org.example.controller.EmpController;
import org.example.controller.TestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfig.class);
        EmpController userDao=ac.getBean(EmpController.class);
        userDao.test();

    }
}