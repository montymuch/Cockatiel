package org.example.Mysql;

import org.example.Mysql.mapper.UserMapper;
import org.example.Mysql.service.UserService;
import org.example.Mysql.service.impl.UserServiceImpl;
import org.example.Mysql.service.impl.WebLogServiceImpl;
import org.example.common.OutObject;
import org.example.record.WebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
public class testMysql {
//    @Autowired
    private UserService userService;
    public static void main(String[] args) {

        new WebLogServiceImpl().addWebLog(new WebLog( new Date(2024,4,21),"hahahahah","1232","asdasd",23123));
    }

    public void setUserService(){
        OutObject outObject = userService.addUser();
        System.out.println(outObject);
    }
}
