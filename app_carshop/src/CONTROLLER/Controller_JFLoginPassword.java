/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Clogin;
import app_carshop.App_carshop;
import VIEW.JFLoginPassword;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFLoginPassword {
    JFLoginPassword loginPassword;
    Connection cn;
     public Controller_JFLoginPassword(Connection cn){
        this.loginPassword = new JFLoginPassword();
        this.loginPassword.label_Bienvenido.setText("Bienvenido root");
        this.loginPassword.label_tipoUser.setText("Tipo usuario: ROOT");
        this.cn=cn;
        //Evento para controlar la salida
        this.loginPassword.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App_carshop.init();
                loginPassword.dispose();
            }
        });
        
        
    }
     public Controller_JFLoginPassword(Clogin usuario,Connection cn){
         
        this.loginPassword.label_Bienvenido.setText("Bienvenido "+usuario.getUsuario());
        this.loginPassword.label_tipoUser.setText("Tipo usuario: "+Clogin.tipoUser(usuario.getId_user(), cn));
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
    
}
