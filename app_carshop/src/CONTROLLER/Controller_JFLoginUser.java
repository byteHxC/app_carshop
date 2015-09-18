/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.Clogin;
import VIEW.JFLoginUser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFLoginUser {
    JFLoginUser loginUser;
    Connection cn;
    public Controller_JFLoginUser(Connection cn){
        loginUser = new JFLoginUser();
        //Evento de validar usuario
        this.loginUser.btn_validarUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ActionEvent) {
                String txtUser = Controller_JFLoginUser.this.loginUser.txt_user.getText();
                if(!txtUser.isEmpty()){
                    if(txtUser.equals("root@carshop")){
                        Controller_JFLoginPassword ControllerPassword = new Controller_JFLoginPassword(cn);
                        loginUser.dispose();
                    }else{
                        Clogin usuario = Clogin.verificarUsuario(txtUser, cn);
                        if (usuario != null){
                            if(usuario.getUsuario().equals(txtUser)){
                            Controller_JFLoginPassword ControllerPassword = new Controller_JFLoginPassword(usuario,cn);
                            loginUser.dispose();
                            }
                        }
                        else{
                            Thread error;
                            error = new Thread(new Runnable() {
                                
                                @Override
                                public void run() {
                                    int i = 0;
                                    while(i<2){
                                        i++;
                                        loginUser.txt_user.setForeground(Color.red);
                                        try {
                                            Thread.sleep(1000);
                                         
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    loginUser.txt_user.setForeground(Color.black);
                                    
                                }
                            });
                            error.start();
                        }
                    }
                }
            }
        });
        
    }
}
