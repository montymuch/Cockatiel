package org.example.aop;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.kafka.kafakFactoryBean;
import org.example.kafka.kafkaProducer;
import org.example.record.MyLog;
import org.example.record.WebLog;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class OpenLogAspect {
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(org.example.record.MyLog)")//在注解的位置切入代码
    //@Pointcut("execution(public * org.wujiangbo.controller..*.*(..))")//从controller切入
    public void openLogPoinCut() {
    }

    @Before("openLogPoinCut()")
    public void beforeMethod(JoinPoint point){
        startTime.set(System.currentTimeMillis());

    }

   @After("openLogPoinCut()")
    public  void afterMethod(JoinPoint point) throws NoSuchMethodException, ClassNotFoundException {
       WebLog webLog = new WebLog();
       Date date = new Date();
       date.setTime(startTime.get());
       webLog.setStartTime(date);
       webLog.setSpendTime((int) (System.currentTimeMillis()-startTime.get()));
       Signature signature = point.getSignature();
       //包名
       System.out.println(signature.getDeclaringTypeName());

       //获得方法名
       String name = signature.getName();
       System.out.println(name);
       //获得代理对象的类名
       Class<?> aClass = Class.forName(point.getTarget().getClass().getName());
       //拿到方法对象
       Method name1 = aClass.getDeclaredMethod(name);
       //拿到对应方法的注解对象
       MyLog annotation = name1.getAnnotation(MyLog.class);
       //解析注解的值
//       System.out.println(annotation.content());
//       System.out.println(annotation.title());

        webLog.setDescription(annotation.title()+":"+annotation.content());
        webLog.setBasePath(signature.getDeclaringTypeName());
        webLog.setMethod(name);
       new kafkaProducer().send(webLog);
       System.out.println(webLog);

   }
}
