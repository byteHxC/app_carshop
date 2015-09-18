/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.io.Serializable;

/**
 *
 * @author ByteDrive
 */
public class UserROOT implements Serializable{
    String user = "root@carshop";
    String password;
    String ip ;
    String port ;
    String userDB ;
    String passDB ;
    
    public UserROOT(){
        
    }

    public UserROOT(String password, String ip, String port, String userDB, String passDB) {
        this.password = password;
        this.ip = ip;
        this.port = port;
        this.userDB = userDB;
        this.passDB = passDB;
    }
    
    public String getUser(){
        return user;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserDB() {
        return userDB;
    }

    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }

    public String getPassDB() {
        return passDB;
    }

    public void setPassDB(String passDB) {
        this.passDB = passDB;
    }
    
    
    
}
