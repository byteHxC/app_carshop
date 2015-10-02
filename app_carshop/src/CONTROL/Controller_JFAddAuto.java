/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CAuto;
import MODEL.CCompra;
import MODEL.CLogin;
import VIEW.JFAddAuto;
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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Byter
 */
public class Controller_JFAddAuto {
    JFAddAuto viewAddAuto;
    Connection cn;
    CCompra compra;
    File fileSelected;
    
    public Controller_JFAddAuto(CLogin login,Connection cn){
        this.viewAddAuto = new JFAddAuto();
        this.viewAddAuto.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewAddAuto.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewAddAuto.label_ImageEmpleado.getWidth(),viewAddAuto.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        this.compra = new CCompra();
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
                if(precio!=0){
                    float valueD = 0;
                    switch(viewAddAuto.cbox_Certificado.getSelectedItem().toString()){
                        case("9"):valueD=2; break;
                        case("8"):valueD=4; break;
                        case("7"):valueD=6; break;
                    }
                    float descuento = precio/100*valueD;
                    viewAddAuto.txt_PrecioNeto.setText(precio-descuento+"");
                }
            }
        });
        
        //Action when prees Button addAuto
     this.viewAddAuto.btn_AgregarAuto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CAuto auto = new CAuto();
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
                auto.setCertificado_mecanico(viewAddAuto.cbox_Certificado.getSelectedIndex()+1);
                if(viewAddAuto.cbox_Certificado.getSelectedIndex()==1){
                    viewAddAuto.txt_PrecioNeto.setText(viewAddAuto.txt_Precio+"");
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
                
                //Validar datos auto
                auto.saveObject(cn);
                JOptionPane.showMessageDialog(viewAddAuto,"Auto agregado en estado de [PROCESO]...", "Mensaje de informacion", JOptionPane.INFORMATION_MESSAGE);
                Controller_JFAddCompra JFAddCompra = new Controller_JFAddCompra(login, cn);
                compra.setAuto_numserie(auto.getNumero_serie());
                compra.setPrecio(auto.getPrecio_compra());
                JFAddCompra.setCompra(compra);
                JFAddCompra.setData();
                viewAddAuto.dispose();
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
                Controller_JFAddCompra jfAddCompra = new Controller_JFAddCompra(login, cn);
                jfAddCompra.setCompra(compra);
                jfAddCompra.setData();
                viewAddAuto.dispose();
            }
     });
     this.viewAddAuto.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFAddCompra jfAddCompra = new Controller_JFAddCompra(login, cn);
                jfAddCompra.setCompra(compra);
                jfAddCompra.setData();
                viewAddAuto.dispose();
            }
     });
        
    }
    public void setCompra(CCompra compra){
         this.compra = compra;
     }
     public CCompra getCompra(){
         return this.compra;
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
            Logger.getLogger(Controller_JFAddAuto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
}
