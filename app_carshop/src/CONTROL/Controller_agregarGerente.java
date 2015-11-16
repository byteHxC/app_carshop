/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CLogin;
import MODEL.CUsuario;
import MODEL.UserROOT;
import VIEW.JFInfoEmpelado;
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
public class Controller_agregarGerente {
     JFInfoEmpelado infoEmpleado;
     
    File fileSelected;
    public Controller_agregarGerente(Connection cn,UserROOT root){
          fileSelected = null;
          infoEmpleado = new JFInfoEmpelado();
          infoEmpleado.label_prop.setText("Agregar gerente");
          infoEmpleado.cbox_departamento.addItem("Gerente");
          infoEmpleado.cbox_departamento.setSelectedIndex(2);
          infoEmpleado.cbox_departamento.setEnabled(false);
          //Boton para seleccionar imagen del gerente
            this.infoEmpleado.ChooserImageGerente.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filterChooser = new FileNameExtensionFilter("Imagenes","jpg","png");
                    fileChooser.setFileFilter(filterChooser);
                    fileChooser.showOpenDialog(infoEmpleado);
                    fileSelected = fileChooser.getSelectedFile();
                    
                    //poner imagen en label
                     ImageIcon image;
                    try{
                     image = new ImageIcon(fileSelected.getAbsolutePath());
                    }catch(NullPointerException er){
                       image = new ImageIcon(getClass().getResource("/ASSETS/user168-1.png"));
                    }
                   infoEmpleado.ChooserImageGerente.setIcon(new ImageIcon(image.getImage().getScaledInstance(infoEmpleado.ChooserImageGerente.getWidth(),infoEmpleado.ChooserImageGerente.getHeight(),Image.SCALE_SMOOTH)));
                    
                }
            });
            //Boton para crear la cuenta de un gerente
        this.infoEmpleado.btn_guardarUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CUsuario usuario = new CUsuario();
                CLogin login = new CLogin();
                // DATOS OBTENIDOS DE LA VENTANA DE AGREGAR GERENTE
                String nombre = infoEmpleado.txtNombreG.getText();
                String apellido_pat = infoEmpleado.txtApellidoPG.getText();
                String apellido_mat = infoEmpleado.txtApellidoMG.getText();
                String direccion = infoEmpleado.txtDireccionG.getText();
                String telefono = infoEmpleado.txtTelefonoG.getText();        
                String txtUsuario = infoEmpleado.txtUsuario.getText();
                String txtPass = infoEmpleado.txtP_Password.getText();
                String txtPassC = infoEmpleado.txtP_PasswordConfirm.getText();
                float salario;
                try{
                salario = Float.parseFloat((infoEmpleado.txtSalarioG.getText().equals(""))?"0":infoEmpleado.txtSalarioG.getText());
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
                    usuario.setClave_elector(infoEmpleado.txtClaveElector.getText());
                //Set data in object Login
                    login.setUsuario(txtUsuario);
                    login.setPassword(txtPass);
                    login.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                    login.setNombreImagen(fileSelected.getName());
                //Verify data login & user
                  
                 if (usuario.validarDatos(infoEmpleado,cn) & login.validarDatos(infoEmpleado, txtPass,cn) ) {
                        if(CUsuario.ifExistsTipo(cn,"Gerente")){

                           int resp = JOptionPane.showConfirmDialog(infoEmpleado, "¿Ya existe un gerente,dar de baja y poner este como nuevo gerente?","Informacion",JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
                               if(resp == JOptionPane.YES_OPTION){
                                   CUsuario.bajaEstado(cn,"Gerente");
                                   usuario.saveObject(cn);
                                   usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                                   //Crear su login  y guardar
                                   login.setClave_elector(usuario.getClave_elector());
                                   login.saveObject(cn);
                                   infoEmpleado.cleanFields();
                                    fileSelected = null;
                                   JOptionPane.showMessageDialog(infoEmpleado,"El gerente fue agregado satisfactoriamente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);

                               }else{
                                   JOptionPane.showMessageDialog(infoEmpleado,"No hubo modificaciones en el sistema!","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);

                               }

                        }else{
                            
                           usuario.saveObject(cn);
                           usuario = CUsuario.getObject(usuario.getClave_elector(), cn);
                           //Crear su login  y guardar
                           login.setClave_elector(usuario.getClave_elector());
                           login.saveObject(cn);
                           infoEmpleado.cleanFields();
                           fileSelected = null;
                           JOptionPane.showMessageDialog(infoEmpleado,"El gerente fue agregado satisfactoriamente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);


                     }
                }
            }
        });
        
        //Al cerrar el frame
        this.infoEmpleado.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(infoEmpleado,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    infoEmpleado.dispose();
                }
            }
        });
        this.infoEmpleado.btn_cancelar.addMouseListener(new MouseAdapter() {
              @Override
              public void mouseClicked(MouseEvent e) {
                  Controller_JFConfiguracionInicial controllerJFSettingsDB = new Controller_JFConfiguracionInicial(cn,root);
                   infoEmpleado.dispose();
              }  
        });
    }
     
}
