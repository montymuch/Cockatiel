package org.example.Mysql.service;


import org.example.common.OutObject;

public interface UserService {
    public OutObject readUsers();

    public OutObject addUser();

    public OutObject login(String name,String password);
}
