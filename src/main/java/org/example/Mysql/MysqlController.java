package org.example.Mysql;

import org.example.Annotation.GetMapping;
import org.example.Mysql.service.UserService;
import org.example.Mysql.service.WeblogService;
import org.example.common.OutObject;
import org.example.record.WebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;


@RestController
public class MysqlController {
    @Autowired
    private WeblogService weblogService;
    @PostMapping("test")
   public void test(){
     weblogService.addWebLog(new WebLog( new Date(2024,4,21),"ssad" ,"sadasd","asdasd",2222));

  }

    public static void main(String[] args) {

    }
}
