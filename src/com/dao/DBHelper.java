
package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBHelper {
      public static Connection getConnection(){
       String path="jdbc:mysql://localhost:3307/kiraye?user=root&password=root";
        Connection c=null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c=DriverManager.getConnection(path);
            
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Exception in DBHelper");
        }
        return c; 
   } 
}
