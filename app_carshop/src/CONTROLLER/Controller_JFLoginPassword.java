/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.CUsuario;
import MODEL.CLogin;
import MODEL.UserROOT;
import app_carshop.App_carshop;
import VIEW.JFLoginPassword;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFLoginPassword {
    JFLoginPassword loginPassword;
    Connection cn;
    //Ingreso usuario root
     public Controller_JFLoginPassword(Connection cn){
        this.loginPassword = new JFLoginPassword();
        this.loginPassword.label_Bienvenido.setText("Bienvenido root");
        this.loginPassword.label_tipoUser.setText("Tipo usuario: ROOT");
        this.cn=cn;
        //Evento para verificar la contraseña del root
        UserROOT root = getROOT();
        
        this.loginPassword.btn_validarPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(root.getPassword().equals(loginPassword.txtP_Password.getText())){
                   Controller_JFSettingsDB controllerJFSettingsDB = new Controller_JFSettingsDB(cn);
                   loginPassword.dispose();
               }else{
                     Thread error;
                            error = new Thread(new Runnable() {
                                
                                @Override
                                public void run() {
                                    int i = 0;
                                    while(i<2){
                                        i++;
                                        loginPassword.txtP_Password.setForeground(Color.red);
                                        loginPassword.label_contrasena.setText("!Contraseña incorrecta¡");
                                        try {
                                            Thread.sleep(800);
                                         
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                   loginPassword.txtP_Password.setForeground(Color.black);
                                   loginPassword.txtP_Password.setText("");
                                   loginPassword.label_contrasena.setText("Contraseña: ");
                                    
                                }
                            });
                            error.start();
               }
            }
        });
        
        
        //Evento para controlar la salida
        this.loginPassword.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App_carshop.init();
                loginPassword.dispose();
            }
        });
        
        
    }
     //Ingreso usuario (EMPLEADO)
     public Controller_JFLoginPassword(CLogin usuario,Connection cn){
         
        this.loginPassword.label_Bienvenido.setText("Bienvenido "+usuario.getUsuario());
        this.loginPassword.label_tipoUser.setText("Tipo usuario: "+CUsuario.tipoUser(usuario.getId_usuario(), cn));
        this.cn=cn;
        this.loginPassword = new JFLoginPassword();
        //Evento para controlar la salida
        this.loginPassword.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App_carshop.init();
                loginPassword.dispose();
            }
        });
        
        
    }
     
     private UserROOT  getROOT(){
        ObjectInputStream objectIn = null;
        UserROOT userObj = null;
        try {
            File file = new File("src//SourceDB//settingsDBROOT.dat");
            FileInputStream fileIn = new FileInputStream(file);
            objectIn = new ObjectInputStream(fileIn);
            userObj = (UserROOT) objectIn.readObject();
            
            
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
        return userObj;
    }
    
}
