/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CLogin;
import MODEL.ConnectionMySQL;
import MODEL.UserROOT;
import VIEW.JFConfiguracionIncial;
import app_carshop.App_carshop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFConfiguracionInicial {
    JFConfiguracionIncial viewSettingsDB;
  
    CLogin login;
    Connection cn;
    UserROOT userRoot;
    
    public Controller_JFConfiguracionInicial(Connection cn,UserROOT root){
        this.viewSettingsDB = new JFConfiguracionIncial();
        this.viewSettingsDB.label_usuario.setText( "USUARIO: root@carshop");
        this.cn = cn;
     
        userRoot = UserROOT.getROOTDB();
       //Boton para ingresar los datos al empleado
        this.viewSettingsDB.btn_addGerente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              Controller_agregarGerente addGerente = new Controller_agregarGerente(cn, root);
              viewSettingsDB.dispose();
            }
        });
        
       //Boton para ir ingresar los datos de la configuracion la base de datos
        this.viewSettingsDB.btn_configBD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {            
                viewSettingsDB.dialog_settingBD.setVisible(true);
            }
        });
        
         //Boton de guardar configuracion de la base de datos
        this.viewSettingsDB.btn_guardarBD.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userRoot.setIp( viewSettingsDB.txtIP.getText());
                userRoot.setPort( viewSettingsDB.txtPORT.getText());
                userRoot.setUserDB(viewSettingsDB.txtUSERNAME.getText());
                userRoot.setPassDB(viewSettingsDB.txtPASSWORD.getText());
                
                boolean testingBD = ConnectionMySQL.testingConnection(viewSettingsDB, userRoot.getIp(), userRoot.getPort(), userRoot.getUserDB(),userRoot.getPassDB());
                if(testingBD==true){
                    int resp = JOptionPane.showConfirmDialog(viewSettingsDB,"¿Para realizar este proceso necesita cerrar sesion, esta de acuerdo? ","Mensaje",JOptionPane.YES_OPTION);
                    if(resp == JOptionPane.YES_OPTION){
                    userRoot.saveObject();
                    viewSettingsDB.dispose();  
                    App_carshop.init();
                    }
                }
            }
          
        });
        //Boton para configurar cuenta de root
        this.viewSettingsDB.btn_settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsUser controllerSettingsUser = new Controller_JFSettingsUser(userRoot,cn);
                viewSettingsDB.dispose();
            }
        });
        //Boton de cerrar sesion
        this.viewSettingsDB.btn_logout.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewSettingsDB,"¿Confirmar cierre de sesion?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewSettingsDB.dispose();
                    
                }
            }
        });
        
        //Al cerrar el frame
        this.viewSettingsDB.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewSettingsDB,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewSettingsDB.dispose();
                }
            }
        });
        
        
    }
    
    public Blob getBlobImage(File file){
        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream(file.getAbsolutePath());
            return (Blob) fileIn;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller_JFConfiguracionInicial.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fileIn!=null)
                try {
                    fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller_JFConfiguracionInicial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (Blob) fileIn;
    }
  
    
}
