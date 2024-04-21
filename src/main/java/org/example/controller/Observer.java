package org.example.controller;


import org.example.mapping.ControllerMappingProperties;
import org.example.server.GsonUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;

public class Observer {
    private TestController tesController=new TestController();

    public Object handle(String path,TestController tesController,String method,Object[] args) throws IOException, ClassNotFoundException {
//        Class<? extends TestController> aClass = tesController.getClass();
        System.out.println("入参：" + args);
//        System.out.println(aClass.getName());
        String[] o1 = this.pathMapping(path);
        String controllerName = o1[0];
        String methodName = o1[1];
        Class<?> aClass = Class.forName(controllerName);
        Method[] methods = aClass.getMethods();
        try {
            Object o = aClass.newInstance();

            for (Method methodd : methods) {
                System.out.println(methodd);
                if (methodd.getName().equals(methodName)) {

//                String print="print";
//                Object[] argss=new Object[]{print,"ssss"};

                    Object invoke = methodd.invoke(o, (Object) args);
                    return  invoke;
                }
            }

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

   return  null;

    }

    public String[] pathMapping(String paths) throws ClassNotFoundException, IOException {
        /*
        * 扫描controller这里没有配置注解的版本
        * */
        String baseUrl= System.getProperty("user.dir")+"/src/main/java/org/example/controller";
        File file=new File(baseUrl);
        File[] files = file.listFiles();
        HashSet<String>  controllerName=new HashSet<>();
        for (File file1 : files) {
            String path = file1.getPath();
            if(path.endsWith("Controller.java")){
            System.out.println(path);
            String[] split = path.split("java\\\\");
            String replace = split[1].replace("\\", ".");
            String substring = replace.substring(0, replace.length() - 5);
            controllerName.add(substring);
           }}
        for (String s : controllerName) {
            System.out.println(s);

        }
        String[] o =  new ControllerMappingProperties().readProperties(controllerName,paths);
        System.out.println(o);
        return o;
    }
}
