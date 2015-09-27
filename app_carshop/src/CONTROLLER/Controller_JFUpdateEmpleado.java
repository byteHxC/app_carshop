/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROLLER;

import MODEL.CLogin;
import MODEL.CUsuario;
import VIEW.JFUpdateEmpleado;
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
public class Controller_JFUpdateEmpleado {
    Connection cn;
    JFUpdateEmpleado viewUpdateEmpleado;
    public Controller_JFUpdateEmpleado(CLogin login,Connection cn,String claveElector,String nombre){
        viewUpdateEmpleado = new JFUpdateEmpleado();
        viewUpdateEmpleado.labelUsuario.setText("Editar usuario: "+nombre);
        this.cn = cn;
        
        this.viewUpdateEmpleado.btn_updateUsuario.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                String direccion = viewUpdateEmpleado.txtDireccion.getText();
                String telefono = viewUpdateEmpleado.txtTelefono.getText();
                float salario;
                try{
                    salario = Float.parseFloat(viewUpdateEmpleado.txtSalario.getText());
                }catch(Exception er){
                    salario = 0;
                }
                if(CUsuario.updateObject(claveElector,direccion, telefono, salario, cn)){
                    JOptionPane.showMessageDialog(viewUpdateEmpleado,"Empleado actualizado","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);
                    Controller_JFShowEmpleados viewshowEmpleados = new  Controller_JFShowEmpleados(login, cn);
                     viewUpdateEmpleado.dispose();
                }else{
                    JOptionPane.showMessageDialog(viewUpdateEmpleado,"Error verificar los datos","Mensaje de error",JOptionPane.ERROR_MESSAGE);
                }
                        
            }
        });
        this.viewUpdateEmpleado.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFShowEmpleados viewshowEmpleados = new  Controller_JFShowEmpleados(login, cn);
                viewUpdateEmpleado.dispose();
            }
        });
        this.viewUpdateEmpleado.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFShowEmpleados viewshowEmpleados = new  Controller_JFShowEmpleados(login, cn);
                viewUpdateEmpleado.dispose();
            }
        });
        
    }
    
}
