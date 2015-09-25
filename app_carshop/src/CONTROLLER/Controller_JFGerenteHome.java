/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.CLogin;
import VIEW.JFGerenteHome;
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
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFGerenteHome {
    JFGerenteHome viewGerenteHome;
    Connection cn;
    CLogin login;
    File fileSelected;
    public Controller_JFGerenteHome(CLogin login,Connection cn){
        viewGerenteHome = new JFGerenteHome();
        this.login = login;
        this.cn = cn;
        //Settings view labels identifications
        this.viewGerenteHome.label_usuario.setText("Bienvenido "+login.getUsuario());
        this.viewGerenteHome.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewGerenteHome.label_ImageEmpleado.getWidth(),viewGerenteHome.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        
        this.viewGerenteHome.ChooserImageGerente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                 JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(viewGerenteHome);
                    fileSelected = fileChooser.getSelectedFile();
                    
                    //poner imagen en label
                    ImageIcon image = new ImageIcon(fileSelected.getAbsolutePath());
                    viewGerenteHome.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(viewGerenteHome.ChooserImageGerente.getWidth(),viewGerenteHome.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        
            }
        });
        //Button the save addEmpleado
        this.viewGerenteHome.btn_guardarUsuario.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        //Button the cancel addEmpleado
        this.viewGerenteHome.btn_cancelar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                cleanFields();
            }
        });
        
        //Button the settings in this account
        this.viewGerenteHome.btn_settings.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        
        //Boton para consultar clientes
        this.viewGerenteHome.btn_clientes.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        
        //Boton para consultar empleados
        this.viewGerenteHome.btn_empleados.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
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
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Confirnar cierre de sesion?","Cerrar sesion",JOptionPane.YES_NO_OPTION);
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
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Desea salir de la app?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewGerenteHome.dispose();
                }
                
            }
        });
    }
    
    private void cleanFields(){
        viewGerenteHome.txtUsuario.setText(null);
        viewGerenteHome.txtP_Password.setText(null);
        viewGerenteHome.txtP_PasswordConfirm.setText(null);
        viewGerenteHome.txtNombreG.setText(null);
        viewGerenteHome.txtApellidoMG.setText(null);
        viewGerenteHome.txtApellidoPG.setText(null);
        viewGerenteHome.txtDireccionG.setText(null);
        viewGerenteHome.txtTelefonoG.setText(null);
        viewGerenteHome.txtSalarioG.setText(null);
        viewGerenteHome.ChooserImageGerente.setIcon(null);
        fileSelected = null;
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
