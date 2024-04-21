package org.example.controller;

import org.example.Annotation.GetMapping;
import org.example.Annotation.PutMapping;
import org.example.Mysql.service.impl.UserServiceImpl;
import org.example.common.OutObject;
import org.example.server.HttpRequestHander;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
@Controller
public class TestController {
    @GetMapping(path = "/test2")
    public  void print(Object[] args){
        System.out.println(args);
        if(args[0]!=null){
            System.out.println("hhhjhh"+args[0]);}

    }
    @Cacheable
    @PutMapping(path="/test")
    public void print2(Object[] args){
        HashMap arg = (HashMap) args[0];
        System.out.println(args[0]+"  ");
        Object film = arg.get("film");
        System.out.println("print22......"+film);

    }
    @GetMapping(path="/readUser")
    public OutObject readUser(Object[] args){
        System.out.println("threadlocal"+new HttpRequestHander().threadLocal.get());
        OutObject outObject = new UserServiceImpl().readUsers();

        return outObject;
    }
}
