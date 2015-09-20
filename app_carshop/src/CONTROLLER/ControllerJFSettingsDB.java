/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import VIEW.JFSettingsDB;
import app_carshop.App_carshop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class ControllerJFSettingsDB {
    JFSettingsDB viewSettingsDB;
    Connection cn;
    
    public ControllerJFSettingsDB(Connection cn){
        this.viewSettingsDB = new JFSettingsDB();
        this.viewSettingsDB.label_usuario.setText( "USUARIO: root@carshop");
        this.cn = cn;
        
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
                App_carshop.init();
                viewSettingsDB.dispose();
            }
        });
    }
}
