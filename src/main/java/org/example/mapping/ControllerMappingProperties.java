package org.example.mapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;

public  class ControllerMappingProperties extends AbstrctControllerMapping{
    private HashSet controllers;

    public HashSet getControllers() {
        return controllers;
    }

    public void setControllers(HashSet controllers) {
        this.controllers = controllers;
    }

    public String[] readProperties(HashSet controllers,String path) throws IOException {
        this.controllers=controllers;
        HashSet<Object> objects = new HashSet<>();
//        String path="/test";
        objects.add("org.example.controller.TestController");
        String[] strings=new String[2];
        for (Object object : objects) {
            String s = object.toString();
            String[] split = s.split("\\.");
            System.out.println(split.length);
            String s1 = split[split.length - 1];
            Properties properties=new Properties();
            String url=System.getProperty("user.dir")+"/src/main/java/resources/"+s1+".properties";
            System.out.println(url);
            properties.load(new FileInputStream(url));
            String methodName = properties.getProperty(path);
            strings[0]=object.toString();
            strings[1]=methodName;
            System.out.println(methodName);
            return  strings;

        }
        return null;
    }

    @Override
    public Object readProperties(HashSet controllers) throws IOException {
        return null;
    }

    @Override
    public Object readXML(HashSet controllers) {
        return null;
    }

    @Override
    public Object readYML(HashSet controllers) {
        return null;
    }


}
