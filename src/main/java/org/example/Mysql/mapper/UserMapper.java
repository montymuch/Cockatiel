package org.example.Mysql.mapper;



import org.example.Mysql.Entity.User;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper  {

    public List<Object> readUsers();

    public int addUser(User user);

    public String login(Map map);

}
