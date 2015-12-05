/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CLogin;
import MODELO.CUsuario;
import VISTA.JFModificarEmpleado;
import app_carshop.app_carshop;
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
public class Controller_JFModificarEmpleado {
    Connection cn;
    JFModificarEmpleado viewUpdateEmpleado;
    public Controller_JFModificarEmpleado(CLogin login,Connection cn,String claveElector,String nombre){
        viewUpdateEmpleado = new JFModificarEmpleado();
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
                    Controller_JFListarEmpleados viewshowEmpleados = new  Controller_JFListarEmpleados(login, cn);
                     viewUpdateEmpleado.dispose();
                }else{
                    JOptionPane.showMessageDialog(viewUpdateEmpleado,"Error verificar los datos","Mensaje de error",JOptionPane.ERROR_MESSAGE);
                }
                        
            }
        });
        this.viewUpdateEmpleado.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewUpdateEmpleado,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    viewUpdateEmpleado.dispose();
                }
            }
        });
        this.viewUpdateEmpleado.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFListarEmpleados viewshowEmpleados = new  Controller_JFListarEmpleados(login, cn);
                viewUpdateEmpleado.dispose();
            }
        });
        
    }
    
}
