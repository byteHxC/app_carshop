/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CLogin;
import VISTA.JFInfoAuto;
import app_carshop.App_carshop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Byter
 */
public class Controller_JFAgregarAuto {
    JFInfoAuto viewAddAuto;
    Connection cn;
    File fileSelected;
    
    CCliente cliente;
    CAuto auto;
    //Constructor for employe Comercio
    public Controller_JFAgregarAuto(CLogin login,Connection cn){
        this.viewAddAuto = new JFInfoAuto();
        cliente = null;
        auto = null;
        //Action that calculate the price neto the el auto 
        this.viewAddAuto.cbox_Certificado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float precio;
                try{
                    precio = Float.parseFloat(viewAddAuto.txt_Precio.getText());
                    
                }catch(Exception er){
                    precio = 0;
                }
                calcularPrecioNeto(precio);
               
            }
        });
        
        //Action when prees Button addAuto
     this.viewAddAuto.btn_AgregarAuto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                auto = new CAuto();
                //Set data in auto for later validation
                auto.setNumero_serie(viewAddAuto.txt_numeroSerie.getText());
                auto.setMarca(viewAddAuto.txt_marca.getText());
                auto.setTipo(viewAddAuto.txt_Tipo.getText());
                auto.setModelo(viewAddAuto.txt_modelo.getText());
                try{
                auto.setNumero_pasajeros(Integer.parseInt(viewAddAuto.txt_numPasajeros.getText()));
                }catch(NumberFormatException erNumP){
                    auto.setNumero_pasajeros(0);
                }
                try{
                auto.setCilindros(Integer.parseInt(viewAddAuto.txt_numCilindros.getText()));
                }catch(NumberFormatException erNumP){
                    auto.setCilindros(0);
                }
                auto.setColor(viewAddAuto.txt_Color.getText());
                auto.setDetalle(viewAddAuto.txtArea_detalle.getText());
                auto.setCertificado_mecanico(Integer.parseInt(viewAddAuto.cbox_Certificado.getSelectedItem().toString()));
                try{
                viewAddAuto.txt_PrecioNeto.setText(calcularPrecioNeto(Float.parseFloat(viewAddAuto.txt_Precio.getText()+""))+"");
                }catch(NumberFormatException er){
                    
                }
                
                try{
                auto.setPrecio_compra(Float.parseFloat(viewAddAuto.txt_PrecioNeto.getText()));
                }catch(NumberFormatException erPrecio){
                    auto.setPrecio_compra(0);
                }
                if(fileSelected == null){
                    fileSelected = new File("src//ASSETS//CarAny.png");
                }
                auto.setAbsolutePathimagen(fileSelected.getAbsolutePath());
                auto.setNombreImagen(fileSelected.getName());
                auto.setEstado("En proceso");
                
                if(auto.validarAuto(viewAddAuto, cn)){
                    JOptionPane.showMessageDialog(viewAddAuto,"Auto agregado a la compra", "Mensaje de informacion", JOptionPane.INFORMATION_MESSAGE);

                    Controller_JFAgregarCompra JFAddCompra = new Controller_JFAgregarCompra(login, cn);
                    JFAddCompra.segueData(auto, cliente);
                    JFAddCompra.viewAddCompra.btn_AddAuto.setEnabled(false);
                    JFAddCompra.viewAddCompra.btn_searchCliente.setEnabled(false);
                    viewAddAuto.dispose();
                }
            }
     });
     //Action for choose image of this auto
     this.viewAddAuto.label_imgAuto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagenes","jpg","png");
                fileChooser.setFileFilter(filter);
                fileChooser.showOpenDialog(viewAddAuto);
                fileSelected = fileChooser.getSelectedFile();
                ImageIcon image;
                try{
                    image = new ImageIcon(fileSelected.getAbsolutePath());
                }catch(NullPointerException err){
                    image = new ImageIcon(getClass().getResource("/ASSETS/CarAny.png"));
                }
                viewAddAuto.label_imgAuto.setIcon(new ImageIcon (image.getImage().getScaledInstance(viewAddAuto.label_imgAuto.getWidth(), viewAddAuto.label_imgAuto.getHeight(), Image.SCALE_SMOOTH)));
            }
     });
     //Actions for to handle the buttons closing and cancel frame   
     this.viewAddAuto.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAgregarCompra jfAddCompra = new Controller_JFAgregarCompra(login, cn);
                jfAddCompra.segueData(null, cliente);
                jfAddCompra.viewAddCompra.btn_searchCliente.setEnabled(false);
                viewAddAuto.dispose();
               
            }
     });
     this.viewAddAuto.addWindowListener(new WindowAdapter() {
           @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAddAuto,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewAddAuto.dispose();
                }
            }
     });
        
    }
    
    
    
   
   
    public void segueData(CAuto auto,CCliente cliente){
        this.auto = auto;
        this.cliente = cliente;
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
            Logger.getLogger(Controller_JFAgregarAuto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    public float calcularPrecioNeto(float precio){
         if(precio!=0){
                    float valueD = 0;
                    switch(viewAddAuto.cbox_Certificado.getSelectedItem().toString()){
                        case("9"):valueD=2; break;
                        case("8"):valueD=4; break;
                        case("7"):valueD=6; break;
                    }
                    float descuento = precio/100*valueD;   
                    viewAddAuto.txt_PrecioNeto.setText(precio-descuento+"");
                     return precio-descuento;
         }
         return 0;
        
    }
    
}
