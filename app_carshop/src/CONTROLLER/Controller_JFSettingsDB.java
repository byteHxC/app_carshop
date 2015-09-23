/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.CUsuario;
import MODEL.CLogin;
import MODEL.ConnectionMySQL;
import MODEL.UserROOT;
import VIEW.JFSettingsDB;
import app_carshop.App_carshop;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFSettingsDB {
    JFSettingsDB viewSettingsDB;
    Connection cn;
    UserROOT userRoot;
    File fileSelected;
    
    public Controller_JFSettingsDB(Connection cn){
        this.viewSettingsDB = new JFSettingsDB();
        this.viewSettingsDB.label_usuario.setText( "USUARIO: root@carshop");
        this.cn = cn;
        fileSelected = null;
        userRoot = UserROOT.getROOTDB();
        
        //Boton para seleccionar imagen del gerente
            this.viewSettingsDB.ChooserImageGerente.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(viewSettingsDB);
                    fileSelected = fileChooser.getSelectedFile();
                    
                    //poner imagen en label
                    ImageIcon image = new ImageIcon(fileSelected.getAbsolutePath());
                    viewSettingsDB.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(viewSettingsDB.ChooserImageGerente.getWidth(),viewSettingsDB.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        
                }
            });
        //Boton para crear la cuenta de un gerente
        this.viewSettingsDB.btn_guardarGerente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CUsuario usuario = new CUsuario();
                boolean userSuc = false;
                CLogin login = new CLogin();
                
                String nombre = viewSettingsDB.txtNombreG.getText();
                String apellido_pat = viewSettingsDB.txtApellidoPG.getText();
                String apellido_mat = viewSettingsDB.txtApellidoMG.getText();
                String direccion = viewSettingsDB.txtDireccionG.getText();
                String telefono = viewSettingsDB.txtTelefonoG.getText();
                float salario = Float.parseFloat(viewSettingsDB.txtSalarioG.getText());
                String tipo = "Gerente";
                //if(nombre.length()>5 && apellido_mat.length()>5 && apellido_pat.length()>5 && direccion.length()>10 && telefono.length()>5 && salario>0){
                    usuario.setNombre(nombre);
                    usuario.setApellido_pat(apellido_pat);
                    usuario.setApellido_mat(apellido_mat);
                    usuario.setDireccion(direccion);
                    usuario.setTelefono(telefono);
                    usuario.setSalario(salario);
                    usuario.setTipo(tipo);
                    userSuc = true;
                //}
                
                
                String erroresR= "Llene bien los siguientes campos: \n";
                String txtUsuario = viewSettingsDB.txtUsuario.getText();
                String txtPass = viewSettingsDB.txtP_Password.getText();
                String txtPassC = viewSettingsDB.txtP_PasswordConfirm.getText();
                if(fileSelected==null){
                    fileSelected = new File("//ASSETS//user168-1.png");
                }
                //Validacion login
                if(txtUsuario.length()>=8){
                    if(txtPass.equals(txtPassC)){
                        if(txtPass.length()<6) erroresR+="\t-La contraseña debe contener 6 o mas caracteres\n";
                        else{
                          
                         if(userSuc == true){
                               //Se guarda usuario 
                                usuario.saveObject(cn);
                                usuario = CUsuario.getObject(usuario.getNombre(), usuario.getApellido_pat(), usuario.getApellido_mat(), cn);
                               //Crear su login  y guardar
                               login.setId_usuario(usuario.getId_usuario());
                               login.setUsuario(txtUsuario);
                               login.setPassword(txtPass);
                               login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                               login.setNombreImagen(fileSelected.getName());
                               login.saveObject(cn);
                               cleanFields();
                             
                         }else erroresR+="\n-Corroborar los datos del gerente";
                        }
                    }else erroresR+="\t-La confirmacion de contraseña es incorrecta\n";
                    
                }else erroresR+="\t-El nombre de usuario debe ser mayor o igual a 8 caracteres\n";
                
                JOptionPane.showMessageDialog(viewSettingsDB,erroresR,"Corregir datos",JOptionPane.WARNING_MESSAGE);
               
                
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
        //Boton de guardar configuracion de la base de datos
        this.viewSettingsDB.btn_guardarDB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userRoot.setIp( viewSettingsDB.txtIP.getText());
                userRoot.setPort( viewSettingsDB.txtPort.getText());
                userRoot.setUserDB(viewSettingsDB.txtUserDB.getText());
                userRoot.setPassDB(viewSettingsDB.txtPassDB.getText());
                
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
            Logger.getLogger(Controller_JFSettingsDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fileIn!=null)
                try {
                    fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(Controller_JFSettingsDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return (Blob) fileIn;
    }
    public void cleanFields(){
       viewSettingsDB.txtUsuario.setText(null);
       viewSettingsDB.txtP_Password.setText(null);
       viewSettingsDB.txtP_PasswordConfirm.setText(null);
       
       viewSettingsDB.txtNombreG.setText(null);
       viewSettingsDB.txtApellidoPG.setText(null);
       viewSettingsDB.txtApellidoMG.setText(null);
       viewSettingsDB.txtDireccionG.setText(null);
       viewSettingsDB.txtTelefonoG.setText(null);
       viewSettingsDB.txtSalarioG.setText(null);
    }
}
