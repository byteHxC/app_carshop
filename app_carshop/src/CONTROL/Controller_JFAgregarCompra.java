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
import MODELO.DateTime;
import VISTA.JFAgregarCompra;
import app_carshop.App_carshop;
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
public class Controller_JFAgregarCompra {
    JFAgregarCompra viewAddCompra;
    Thread time;
    
    CAuto auto;
    CCliente cliente;
    
    public Controller_JFAgregarCompra(CLogin login, Connection cn){
        this.viewAddCompra = new JFAgregarCompra();
        auto = new CAuto();
        cliente = new CCliente();
        CUsuario usuario = CUsuario.getObject(login.getClave_elector(), cn);
        actualizarTime();
        this.viewAddCompra.label_usuario.setText("USUARIOS: "+login.getUsuario());
        this.viewAddCompra.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewAddCompra.label_ImageEmpleado.getWidth(),viewAddCompra.label_ImageEmpleado.getHeight(), Image.SCALE_SMOOTH)));
        
        if(usuario!=null){
            this.viewAddCompra.txt_encargadoVenta.setText(usuario.getNombre()+" "+usuario.getApellido_pat()+" "+usuario.getApellido_pat());
            
        }
        this.viewAddCompra.btn_enviarSolicitud.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //Validar compra y guardar compra en estado de proceso
                CCompra compra = new CCompra();
                //Agregar datos del encargado a la compra
                compra.setEncargado_cve(login.getClave_elector());
                //Agregar datos del auto a la compra
                compra.setAuto_numserie(auto.getNumero_serie());
                compra.setPrecio(auto.getPrecio_compra());
                //Agregar datos del cliente a la compra
                compra.setCliente_cve(cliente.getClaveElector());
              //Validar compra
                if(compra.validarCompra(viewAddCompra, cn)){
                  auto.saveObject(cn);
                  compra.saveObject(cn);
                  JOptionPane.showMessageDialog(viewAddCompra,"Solicitud enviada al departamento de financiamiento","Informacion",JOptionPane.INFORMATION_MESSAGE);
                  Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                  viewAddCompra.dispose();
              }
            }
        });
        
        //Agregar auto
        this.viewAddCompra.btn_AddAuto.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
              if(viewAddCompra.btn_AddAuto.isEnabled()){ 
                if(cliente.getClaveElector().equals("")){
                    JOptionPane.showMessageDialog(viewAddCompra,"Primero agregue a el cliente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    Controller_JFAgregarAuto JFAddAuto = new Controller_JFAgregarAuto(login, cn);
                    JFAddAuto.segueData(null, cliente);
                    viewAddCompra.dispose();
                }
              }
            }
        });
        //Buscar y seleccionar cliente
        this.viewAddCompra.btn_searchCliente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(viewAddCompra.btn_searchCliente.isEnabled()){
                   Controller_SeleccionarClienteCompra addClienteCompra = new Controller_SeleccionarClienteCompra(login, cn);
                    viewAddCompra.dispose();
                }
            }
        });
        this.viewAddCompra.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAddCompra,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewAddCompra.dispose();
                }
            }
        });
        this.viewAddCompra.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                 Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                viewAddCompra.dispose();
            }
        });
    }
    //Metodos importantes pase de datos y carga de datos
    public void segueData(CAuto auto,CCliente cliente){
        this.auto = auto;
        this.cliente = cliente;
        reloadData();
    }
    public void reloadData(){
         //update data cliente
          if(cliente!=null){
              this.viewAddCompra.txt_clienteCveElector.setText(cliente.getClaveElector());
              this.viewAddCompra.txt_ClienteNombre.setText(cliente.getNombre()+" "+cliente.getApellido_pat()+" "+cliente.getApellido_mat());
          }
        //update date auto
          if(auto!=null){
              this.viewAddCompra.txt_numSerie.setText(auto.getNumero_serie());
              String descripcion = "Marca: "+auto.getMarca()+"\nTipo: "+auto.getTipo()+"\n Modelo: "+auto.getModelo();
              this.viewAddCompra.txtArea_descripcion.setText(descripcion);
              this.viewAddCompra.txt_Precio.setText(auto.getPrecio_compra()+"");
            
          }
     }
    
    //Metodos deslindados
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
            Logger.getLogger(Controller_JFAgregarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    public void actualizarTime(){
        time = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        viewAddCompra.txt_fecha.setText(DateTime.getFechaNow());
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller_JFAgregarCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        time.start();
    }
      
}
