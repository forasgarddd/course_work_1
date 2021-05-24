package com.cinema.database;
import java.sql.*;

public class ConnectionProvider {
    private static Connection con;

    public static Connection getConnection(){
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cw5","root","Goodluck2231");

        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
