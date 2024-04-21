package org.example.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.example.controller.EmpController;
import org.example.record.MyLog;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Component
@Aspect
public class CacheAop {
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
    Jedis jedis = RedisFactroy.getInstance();

    @Pointcut("@annotation(org.example.cache.MyCache)")//在注解的位置切入代码
    //@Pointcut("execution(public * org.wujiangbo.controller..*.*(..))")//从controller切入
    public void myCachePoinCut() {
    }

    @Before("myCachePoinCut()")
    public void beforMethod(JoinPoint point) throws ClassNotFoundException, NoSuchMethodException {
//        startTime.set(System.currentTimeMillis());
        Class<?> emp = point.getTarget().getClass();
        Signature signature = point.getSignature();
        String name = signature.getName();
        System.out.println("方法名："+name);
        //获得代理对象的类名
        Class<?> aClass =point.getThis().getClass();

        System.out.println("类名："+aClass.getName());
        System.out.println(testCacheAop.threadLocal.get());
        testCacheAop.threadLocal.set(new EmpController());
        //拿到方法对象
//        Method name1 = aClass.getDeclaredMethod(name);
        //拿到对应方法的注解对象
//        MyCache annotation = name1.getAnnotation(MyCache.class);


    }

}
