/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ByteDrive
 */
public class ConnectionMySQL {
    public static Connection getConnection(String ip,String port,String user,String password){
        Connection cn = null;
        try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/carshopTEST", user, password);
            System.out.println("Connection succesful");
        
        }catch(SQLException sqle){
            System.out.println("Error: getConnection()->SQLException"+sqle.getMessage());
        }catch(Exception e){
            System.out.println("Error: getConnection()->Exception"+e.getMessage());
        }
        return cn;
    }
}
