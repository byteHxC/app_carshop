/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CLogin;
import MODEL.UserROOT;
import VIEW.JFSettingsUser;
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
public class Controller_JFSettingsUser {
    JFSettingsUser viewSettingsUser;
    Connection cn;
    File fileSelected;
    
    //Constructor usuario root
    public Controller_JFSettingsUser(UserROOT root,Connection cn){
        fileSelected = null;
        viewSettingsUser = new JFSettingsUser();
        this.cn = cn;
        viewSettingsUser.label_usuario.setText("USUARIO: "+root.getUser());
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
                           root.setPassword(pass);
                           root.saveObject();
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
                
                Controller_JFConfiguracionInicial controllerSettingsDB = new Controller_JFConfiguracionInicial(cn,root);
                viewSettingsUser.dispose();
            
            }
        });
        
        //Cerrar desde jframe
        this.viewSettingsUser.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFConfiguracionInicial controllerSettingsDB = new Controller_JFConfiguracionInicial(cn,root);
                viewSettingsUser.dispose();
            }
        });
    }
    
    //Constructor usuario empleado
    
    public Controller_JFSettingsUser(String JFController, CLogin login, Connection cn){
        viewSettingsUser = new JFSettingsUser();
        this.cn = cn;
        viewSettingsUser.label_usuario.setText("USUARIO: "+login.getUsuario());
        viewSettingsUser.txtUsuario.setText(login.getUsuario());
        viewSettingsUser.ChooserImageGerente.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewSettingsUser.ChooserImageGerente.getWidth(),viewSettingsUser.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        //Boton para escoger imagen
        this.viewSettingsUser.ChooserImageGerente.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(viewSettingsUser);
                    fileSelected = fileChooser.getSelectedFile();
                    
                    //poner imagen en label
                     ImageIcon image;
                    try{
                     image = new ImageIcon(fileSelected.getAbsolutePath());
                    }catch(NullPointerException er){
                     image = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
                    }
                   viewSettingsUser.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(viewSettingsUser.ChooserImageGerente.getWidth(),viewSettingsUser.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));

            }
        });
        //Boton de guardar cambios
        this.viewSettingsUser.btn_guardar.addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent e) {     
                String pass = viewSettingsUser.txtP_Password.getText();
                String passConfirm = viewSettingsUser.txtP_PasswordConfirm.getText();
                String usuario = viewSettingsUser.txtUsuario.getText();
                if(validarDatos(login,pass, passConfirm, usuario)){
                     int resp = JOptionPane.showConfirmDialog(viewSettingsUser,"¿Para realizar este proceso necesita cerrar sesion, esta de acuerdo? ","Mensaje",JOptionPane.YES_OPTION);
                        if(resp == JOptionPane.YES_OPTION){
                             if(fileSelected==null){
                                fileSelected = new File("src//ASSETS//user168-1.png");
                                login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                                login.setNombreImagen(fileSelected.getName());
                             }else{
                                  login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                                  login.setNombreImagen(fileSelected.getName());
                             }
                               login.setPassword(pass);
                               login.setUsuario(usuario);
                               login.updateObject(cn);
                              
                               viewSettingsUser.dispose();   
                               App_carshop.init();
                        }
                }
                
                       
                  
                }
        });
        
     //boton de cancelar
        this.viewSettingsUser.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                switch(JFController){
                    case ("ControllerGerente"):
                         Controller_JFGerenteHome JFGerenteHome = new Controller_JFGerenteHome(login, cn);
                            viewSettingsUser.dispose();
                        break;
                    case ("ControllerComercio"):
                         Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                            viewSettingsUser.dispose();
                        break;
                    case ("ControllerFinanciamiento"):
                         
                        break;                        
                        
                }
               
            
            }
        });
        
        //Cerrar desde jframe
        this.viewSettingsUser.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
               switch(JFController){
                    case ("ControllerGerente"):
                         Controller_JFGerenteHome JFGerenteHome = new Controller_JFGerenteHome(login, cn);
                            viewSettingsUser.dispose();
                        break;
                    case ("ControllerComercio"):
                         Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                            viewSettingsUser.dispose();
                        break;
                    case ("ControllerFinanciamiento"):
                         
                        break;                        
                        
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
            Logger.getLogger(Controller_JFSettingsUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return image;
     }
      
      public boolean validarDatos(CLogin login,String password,String confirmationPass,String usuario){
        String txtError = "Verificar datos login\n";
        Boolean errores = false;
        if(CLogin.existsUsuario(cn,usuario) && !usuario.equals(login.getUsuario())){
            txtError+="\t-El usuario ya existe elija otro";
            errores = true;
        }
        if(password.length() < 6){
            txtError += "\t-La contraseña debe ser mayor a 6 caracteres\n";
            errores = true;
        }
        
        if(!(password.equals(confirmationPass))){
            txtError += "\t-La confirmacion de contraseña es diferente\n";
            errores = true;
        }
         if(!(usuario.matches("[a-zA-Z0-9]+[@][a-zA-Z]+"))){
             txtError += "\t-El nombre de usuario debe tener mas de 8 caracteres.\n";
             errores= true;
         }
         if(errores){
              JOptionPane.showMessageDialog(viewSettingsUser, txtError, "Validación de datos del login", JOptionPane.WARNING_MESSAGE);
               return false;
         }
         return true;
    }
}
