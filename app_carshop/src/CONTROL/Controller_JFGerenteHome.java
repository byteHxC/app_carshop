/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CLogin;
import MODEL.CUsuario;
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
    File fileSelected;
    
    public Controller_JFGerenteHome(CLogin login,Connection cn){
        this.viewGerenteHome = new JFGerenteHome();
        this.cn = cn;
        //Settings view labels identifications
        this.viewGerenteHome.label_usuario.setText("Bienvenido "+login.getUsuario());
        this.viewGerenteHome.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewGerenteHome.label_ImageEmpleado.getWidth(),viewGerenteHome.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
       
        //Chooser image for the  empleado
        this.viewGerenteHome.ChooserImageGerente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                    
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(viewGerenteHome);
                    fileSelected = fileChooser.getSelectedFile();
            
                    //poner imagen en label
                     ImageIcon image;
                    try{
                     image = new ImageIcon(fileSelected.getAbsolutePath());
                    }catch(NullPointerException er){
                     image = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
                    }
                    viewGerenteHome.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(viewGerenteHome.ChooserImageGerente.getWidth(),viewGerenteHome.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        
            }
        });
        //Button the save addEmpleado
        this.viewGerenteHome.btn_guardarUsuario.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                CUsuario usuario = new CUsuario();
                CLogin login = new CLogin();
                // DATOS OBTENIDOS DE LA VENTANA DE AGREGAR GERENTE
                String nombre = viewGerenteHome.txtNombreG.getText();
                String apellido_pat = viewGerenteHome.txtApellidoPG.getText();
                String apellido_mat = viewGerenteHome.txtApellidoMG.getText();
                String direccion = viewGerenteHome.txtDireccionG.getText();
                String telefono = viewGerenteHome.txtTelefonoG.getText();        
                String txtUsuario = viewGerenteHome.txtUsuario.getText();
                String txtPass = viewGerenteHome.txtP_Password.getText();
                String txtPassC = viewGerenteHome.txtP_PasswordConfirm.getText();
                float salario;
                try{
                salario = Float.parseFloat((viewGerenteHome.txtSalarioG.getText().equals(""))?"0":viewGerenteHome.txtSalarioG.getText());
                }catch(NumberFormatException err){
                salario = 0;
                }
                String tipo = viewGerenteHome.cbox_departamento.getSelectedItem().toString();
                
                if(fileSelected==null){
                    fileSelected = new File("src//ASSETS//user168-1.png");
                }
                
                
                //Set data in object Usuario
                    usuario.setNombre(nombre);
                    usuario.setApellido_pat(apellido_pat);
                    usuario.setApellido_mat(apellido_mat);
                    usuario.setDireccion(direccion);
                    usuario.setTelefono(telefono);
                    usuario.setSalario(salario);
                    usuario.setTipo(tipo);
                    usuario.setEstado(true);
                    usuario.setClave_elector(viewGerenteHome.txtClaveElector.getText());
                //Set data in object Login
                    login.setUsuario(txtUsuario);
                    login.setPassword(txtPass);
                    login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                    login.setNombreImagen(fileSelected.getName());
                //Verify data login & user
                  
                 if (usuario.validarDatos(viewGerenteHome,cn) & login.validarDatos(viewGerenteHome, txtPass,cn) ) {
                       usuario.saveObject(cn);
                       usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                       //Crear su login  y guardar
                       login.setClave_elector(usuario.getClave_elector());
                       login.saveObject(cn);
                       JOptionPane.showMessageDialog(viewGerenteHome,"Empleado agregado", "Mensaje de informacion", JOptionPane.INFORMATION_MESSAGE);
                       cleanFields();
                      
                }
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
        
        //Boton para consultar empleados
        this.viewGerenteHome.btn_empleados.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
               Controller_JFShowEmpleados jshowEmpleados = new Controller_JFShowEmpleados(login, cn);
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
    
    public void cleanFields(){
       viewGerenteHome.txtUsuario.setText(null);
       viewGerenteHome.txtP_Password.setText(null);
       viewGerenteHome.txtP_PasswordConfirm.setText(null);
       
       viewGerenteHome.txtNombreG.setText(null);
       viewGerenteHome.txtApellidoPG.setText(null);
       viewGerenteHome.txtApellidoMG.setText(null);
       viewGerenteHome.txtDireccionG.setText(null);
       viewGerenteHome.txtTelefonoG.setText(null);
       viewGerenteHome.txtSalarioG.setText(null);
       viewGerenteHome.txtClaveElector.setText(null);
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
