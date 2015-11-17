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
import VISTA.JFAgregarAuto;
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
    JFAgregarAuto viewAddAuto;
    Connection cn;
    File fileSelected;
    
    CCliente cliente;
    CAuto auto;
    //Constructor for employe Comercio
    public Controller_JFAgregarAuto(CLogin login,Connection cn){
        this.viewAddAuto = new JFAgregarAuto();
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
          
                viewAddAuto.txt_PrecioNeto.setText(calcularPrecioNeto(Float.parseFloat(viewAddAuto.txt_Precio.getText()+""))+"");
                
                
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
    
    
    
    //_______________________
    //Constructor for employe Financiamiento Compra/venta, crear nuevo controlador sera pura vista ;)
    public Controller_JFAgregarAuto(String JFController,CLogin login,Connection cn){
      
        this.viewAddAuto = new JFAgregarAuto();
        this.viewAddAuto.setTitle("Detalle auto");
        this.viewAddAuto.jLabel15.setText("Vista auto");
        //Enable false for components that not use
        this.viewAddAuto.cbox_Certificado.setEnabled(false);
        this.viewAddAuto.btn_AgregarAuto.setVisible(false);
        this.viewAddAuto.btn_cancelar.setText("Regresar");
        lockFields();
     
     //Actions for to handle the buttons closing and cancel frame   
     this.viewAddAuto.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch(JFController){
                    case ("FinanciamientoCompra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        //JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        viewAddAuto.dispose();
                        break;
                    case ("FinanciamientoVenta"):
                        break;
                }
                
            }
     });
     this.viewAddAuto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                 switch(JFController){
                    case ("FinanciamientoCompra"):
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login,cn);
                        //JFAprobarCompra.setData(compra, encargado, auto, cliente);
                        JFAprobarCompra.viewData();
                        viewAddAuto.dispose();
                        break;
                    case ("FinanciamientoVenta"):
                        break;
                }
            }
     });
        
    }
   
    public void segueData(CAuto auto,CCliente cliente){
        this.auto = auto;
        this.cliente = cliente;
    }
      
      public void viewData(){
          this.viewAddAuto.label_imgAuto.setIcon(new ImageIcon(getImageWithBlob(this.auto.getImageBlob(),this.auto.getNombreImagen()).getImage().getScaledInstance(viewAddAuto.label_imgAuto.getWidth(),viewAddAuto.label_imgAuto.getHeight(),Image.SCALE_SMOOTH)));
          this.viewAddAuto.txt_numeroSerie.setText(auto.getNumero_serie());
          this.viewAddAuto.txt_marca.setText(auto.getMarca());
          this.viewAddAuto.txt_Tipo.setText(auto.getTipo());
          this.viewAddAuto.txt_modelo.setText(auto.getModelo());
          this.viewAddAuto.txt_numPasajeros.setText(auto.getNumero_pasajeros()+"");
          this.viewAddAuto.txt_numCilindros.setText(auto.getCilindros()+"");
          this.viewAddAuto.txt_Color.setText(auto.getColor());
          this.viewAddAuto.cbox_Certificado.setSelectedItem(auto.getCilindros());
          this.viewAddAuto.txtArea_detalle.setText(auto.getDetalle());
          this.viewAddAuto.txt_Precio.setVisible(false);
          this.viewAddAuto.jLabel10.setVisible(false);
          this.viewAddAuto.txt_PrecioNeto.setText(auto.getPrecio_compra()+"  $");
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

    private void lockFields() {
          this.viewAddAuto.txt_numeroSerie.setEditable(false);
          this.viewAddAuto.txt_marca.setEditable(false);
          this.viewAddAuto.txt_Tipo.setEditable(false);
          this.viewAddAuto.txt_modelo.setEditable(false);
          this.viewAddAuto.txt_numPasajeros.setEditable(false);
          this.viewAddAuto.txt_numCilindros.setEditable(false);
          this.viewAddAuto.txt_Color.setEditable(false);
          this.viewAddAuto.cbox_Certificado.setEditable(false);
          this.viewAddAuto.txtArea_detalle.setEditable(false);
          this.viewAddAuto.txt_Precio.setVisible(false);
          this.viewAddAuto.jLabel10.setVisible(false);
          this.viewAddAuto.txt_PrecioNeto.setEditable(false);
    }
    
}
