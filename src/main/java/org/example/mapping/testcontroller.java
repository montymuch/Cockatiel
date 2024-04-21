package org.example.mapping;

import java.io.IOException;
import java.util.HashSet;

public class testcontroller {
    public static void main(String[] args) throws IOException {
//        String str="org.example.controller.TestController";
//        String[] split = str.split("\\.");
//        for (String s : split) {
//            System.out.println(s);
//        }
//        String str="/test";
//        System.out.println(str);
        new ControllerMappingProperties().readProperties(new HashSet());
    }
}
