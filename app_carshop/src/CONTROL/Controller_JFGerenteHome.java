/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CLogin;
import VISTA.JFGerenteHome;
import app_carshop.App_carshop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFGerenteHome {
    JFGerenteHome viewGerenteHome;
    File fileSelected;
    
    public Controller_JFGerenteHome(CLogin login,Connection cn){
        this.viewGerenteHome = new JFGerenteHome();
        //Settings view labels identifications
        this.viewGerenteHome.label_usuario.setText("Bienvenido "+login.getUsuario());
        this.viewGerenteHome.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewGerenteHome.label_ImageEmpleado.getWidth(),viewGerenteHome.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        //evento para instanciar controlador agregarempleado
        this.viewGerenteHome.btn_addEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
                Controller_AgregarEmpleado agregarEmpleado = new Controller_AgregarEmpleado(login, cn);
                viewGerenteHome.dispose();
            }
        });
        //evento para instanciar controlador modificar empleados
        this.viewGerenteHome.btn_listEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
                Controller_JFListarEmpleados listarEmpleados = new Controller_JFListarEmpleados(login, cn);
                viewGerenteHome.dispose();
            }
        });
        //Evento al precionar btn_empleados, desplegar opciones
        this.viewGerenteHome.btn_empleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.setVisible(true);
            }
        });
        //cancelar despligue de opciones en empleado
        this.viewGerenteHome.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
            }
            
        });
       
        
        //Button the settings in this account
        this.viewGerenteHome.btn_settings.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsUser JFSettingsUser = new Controller_JFSettingsUser("ControllerGerente",login, cn);
                viewGerenteHome.dispose();
            }
        });
        
        //Boton para consultar clientes
        this.viewGerenteHome.btn_clientes.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSearchCliente JFSearchCliente = new Controller_JFSearchCliente(login, cn,"Show");
                viewGerenteHome.dispose();
                
            }
        });
        
       
        
        //Boton para consultar ventas
        this.viewGerenteHome.btn_ventas.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            
            }
        });
        
        //Boton para consultar compras
        this.viewGerenteHome.btn_compras.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            
            }
        });
        //Boton de cerrar cesion
        this.viewGerenteHome.btn_logout.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Confirmar cierre de sesion?","Cerrar sesion",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    viewGerenteHome.dispose();
                    App_carshop.init();
                }
            }
        });
        //Boton al cerrar al frame
        this.viewGerenteHome.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Si sale de aqui, se cerrara su sesion?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewGerenteHome.dispose();
                }
                
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
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Controller_JFGerenteHome.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
}
