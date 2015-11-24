/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CLogin;
import MODELO.CUsuario;
import VISTA.JFInfoEmpelado;
import app_carshop.App_carshop;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Byter
 */
public class Controller_AgregarEmpleado {
    JFInfoEmpelado info_empleado;
    File fileSelected;
    
    public Controller_AgregarEmpleado(CLogin login,Connection cn){
        info_empleado = new JFInfoEmpelado();
        info_empleado.label_prop.setText("Agregar Empleado");
        //Chooser image for the  empleado
        this.info_empleado.ChooserImageGerente.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                    
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(info_empleado);
                    fileSelected = fileChooser.getSelectedFile();
            
                    //poner imagen en label
                     ImageIcon image;
                    try{
                     image = new ImageIcon(fileSelected.getAbsolutePath());
                    }catch(NullPointerException er){
                     image = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
                    }
                    info_empleado.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(info_empleado.ChooserImageGerente.getWidth(),info_empleado.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
        
            }
        });
        
         //Button the save addEmpleado
        this.info_empleado.btn_guardarUsuario.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                CUsuario usuario = new CUsuario();
                CLogin login = new CLogin();
                // DATOS OBTENIDOS DE LA VENTANA DE AGREGAR GERENTE
                String nombre = info_empleado.txtNombreG.getText();
                String apellido_pat = info_empleado.txtApellidoPG.getText();
                String apellido_mat = info_empleado.txtApellidoMG.getText();
                String direccion = info_empleado.txtDireccionG.getText();
                String telefono = info_empleado.txtTelefonoG.getText();        
                String txtUsuario = info_empleado.txtUsuario.getText();
                String txtPass = info_empleado.txtP_Password.getText();
                String txtPassC = info_empleado.txtP_PasswordConfirm.getText();
                
                
                
                float salario;
                try{
                salario = Float.parseFloat((info_empleado.txtSalarioG.getText().equals(""))?"0":info_empleado.txtSalarioG.getText());
                }catch(NumberFormatException err){
                salario = 0;
                }
                String tipo = info_empleado.cbox_departamento.getSelectedItem().toString();
                
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
                    usuario.setClave_elector(info_empleado.txtClaveElector.getText());
                //Set data in object Login
                    login.setUsuario(txtUsuario);
                    login.setPassword(txtPass);
                    login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                    login.setNombreImagen(fileSelected.getName());
                //Verify data login & user
                  
                 if (usuario.validarDatos(info_empleado,cn) & login.validarDatos(info_empleado, txtPass,cn) ) {
                       if(!txtPass.equals(txtPassC)){
                            JOptionPane.showMessageDialog(info_empleado,"Confirmacion de contraseña invalida","Mensaje de error",JOptionPane.ERROR_MESSAGE);
                        }else{
                           usuario.saveObject(cn);
                           usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                           //Crear su login  y guardar
                           login.setClave_elector(usuario.getClave_elector());
                           login.saveObject(cn);
                           JOptionPane.showMessageDialog(info_empleado,"Empleado agregado", "Mensaje de informacion", JOptionPane.INFORMATION_MESSAGE);
                          
                            Controller_JFGerenteHome gerenteHome = new Controller_JFGerenteHome(login, cn);
                            info_empleado.dispose();
                           
                       }
                      
                }
            }               
        });
        
    //Button the cancel addEmpleado
        this.info_empleado.btn_cancelar.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFGerenteHome gerenteHome = new Controller_JFGerenteHome(login, cn);
                info_empleado.dispose();
            }
        });
       //Al cerrar el frame
        this.info_empleado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(info_empleado,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    info_empleado.dispose();
                }
            }
        });
    
    }
    
       
    
    public void cleanFields(){
       info_empleado.txtUsuario.setText(null);
       info_empleado.txtP_Password.setText(null);
       info_empleado.txtP_PasswordConfirm.setText(null);
       
       info_empleado.txtNombreG.setText(null);
       info_empleado.txtApellidoPG.setText(null);
       info_empleado.txtApellidoMG.setText(null);
       info_empleado.txtDireccionG.setText(null);
       info_empleado.txtTelefonoG.setText(null);
       info_empleado.txtSalarioG.setText(null);
       info_empleado.txtClaveElector.setText(null);
       info_empleado.ChooserImageGerente.setIcon(null);
       fileSelected = null;
    }
    
}
