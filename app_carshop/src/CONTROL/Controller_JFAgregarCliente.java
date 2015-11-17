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
import VISTA.JFInfoCliente;
import app_carshop.App_carshop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFAgregarCliente {
    JFInfoCliente viewAddCliente;
    CCompra compra;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
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
                    ing = 0;
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
                    App_carshop.init();
                    viewAddCliente.dispose();
                }
            }
        });
    }
    
    
    //--------------------------------------------
    //Constructor for empleado Financiamiento
     public Controller_JFAgregarCliente(String JFController,CLogin login,Connection cn){
        this.viewAddCliente = new JFInfoCliente();
        this.viewAddCliente.setTitle("Detalle cliente");
        this.viewAddCliente.head.setText("Vista cliente");
        lockFields();
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
                    ing = 0;
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
        this.viewAddCliente.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(JFController){
                    case("FinanciamientoCompra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        viewAddCliente.dispose();
                        break;
                    case("FinanciamientoVenta"):
                            //JFrame aprobarCompra
                        break;
                }
              
            }
        });
        
        this.viewAddCliente.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                  switch(JFController){
                    case("FinanciamientoCompra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        viewAddCliente.dispose();
                        break;
                    case("FinanciamientoVenta"):
                            //JFrame aprobarCompra
                        break;
                }
            }
        });
    }
    
    private ImageIcon getImageWithBlob(Blob blob,String nombre){
             ImageIcon image = null;
             BufferedImage img = null;
            try {
                byte[] data = blob.getBytes(1,(int)blob.length());

                img = ImageIO.read(new ByteArrayInputStream(data));
                //Crear la imagen
                 image = new ImageIcon(img);
                return image;
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Controller_JFAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return image;
         }
    public void setData(CCompra compra,CUsuario encargado, CAuto auto,CCliente cliente) {
            this.compra = compra;
            this.auto = auto;
            this.cliente = cliente;
            this.encargado = encargado;
    }
    public void viewData(){
        this.viewAddCliente.txt_cveElector.setText(cliente.getClaveElector());
        this.viewAddCliente.txt_nombre.setText(cliente.getNombre());
        this.viewAddCliente.txt_apellidoPat.setText(cliente.getApellido_pat());
        this.viewAddCliente.txt_apellidoMat.setText(cliente.getApellido_mat());
        this.viewAddCliente.txt_RFC.setText(cliente.getRfc());
        this.viewAddCliente.txt_ingresoMensual.setText(cliente.getIngresoMensual()+ " $");
        this.viewAddCliente.txt_direccion.setText(cliente.getDireccion());
        this.viewAddCliente.txt_telefono.setText(cliente.getTelefono());
    }
    public void lockFields(){
        this.viewAddCliente.txt_cveElector.setEditable(false);
        this.viewAddCliente.txt_nombre.setEditable(false);
        this.viewAddCliente.txt_apellidoPat.setEditable(false);
        this.viewAddCliente.txt_apellidoMat.setEditable(false);
        this.viewAddCliente.txt_RFC.setEditable(false);
        this.viewAddCliente.txt_ingresoMensual.setEditable(false);
        this.viewAddCliente.txt_direccion.setEditable(false);
        this.viewAddCliente.txt_telefono.setEditable(false);
        
        this.viewAddCliente.btn_guardarCliente.setVisible(false);
        this.viewAddCliente.btn_cancelar.setText("Regresar");
    }
    
}
