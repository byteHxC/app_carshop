/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CCliente;
import MODELO.CLogin;
import VISTA.JFListaClientes;
import app_carshop.app_carshop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Byter
 */
public class Controller_SeleccionarClienteCompra {
    JFListaClientes clienteCompra;
    CCliente cliente;
  
    
    public Controller_SeleccionarClienteCompra(CLogin login,Connection cn){
        cliente = null;
        clienteCompra = new JFListaClientes();
        loadTable(clienteCompra.table_showClientes,CCliente.queryAll(cn));
         //Action when press button seleccionar
        this.clienteCompra.btn_seleccionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = clienteCompra.table_showClientes.getSelectedRow();
                if(rowSelected == -1){
                   JOptionPane.showMessageDialog(clienteCompra, "Seleccione un registro","Mensaje",JOptionPane.WARNING_MESSAGE);
                }else{
                    Controller_JFAgregarCompra JFAddCompra = new Controller_JFAgregarCompra(login, cn);
                    cliente = CCliente.getObject(cn,clienteCompra.table_showClientes.getValueAt(rowSelected, 0).toString());
                    JFAddCompra.segueData(null,cliente);
                    JFAddCompra.viewAddCompra.btn_searchCliente.setEnabled(false);
                    clienteCompra.dispose();
                }
            }
        });
        this.clienteCompra.btn_addNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAgregarCliente agregarCliente = new Controller_JFAgregarCliente(login, cn);
                clienteCompra.dispose();
            }
        });
        //Al dar en el boton cancelar
        this.clienteCompra.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAgregarCompra agregarCompra = new Controller_JFAgregarCompra(login, cn);
                clienteCompra.dispose();
            }
        });
        //Al cerrar el frame 
        this.clienteCompra.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(clienteCompra,"¿Si sale de aqui, se cerrara su sesion?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                   clienteCompra.dispose();
                }
                
            }
        });
        
         //All dar clic en buscar
        this.clienteCompra.btn_buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               String value = clienteCompra.txt_valueSearch.getText();
               if(value.isEmpty()){
                    JOptionPane.showMessageDialog(clienteCompra, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
               }else{
                   switch(clienteCompra.cbox_searchFor.getSelectedItem().toString()){
                       case ("Clave elector"):
                           loadTable(clienteCompra.table_showClientes, CCliente.queryForClaveElector(cn, value));
                           break;
                       case ("Nombre"):
                           loadTable(clienteCompra.table_showClientes, CCliente.queryForName(cn, value));
   
                           break;
                       case ("Apellido paterno"):
                           loadTable(clienteCompra.table_showClientes, CCliente.queryForApellido_Pat(cn, value));
                           break;
                       case ("RFC"):
                           loadTable(clienteCompra.table_showClientes, CCliente.queryForRFC(cn, value));
                           break;
                   }
               }
            }
        });
        //Al cambiar valor de combo box filtro
        this.clienteCompra.cbox_searchFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clienteCompra.txt_valueSearch.setText(null);
                if(clienteCompra.cbox_searchFor.getSelectedItem().toString().equals("Todos")){
                    loadTable(clienteCompra.table_showClientes,CCliente.queryAll(cn));
                }
            }
        });
    }
    
     public void segueData(CCliente cliente){
        this.cliente = cliente;
    }
    
      public void loadTable(JTable tabla,ArrayList<CCliente> clientes){
        
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
	};
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.removeAll();
        model.addColumn("Clave elector");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        model.addColumn("RFC");
        model.addColumn("Ingreso mensual");
        
        for (CCliente cliente : clientes){
            Object[] user = new Object[8];
                user[0] = cliente.getClaveElector();
                user[1] = cliente.getNombre();
                user[2] = cliente.getApellido_pat()+" "+cliente.getApellido_mat();
                user[3] = cliente.getDireccion();
                user[4] = cliente.getTelefono();
                user[5] = cliente.getRfc();
                user[6] = cliente.getIngresoMensual();
            model.addRow(user);
        }
        tabla.setModel(model);
    }
}
