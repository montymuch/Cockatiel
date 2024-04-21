package org.example.controller;


import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
//        Class clz = Class.forName("org.example.controller.TestController");
//        Object o = clz.newInstance();
//        Method method = clz.getMethod("print",Object[].class);
//        Type[] genericParameterTypes = method.getGenericParameterTypes();
//        System.out.println(genericParameterTypes);
//        Object[] objects=new Object[]{"print","sss"};
//        method.invoke(o,(Object) objects);
//        new Observer().handle("/test",new TestController(),"print",null);
//        System.out.println("hhhhhhhhhhh");
        new Observer().pathMapping("/test");
//        Class.forName("org.example.controller.TestController");
    }
}
