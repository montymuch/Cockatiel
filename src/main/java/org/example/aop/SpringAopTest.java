package org.example.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SpringAopTest {
    /**
     * 定义切入点
     *  在通知类中，定义一个空的private方法，在其上添加注解@Pointcut("execution(返回值类型 包名.类.方法)")
     *  @Pointcut注解表示需要注入aop功能的方法范围
     */
    @Pointcut("execution(void org.example.controller.*.*(..))")
    private void test(){

    }


    @Before("test()")
    public void methodTestBefore(){
        System.out.println("我是测试方法！");
    }
    @After("test()")
    public void methodTestAfter(){
        System.out.println("我是测试方法！");
    }

}