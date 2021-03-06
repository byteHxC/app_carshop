/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CLogin;
import VISTA.JFComercioHome;
import app_carshop.app_carshop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
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
public class Controller_JFComercioHome {
    Connection cn;
    JFComercioHome viewComercio;
    
    public Controller_JFComercioHome(CLogin login,Connection cn){
        this.viewComercio = new JFComercioHome();
        this.cn = cn;
        
        //Settings view labels identifications
        this.viewComercio.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewComercio.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewComercio.label_ImageEmpleado.getWidth(),viewComercio.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        //Visualziar catalogo de autos
        this.viewComercio.btn_verAutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_ListaAutos listaAutos = new Controller_ListaAutos(login, cn);
                viewComercio.dispose();
            }
        });
        //Button of  addCompra
        this.viewComercio.btn_addCompra.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAgregarCompra JFAddCompra = new Controller_JFAgregarCompra(login, cn);
                viewComercio.dispose();
            }
        });
        //Button of addVenta
        this.viewComercio.btn_addVenta.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
            Controller_JFAgregarVenta JFAddVenta = new Controller_JFAgregarVenta(login,cn);
            viewComercio.dispose();
            
            }
        });
        //Button of options cliente
        this.viewComercio.btn_cliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               //viewComercio.dialog_opcionesCliente.setVisible(true);
                 viewComercio.dialog_opcionesCliente.dispose();
                Controller_JFAgregarCliente agregarCliente = new Controller_JFAgregarCliente(login, cn);
                viewComercio.dispose();
            }
        });
        //Button cancel options cliente
        this.viewComercio.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewComercio.dialog_opcionesCliente.dispose();
            }
        });
        //Button in option add cliente
        this.viewComercio.btn_addCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewComercio.dialog_opcionesCliente.dispose();
                Controller_JFAgregarCliente agregarCliente = new Controller_JFAgregarCliente(login, cn);
                viewComercio.dispose();
            }
        });
        //Button un option mod_cliente
        this.viewComercio.btn_modCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //Pendiente modificar cliente
            }
        });
         //Button the settings in this account
        this.viewComercio.btn_settings.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsUser JFSettingsUser = new Controller_JFSettingsUser("ControllerComercio",login, cn);
                viewComercio.dispose();
            }
        });
        
        this.viewComercio.btn_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            int resp = JOptionPane.showConfirmDialog(viewComercio,"¿Confirmar cierre de sesion?","Cerrar sesion",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    viewComercio.dispose();
                    app_carshop.init();
                }
            }
        });
        
        this.viewComercio.addWindowListener(new WindowAdapter() {
            @Override
             public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewComercio,"¿Si sale de aqui, se cerrara su sesion?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    viewComercio.dispose();
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
            Logger.getLogger(Controller_JFComercioHome.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
}
