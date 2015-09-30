/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CUsuario;
import MODEL.CLogin;
import MODEL.UserROOT;
import app_carshop.App_carshop;
import VIEW.JFLoginPassword;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFLoginPassword {
    JFLoginPassword loginPassword;
    Connection cn;
    CLogin login;
    //Ingreso usuario root
     public Controller_JFLoginPassword(Connection cn){
        this.loginPassword = new JFLoginPassword();
        this.loginPassword.label_Bienvenido.setText("Bienvenido root");
        this.loginPassword.label_tipoUser.setText("Tipo usuario: ROOT");
        this.cn=cn;
       
        UserROOT root = UserROOT.getROOTDB();
         //Evento para verificar la contraseña del root
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
     public Controller_JFLoginPassword(CLogin login,Connection cn){
         CUsuario usuario = CUsuario.getObject(login.getClave_elector(), cn);
        this.login=login;
        this.loginPassword = new JFLoginPassword();
        this.loginPassword.label_Bienvenido.setText("Bienvenido "+login.getUsuario());
        this.loginPassword.label_tipoUser.setText("Tipo usuario: \n"+usuario.getTipo());
      
        this.cn=cn;
        //poner imagen de usuario
        this.loginPassword.label_ImageUser.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(loginPassword.label_ImageUser.getWidth(), loginPassword.label_ImageUser.getHeight(), Image.SCALE_DEFAULT)));
        
        this.loginPassword.btn_validarPass.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(login.getPassword().equals(loginPassword.txtP_Password.getText())){
                    switch (usuario.getTipo()){
                        case ("Gerente"):
                                Controller_JFGerenteHome JFGerenteHome = new Controller_JFGerenteHome(login, cn);
                                loginPassword.dispose();
                            break;
                        case("Financiamiento"):
                                System.out.println("Entra financiemianto");
                            break;
                        case("Comercio"):
                                Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                                loginPassword.dispose();
                            break;
                    }
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

     
     private ImageIcon getImageWithBlob(Blob blob,String nombre){
         ImageIcon image = null;
         BufferedImage img = null;
        try {
            byte[] data = blob.getBytes(1,(int)blob.length());
         
            img = ImageIO.read(new ByteArrayInputStream(data));
            //Crear la imagen
             image = new ImageIcon(img);
            return image;
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Controller_JFLoginPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
     }
     
    
}
