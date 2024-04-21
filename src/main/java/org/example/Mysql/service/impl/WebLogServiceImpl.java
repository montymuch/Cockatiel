package org.example.Mysql.service.impl;

import org.example.Mysql.Entity.User;
import org.example.Mysql.connector.Conn;
import org.example.Mysql.mapper.WebLogMapper;
import org.example.Mysql.service.WeblogService;
import org.example.record.WebLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.util.HashSet;

@Service
public class WebLogServiceImpl implements WeblogService {

    @Resource
    private WebLogMapper webLogMapper;
    Conn c = new Conn(); // 创建本类对象
    Connection connection = c.getConnection();
    @Override
    public void addWebLog(WebLog webLog) {
        webLogMapper.addWebLog(webLog);
    }


    public void addWebLogDo(WebLog webLog) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO web_log (startTime, basepath, method, description, spendTime) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, webLog.getStartTime());
        preparedStatement.setString(2, webLog.getBasePath());
        preparedStatement.setString(3, webLog.getMethod());
        preparedStatement.setString(4, webLog.getDescription());
        preparedStatement.setDouble(5, webLog.getSpendTime());
        int rowsAffected = preparedStatement.executeUpdate();
    }
}
