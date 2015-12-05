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
import VISTA.JFInfoAuto;
import app_carshop.app_carshop;
import java.awt.Image;
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
 * @author Byter
 */
public class Controller_DetallesAuto {
    JFInfoAuto infoAuto;
    
    CCompra compra;
    CVenta venta;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
    
    public Controller_DetallesAuto(CLogin login,Connection cn,CAuto auto){
        infoAuto = new JFInfoAuto();
        infoAuto.lockFields(false);
        infoAuto.setTitle("Detalles auto");
        loadAuto(auto);
        //Al dar en cancelar
        this.infoAuto.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_ListaAutos listaAutos = new Controller_ListaAutos(login, cn);
                infoAuto.dispose();
            }
        });
        //Al cerrar el frame
        this.infoAuto.addWindowListener(new WindowAdapter() {
                @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(infoAuto,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    infoAuto.dispose();
                }
            }
            
        });
        
    }
    
    //Metodo para mostrar detalles al agregar venta
    public void Controller_DetallesAuto(CLogin login,Connection cn,CAuto auto,CCliente cliente) {
        infoAuto = new JFInfoAuto();
        infoAuto.lockFields(false);
        infoAuto.setTitle("Detalles auto");
        loadAuto(auto);
        //Al dar en cancelar
        this.infoAuto.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_SeleccionarAuto seleccionarAutos = new Controller_SeleccionarAuto(login, cn,cliente);
       
                infoAuto.dispose();
            }
        });
        //Al cerrar el frame
        this.infoAuto.addWindowListener(new WindowAdapter() {
                @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(infoAuto,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    infoAuto.dispose();
                }
            }
            
        });
        
    }
    public  Controller_DetallesAuto(){
        
    }
    
    
     //_______________________
    //Constructor for employe Financiamiento Compra/venta, crear nuevo controlador sera pura vista ;)
    public Controller_DetallesAuto(CLogin login,Connection cn,String typeDocto){
        this.infoAuto = new JFInfoAuto();
        this.infoAuto.setTitle("Detalles auto");
         infoAuto.lockFields(false);
     
     //Actions for to handle the buttons closing and cancel frame   
     this.infoAuto.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(typeDocto){
                    case ("Compra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        infoAuto.dispose();
                        break;
                    case ("Venta"):
                        Controller_JFAprobarVenta JFAprobarVenta = new Controller_JFAprobarVenta(login,cn);
                        JFAprobarVenta.setData(venta, encargado, auto, cliente);
                        JFAprobarVenta.viewData();
                        infoAuto.dispose();
                        break;
                }
                
            }
     });
     this.infoAuto.addWindowListener(new WindowAdapter() {
             @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(infoAuto,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    infoAuto.dispose();
                }
            }
     });
        
    }
    public void setData(CCompra compra,CUsuario encargado, CAuto auto,CCliente cliente) {
            this.compra = compra;
            this.auto = auto;
            this.cliente = cliente;
            this.encargado = encargado;
            loadAuto(auto);
    }
     public void setData(CVenta venta,CUsuario encargado, CAuto auto,CCliente cliente) {
            this.venta = venta;
            this.auto = auto;
            this.cliente = cliente;
            this.encargado = encargado;
            loadAuto(auto);
    }
    
    public void loadAuto(CAuto auto){
        infoAuto.txt_numeroSerie.setText(auto.getNumero_serie());
        infoAuto.txt_marca.setText(auto.getMarca());
        infoAuto.txt_Tipo.setText(auto.getTipo());
        infoAuto.txt_modelo.setText(auto.getModelo());
        infoAuto.txt_numPasajeros.setText(auto.getNumero_pasajeros()+ "");
        infoAuto.txt_numCilindros.setText(auto.getCilindros()+"");
        infoAuto.txt_Color.setText(auto.getColor());
        
        infoAuto.cbox_Certificado.removeAllItems();
        infoAuto.cbox_Certificado.addItem(auto.getCertificado_mecanico());
        infoAuto.txtArea_detalle.setText(auto.getDetalle());
        infoAuto.label_preciocompra.setText("Precio compra: ");
        infoAuto.txt_Precio.setText(auto.getPrecio_compra()+" $");
        infoAuto.label_precioopt.setText("Precio venta: ");
        infoAuto.txt_PrecioNeto.setText(auto.getPrecio_venta()+" $");
        infoAuto.label_imgAuto.setIcon(new ImageIcon(getImageWithBlob(auto.getImageBlob(), auto.getNombreImagen()).getImage().getScaledInstance(infoAuto.label_imgAuto.getWidth(), infoAuto.label_imgAuto.getHeight(),Image.SCALE_SMOOTH)));

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
                Logger.getLogger(Controller_DetallesAuto.class.getName()).log(Level.SEVERE, null, ex);
            } 
            return image;
         }
        
       
}
