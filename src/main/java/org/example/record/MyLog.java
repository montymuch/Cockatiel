package org.example.record;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
//Retention注解括号中的"RetentionPolicy.RUNTIME"意思是让 MyLog 这个注解的生命周期一直程序运行时都存在
@Retention(RetentionPolicy.RUNTIME)
public @interface MyLog
{
    /**
     * 模块标题
     */
    String title() default "";
    /**
     * 日志内容
     */
    String content() default "";
}
