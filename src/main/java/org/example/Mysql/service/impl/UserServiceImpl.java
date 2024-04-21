package org.example.Mysql.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.MD5;

import org.example.Mysql.Entity.User;
import org.example.Mysql.connector.Conn;
import org.example.Mysql.mapper.UserMapper;
import org.example.Mysql.service.UserService;
import org.example.common.OutObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    Conn c = new Conn(); // 创建本类对象
    Connection connection = c.getConnection();
//    public OutObject readUsers(){
//        OutObject outObject = new OutObject();
//        List<Object> objects = userMapper.readUsers();
//        System.out.println(objects);
//        outObject.setOut(objects);
//        outObject.setMsg("成功了");
//        outObject.setDate(new Date(System.currentTimeMillis()));
//        return outObject;
//    }
   public OutObject readUsers(){
      try {
           OutObject outObject = new OutObject();
           Statement statement = connection.createStatement();
           String sql="  select *  from user ";
          HashSet<User> objects = new HashSet<>();
           ResultSet resultSet = statement.executeQuery(sql);
           while (resultSet.next()){
               User user = new User();
               user.setId(resultSet.getString("id"));
               user.setName(resultSet.getString("username"));
               user.setPassword(resultSet.getString("password"));
                objects.add(user);
           }
           statement.close();
           connection.close();
           outObject.setOut(objects);
           outObject.setMsg("成功了");
           outObject.setDate(new Date(System.currentTimeMillis()));
          System.out.println(outObject);
           return outObject;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
 }

    public OutObject addUser(){
        String salt = "md5Salt";
        int index = 1;
        int count = 2;
        MD5 md5 = new MD5(salt.getBytes(StandardCharsets.UTF_8), index, count);

        String password=md5.digestHex("cccc");
        Snowflake snowflake = IdUtil.getSnowflake();
        long l = snowflake.nextId();
        String ll=l+"";
        int i = userMapper.addUser(new User(ll,"hh",password));
        OutObject outObject = new OutObject();
        outObject.setCode(200);
        outObject.setOut(i);
        outObject.setMsg("成功修改"+i+"条数据");
        outObject.setDate(new Date(System.currentTimeMillis()));
        return outObject;
    }

    @Override
    public OutObject login(String name, String password) {
        String salt = "md5Salt";
        int index = 1;
        int count = 2;
        MD5 md5 = new MD5(salt.getBytes(StandardCharsets.UTF_8), index, count);
        password=md5.digestHex("cccc");
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name","hh");
        objectObjectHashMap.put("password",password);
        String lid = userMapper.login(objectObjectHashMap);
        OutObject outObject = new OutObject(lid,200,"登录许可",new Date(System.currentTimeMillis()));

        return outObject;
    }


}
