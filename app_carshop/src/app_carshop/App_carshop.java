package app_carshop;
import CONTROLLER.Controller_JFLoginUser;
import MODEL.ConnectionMySQL;
import MODEL.UserROOT;
import VIEW.JFLoginUser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ByteDrive
 */
public class App_carshop{
    //Instancia de la clase principal LOGIN
    static Controller_JFLoginUser controladorLogin;
    static Connection cn;
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
         
       //escribirDefault("123456","localhost","3306","root","#%mysql/1");
       settingsDB();
       controladorLogin = new Controller_JFLoginUser(cn);
    }
    public static void  init(){
        settingsDB();
       controladorLogin = new Controller_JFLoginUser(cn);
    }
    
      //Metodo para leer la configuracion de la base de datos y acceder a ella
    private static  void settingsDB(){
        ObjectInputStream objectIn = null;
        try {
            File file = new File("src//SourceDB//settingsDBROOT.dat");
            FileInputStream fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            UserROOT userObj = (UserROOT) objectIn.readObject();
            System.out.println(userObj.getUser());
            
            cn = ConnectionMySQL.getConnection(userObj.getIp(),userObj.getPort(),userObj.getUserDB(),userObj.getPassDB());
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(App_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(objectIn!=null)
                     objectIn.close();
            } catch (IOException ex) {
                Logger.getLogger(App_carshop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void escribirDefault(String password,String ip,String port,String userdb,String passdb){
        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;
        UserROOT objSave = new UserROOT(password,ip,port,userdb,passdb);
        File file = new File ("src//SourceDB//settingsDBROOT.dat");
        try {
            fileOut = new FileOutputStream(file);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(objSave);
            System.out.println("escrito");
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App_carshop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if( fileOut !=null & objectOut!=null ){
                fileOut.close();
                objectOut.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(App_carshop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
