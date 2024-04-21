package org.example.controller;

import org.example.Annotation.GetMapping;
import org.example.cache.MyCache;
import org.example.record.MyLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Controller
public class EmpController {


//    @GetMapping
//    @MyCache(key = "id")
    @MyLog(title = "用户模块", content = "删除用户操作")
    public  void test(){
        System.out.println("hahaha");
    }


}
