package org.example.Mysql.connector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conn { // 创建类Conn
    Connection con; // 声明Connection对象
    public static String user;
    public static  String password;
    public Connection getConnection() { // 建立返回值为Connection的方法
        try { // 加载数据库驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        user = "root";//数据库登录名
        password = "root";//密码
        try { // 通过访问数据库的URL获取数据库连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true", user, password);
//            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con; // 按方法要求返回一个Connection对象
    }
    public static void main(String[] args) throws SQLException { // 主方法，测试连接
        Conn c = new Conn(); // 创建本类对象
        Connection connection = c.getConnection();// 调用连接数据库的方法
        Statement statement = connection.createStatement();
//        resultSet.close();
        statement.close();
        connection.close();
    }
}

