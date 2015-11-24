/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CCompra;
import MODELO.CLogin;
import MODELO.CUsuario;
import MODELO.CVenta;
import VISTA.JFInfoCliente;
import app_carshop.App_carshop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Byter
 */
public class Controller_DetallesCliente {
    JFInfoCliente infoCliente;
    
    CCompra compra;
    CVenta venta;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
    
    //Constructor for empleado Financiamiento
     public Controller_DetallesCliente(CLogin login,Connection cn,String typeDocto){
        this.infoCliente = new JFInfoCliente();
        this.infoCliente.setTitle("Detalles cliente");
        this.infoCliente.head.setText("Información cliente");
        this.infoCliente.lockFields(false);
        
        this.infoCliente.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(typeDocto){
                    case("Compra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        infoCliente.dispose();
                        break;
                    case("Venta"):
                        Controller_JFAprobarVenta JFAprobarVenta = new Controller_JFAprobarVenta(login,cn);
                        JFAprobarVenta.setData(venta, encargado, auto, cliente);
                        JFAprobarVenta.viewData();
                        infoCliente.dispose();
                        break;
                }
              
            }
        });
        
        this.infoCliente.addWindowListener(new WindowAdapter() {
             @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(infoCliente,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    infoCliente.dispose();
                }
            }
        });
    }
    
    public void setData(CCompra compra,CUsuario encargado, CAuto auto,CCliente cliente) {
            this.compra = compra;
            this.auto = auto;
            this.cliente = cliente;
            this.encargado = encargado;
    }
    public void setData(CVenta venta,CUsuario encargado, CAuto auto,CCliente cliente) {
            this.venta = venta;
            this.auto = auto;
            this.cliente = cliente;
            this.encargado = encargado;
    }
    public void viewData(){
        this.infoCliente.txt_cveElector.setText(cliente.getClaveElector());
        this.infoCliente.txt_nombre.setText(cliente.getNombre());
        this.infoCliente.txt_apellidoPat.setText(cliente.getApellido_pat());
        this.infoCliente.txt_apellidoMat.setText(cliente.getApellido_mat());
        this.infoCliente.txt_RFC.setText(cliente.getRfc());
        this.infoCliente.txt_ingresoMensual.setText(cliente.getIngresoMensual()+ " $");
        this.infoCliente.txt_direccion.setText(cliente.getDireccion());
        this.infoCliente.txt_telefono.setText(cliente.getTelefono());
    }
    
}
