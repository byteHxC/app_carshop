/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.UserROOT;
import VIEW.JFSettingsUser;
import app_carshop.App_carshop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFSettingsUser {
    JFSettingsUser viewSettingsUser;
    Connection cn;
    
    //Constructor usuario root
    public Controller_JFSettingsUser(UserROOT userRoot,Connection cn){
        viewSettingsUser = new JFSettingsUser();
        this.cn = cn;
        viewSettingsUser.label_usuario.setText("USUARIO: "+userRoot.getUser());
        viewSettingsUser.jLabel6.setVisible(false);
        viewSettingsUser.txtUsuario.setVisible(false);
        ImageIcon imageLogo = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
        viewSettingsUser.ChooserImageGerente.setIcon(new ImageIcon(imageLogo.getImage().getScaledInstance(viewSettingsUser.ChooserImageGerente.getWidth(),viewSettingsUser.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        
        //boton de guardar
        this.viewSettingsUser.btn_guardar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
               String pass = viewSettingsUser.txtP_Password.getText();
               String passConfirm = viewSettingsUser.txtP_PasswordConfirm.getText();
               if(pass.equals(passConfirm) && pass.length()>=6){
                    int resp = JOptionPane.showConfirmDialog(viewSettingsUser,"¿Para realizar este proceso necesita cerrar sesion, esta de acuerdo? ","Mensaje",JOptionPane.YES_OPTION);
                    if(resp == JOptionPane.YES_OPTION){
                           userRoot.setPassword(pass);
                           userRoot.saveObject();
                           viewSettingsUser.dispose();   
                           App_carshop.init();
                    }
               }else if (pass.length()<6){
                   
                   JOptionPane.showMessageDialog(viewSettingsUser,"La contraseña debe tener 6 o mas caracteres","Vuelva a intentar",JOptionPane.INFORMATION_MESSAGE);
                   viewSettingsUser.txtP_PasswordConfirm.setText(null);
                   viewSettingsUser.txtP_Password.setText(null);
               }else{
                   JOptionPane.showMessageDialog(viewSettingsUser,"No coinciden la confirmacion de PASSWORD","Vuelva a intentar",JOptionPane.INFORMATION_MESSAGE);
                   viewSettingsUser.txtP_PasswordConfirm.setText(null);
               }
            }
            
        });
        
        //boton de cancelar
        this.viewSettingsUser.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsDB controllerSettingsDB = new Controller_JFSettingsDB(cn);
                viewSettingsUser.dispose();
            
            }
        });
        
        //Cerrar desde jframe
        this.viewSettingsUser.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFSettingsDB controllerSettingsDB = new Controller_JFSettingsDB(cn);
                viewSettingsUser.dispose();
            }
        });
    }
    
    //Constructor usuario empleado
}
