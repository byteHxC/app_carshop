/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CCliente;
import MODELO.CLogin;
import VISTA.JFInfoCliente;
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
public class Controller_JFAgregarCliente {
    JFInfoCliente viewAddCliente;
    
    //Constructor for empleado comercio
    public Controller_JFAgregarCliente(CLogin login,Connection cn){
        this.viewAddCliente = new JFInfoCliente();
        //When press btn_guardar
        this.viewAddCliente.btn_guardarCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CCliente cliente = new CCliente();
                cliente.setClaveElector(viewAddCliente.txt_cveElector.getText());
                cliente.setNombre(viewAddCliente.txt_nombre.getText());
                cliente.setApellido_pat(viewAddCliente.txt_apellidoPat.getText());
                cliente.setApellido_mat(viewAddCliente.txt_apellidoMat.getText());
                cliente.setTelefono(viewAddCliente.txt_telefono.getText());
                cliente.setRfc(viewAddCliente.txt_RFC.getText());
                cliente.setDireccion(viewAddCliente.txt_direccion.getText());
                 float ing;
                try{
                    ing = Float.parseFloat(viewAddCliente.txt_ingresoMensual.getText());
                }catch(NumberFormatException err){
                    ing = -1;
                }
                cliente.setIngresoMensual(ing);
                
                if(cliente.validarDatos(viewAddCliente, cn)){
                    cliente.saveObject(cn);
                    JOptionPane.showMessageDialog(viewAddCliente,"El cliente fue agregado satisfactoriamente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);
                    Controller_JFComercioHome JComercioHome = new Controller_JFComercioHome(login, cn);
                    viewAddCliente.dispose();
                }
            }
        });
        //Cuando presione cancelar
        this.viewAddCliente.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                  viewAddCliente.dispose();  
            }
        });
        
        this.viewAddCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAddCliente,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    viewAddCliente.dispose();
                }
            }
        });
    }
     
}
