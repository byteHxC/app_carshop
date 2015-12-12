/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROL.Controller_JFLoginUser;
import app_carshop.app_carshop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public void saveObject(){
              String path = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
           File aux =new File(path);
            if (aux.isDirectory())
                path = path + "/Resources/SourceDB/settingsDBROOT.dat";
            else
                path = aux.getParent() + "/Resources/SourceDB/settingsDBROOT.dat";
            
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        File fileDB = new File(path);
        try{
            fileOut = new FileOutputStream(fileDB);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            System.out.println("settingsDBROOT.dat write sucessful");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserROOT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserROOT.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if( fileOut !=null & objectOut!=null ){
                fileOut.close();
                objectOut.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(UserROOT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static  UserROOT getROOTDB(){
        UserROOT userObj = null;
        ObjectInputStream objectIn = null;
        try {
             String path = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
           File aux =new File(path);
            if (aux.isDirectory())
                path = path + "/Resources/SourceDB/settingsDBROOT.dat";
            else
                path = aux.getParent() + "/Resources/SourceDB/settingsDBROOT.dat";
            File file = new File(path);
            FileInputStream fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            
            userObj = (UserROOT) objectIn.readObject();
            return userObj;
        } catch (FileNotFoundException ex) {
            System.out.println("va escribir ");
            UserROOT.escribirDefaultROOTDB("123456","localhost","3306","root","#%mysql/1");
            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(app_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(objectIn!=null)
                     objectIn.close();
            } catch (IOException ex) {
                Logger.getLogger(app_carshop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return userObj;
    }
    
     public  static void escribirDefaultROOTDB(String password,String ip,String port,String userdb,String passdb){
       
              String path = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
           File aux =new File(path);
            if (aux.isDirectory())
                path = path + "/Resources/SourceDB/settingsDBROOT.dat";
            else
                path = aux.getParent() + "/Resources/SourceDB/settingsDBROOT.dat";
            
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        UserROOT objSave = new UserROOT(password,ip,port,userdb,passdb);
        File file = new File (path);
        try {
            fileOut = new FileOutputStream(file);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objSave);
            System.out.println("Default's settingsDBROOT.dat :D");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(app_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(app_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if( fileOut !=null & objectOut!=null ){
                fileOut.close();
                objectOut.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(app_carshop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
