/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CLogin;
import VISTA.JFListaAutos;
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
public class Controller_SeleccionarAuto {
    JFListaAutos listaAutos;
    ArrayList<CAuto> autos = new ArrayList<>();
    
    CCliente cliente;
    CAuto auto;
    //Constructor para mostrar el catalogo de autos
    public Controller_SeleccionarAuto(CLogin login,Connection cn,CCliente cliente){
        this.cliente = cliente;
        listaAutos = new JFListaAutos();
        //Seleccionar auto
        listaAutos.btn_seleccionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected =listaAutos.tabla_vAutos.getSelectedRow();
                if(rowSelected == -1){
                   JOptionPane.showMessageDialog(listaAutos, "Seleccione un registro","Mensaje",JOptionPane.WARNING_MESSAGE);
                }else{
                    Controller_JFAgregarVenta JFAddVenta = new Controller_JFAgregarVenta(login, cn);
                    auto = CAuto.getObject(cn,listaAutos.tabla_vAutos.getValueAt(rowSelected, 0).toString());
                    JFAddVenta.segueData(auto, cliente);
     
                    JFAddVenta.viewAddVenta.btn_searchCliente.setEnabled(false);
                    listaAutos.dispose();
                }
            }
        });
        //Ver mas detalles del auto
        listaAutos.btn_verDetalles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = listaAutos.tabla_vAutos.getSelectedRow();
                if(rowSelected == -1){
                    JOptionPane.showMessageDialog(listaAutos, "Seleccione un registro","Mensaje",JOptionPane.WARNING_MESSAGE);
                }else{
                    String valueSelected = listaAutos.tabla_vAutos.getValueAt(rowSelected, 0).toString();
                    for(CAuto auto : autos){
                        if(auto.getNumero_serie().equals(valueSelected)){
                            Controller_DetallesAuto detalleAuto = new Controller_DetallesAuto();
                            detalleAuto.Controller_DetallesAuto(login, cn, auto,cliente);
                            listaAutos.dispose();
                        }
                    }
                }
            }
        });
        //Evento cuando preciona en buscar
        listaAutos.btn_buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String value = listaAutos.txt_valueSearch.getText();
                String buscarPor = listaAutos.cbox_searchFor.getSelectedItem().toString();
                if(value.isEmpty()){
                   JOptionPane.showMessageDialog(listaAutos, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
                }else{
                    switch (buscarPor){
                        case ("Marca"):
                            autos = new ArrayList<>();
                            autos = CAuto.queryForMarca(cn, value);
                                loadTable(listaAutos.tabla_vAutos,autos);
                            break;
                        case ("Modelo"):
                            autos = new ArrayList<>();
                            autos = CAuto.queryForModelo(cn, value);
                                loadTable(listaAutos.tabla_vAutos,autos);
                            break;
                        case ("Numero pasajeros"):
                            autos = new ArrayList<>();
                            autos = CAuto.queryForNumPasajeros(cn,value);
                                loadTable(listaAutos.tabla_vAutos,autos);
                            break;    
                        case ("Cilindros"):
                            autos = new ArrayList<>();
                            autos = CAuto.queryForCilindros(cn,value);
                                loadTable(listaAutos.tabla_vAutos,autos);
                            break; 
                        case ("Precio menor a"):
                            autos = new ArrayList<>();
                            autos = CAuto.queryForPrecio(cn, value);
                                loadTable(listaAutos.tabla_vAutos,autos);
                            break;    
                    } 
                }
            }
        });
        //Evento al cambiar valor del combox
        listaAutos.cbox_searchFor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listaAutos.txt_valueSearch.setText(null);
                }
            });
        //Al dar en cancelar
        this.listaAutos.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAgregarVenta agregarVenta= new Controller_JFAgregarVenta(login, cn);
                agregarVenta.segueData(null, cliente);

                listaAutos.dispose();
            }
        });
         //Al cerrar el frame
        this.listaAutos.addWindowListener(new WindowAdapter() {
                @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(listaAutos,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    listaAutos.dispose();
                }
            }
            
        });

    }
    public CCliente getCliente(){
        return this.cliente;
    }
    
public void loadTable(JTable tabla,ArrayList<CAuto> autos){
        
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
	};
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.removeAll();
        
        model.addColumn("Numero serie");
        model.addColumn("Marca");
        model.addColumn("Tipo");
        model.addColumn("Modelo");
        model.addColumn("# Pasajeros");
        model.addColumn("Cilindros");
        model.addColumn("Color");
        model.addColumn("Precio");

        
        for (CAuto auto : autos){
            Object[] objA = new Object[8];
                objA[0] = auto.getNumero_serie();
                objA[1] = auto.getMarca();
                objA[2] = auto.getTipo();
                objA[3] = auto.getModelo();
                objA[4] = auto.getNumero_pasajeros();
                objA[5] = auto.getCilindros();
                objA[6] = auto.getColor();
                objA[7] = auto.getPrecio_venta();
            model.addRow(objA);
        }
        tabla.setModel(model);
    }    
}
