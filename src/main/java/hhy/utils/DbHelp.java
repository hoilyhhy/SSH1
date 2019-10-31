package hhy.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DbHelp {

    private  Connection conn = null;
   /* static{
        final String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(dbDriver);
            System.out.println("驱动加载成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        }
    }

    public DbHelp(){
        String url = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=practice";
        String username = "sa";
        String password = "123456";
        try {
            conn = DriverManager.getConnection(url,username,password);
        System.out.println("获取数据库链接成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取数据库链接失败");
        }
    }

    public  ResultSet execSQL(String sql,Object...args) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            pstmt = conn.prepareStatement(sql);


        for(int i=0;i<args.length;i++){
            pstmt.setObject(i+1,args[i]);
        }
        pstmt.execute();
            resultSet =  pstmt.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
*/}
