/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

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
                     ImageIcon image;
                    try{
                     image = new ImageIcon(fileSelected.getAbsolutePath());
                    }catch(NullPointerException er){
                       image = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
                    }
                   viewSettingsDB.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(viewSettingsDB.ChooserImageGerente.getWidth(),viewSettingsDB.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
                    
                }
            });
        //Boton para crear la cuenta de un gerente
        this.viewSettingsDB.btn_guardarGerente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CUsuario usuario = new CUsuario();
                CLogin login = new CLogin();
                // DATOS OBTENIDOS DE LA VENTANA DE AGREGAR GERENTE
                String nombre = viewSettingsDB.txtNombreG.getText();
                String apellido_pat = viewSettingsDB.txtApellidoPG.getText();
                String apellido_mat = viewSettingsDB.txtApellidoMG.getText();
                String direccion = viewSettingsDB.txtDireccionG.getText();
                String telefono = viewSettingsDB.txtTelefonoG.getText();        
                String txtUsuario = viewSettingsDB.txtUsuario.getText();
                String txtPass = viewSettingsDB.txtP_Password.getText();
                String txtPassC = viewSettingsDB.txtP_PasswordConfirm.getText();
                float salario;
                try{
                salario = Float.parseFloat((viewSettingsDB.txtSalarioG.getText().equals(""))?"0":viewSettingsDB.txtSalarioG.getText());
                }catch(NumberFormatException err){
                salario = 0;
                }
                String tipo = "Gerente";
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
                    usuario.setClave_elector(viewSettingsDB.txtClaveElector.getText());
                //Set data in object Login
                    login.setUsuario(txtUsuario);
                    login.setPassword(txtPass);
                    login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                    login.setNombreImagen(fileSelected.getName());
                //Verify data login & user
                  
                 if (usuario.validarDatos(viewSettingsDB,cn) & login.validarDatos(viewSettingsDB, txtPass,cn) ) {
                        if(CUsuario.ifExistsTipo(cn,"Gerente")){

                           int resp = JOptionPane.showConfirmDialog(viewSettingsDB, "¿Ya existe un gerente,dar de baja y poner este como nuevo gerente?","Informacion",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
                               if(resp == JOptionPane.YES_OPTION){
                                   CUsuario.bajaEstado(cn,"Gerente");
                                   usuario.saveObject(cn);
                                   usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                                   //Crear su login  y guardar
                                   login.setClave_elector(usuario.getClave_elector());
                                   login.saveObject(cn);
                                   cleanFields();
                                   JOptionPane.showMessageDialog(viewSettingsDB,"El gerente fue agregado satisfactoriamente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);

                               }

                        }else{
                            
                           usuario.saveObject(cn);
                           usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                           //Crear su login  y guardar
                           login.setClave_elector(usuario.getClave_elector());
                           login.saveObject(cn);
                           cleanFields();
                           JOptionPane.showMessageDialog(viewSettingsDB,"El gerente fue agregado satisfactoriamente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);


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
       viewSettingsDB.txtClaveElector.setText(null);
       viewSettingsDB.ChooserImageGerente.setIcon(null);
       fileSelected = null;
    }
    
}
