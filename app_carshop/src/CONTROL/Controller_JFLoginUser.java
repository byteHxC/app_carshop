/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.ELogin;
import VIEW.JFLoginUser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.loginUser.btn_validarUser.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                String txtUser = Controller_JFLoginUser.this.loginUser.txt_user.getText();
                if(!txtUser.isEmpty()){
                    if(txtUser.equals("root@carshop")){
                        Controller_JFLoginPassword ControllerPassword = new Controller_JFLoginPassword(cn);
                        loginUser.dispose();
                    }else{
                        ELogin usuario = ELogin.verificarLogin(txtUser, cn);
                        if (usuario !=null){
                            Controller_JFLoginPassword ControllerPassword = new Controller_JFLoginPassword(usuario,cn);
                            loginUser.dispose();
                            
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
                                        loginUser.label_Usuario.setText("Usuario incorrecto");
                                        try {
                                            Thread.sleep(800);
                                         
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(Controller_JFLoginUser.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    loginUser.txt_user.setForeground(Color.black);
                                    loginUser.label_Usuario.setText("Usuario:");
                                    
                                    
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
