/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CLogin;
import MODELO.CUsuario;
import MODELO.CVenta;
import MODELO.DateTime;
import VISTA.JFAgregarVenta;
import app_carshop.app_carshop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Controller_JFAgregarVenta {
    JFAgregarVenta viewAddVenta;
    Thread time;
    
    CCliente cliente;
    CAuto auto;
    
    public Controller_JFAgregarVenta(CLogin login,Connection cn){
        this.viewAddVenta = new JFAgregarVenta();
        this.auto = null;
        this.cliente = null;
        CUsuario usuario = CUsuario.getObject(login.getClave_elector(), cn);
        actualizarTime();
        this.viewAddVenta.label_usuario.setText("USUARIOS: "+login.getUsuario());
        this.viewAddVenta.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewAddVenta.label_ImageEmpleado.getWidth(),viewAddVenta.label_ImageEmpleado.getHeight(), Image.SCALE_SMOOTH)));
        if(usuario!=null){
        this.viewAddVenta.txt_encargadoVenta.setText(usuario.getNombre()+" "+usuario.getApellido_pat()+" "+usuario.getApellido_pat());        
        }
        //Enviar solicitud de venta 
        this.viewAddVenta.btn_enviarSolicitud.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(auto!=null && cliente!=null){
                    CVenta venta = new CVenta();
                    venta.setTotal(auto.getPrecio_venta()-calcularDescuento(viewAddVenta.cbox_tipoPago.getSelectedIndex(), auto.getPrecio_venta()));
                    venta.setTipo_pago(viewAddVenta.cbox_tipoPago.getSelectedItem().toString());
                    venta.setAuto_numserie(auto.getNumero_serie());
                    venta.setEncargado_cve(login.getClave_elector());
                    venta.setCliente_cve(cliente.getClaveElector());
                    if(venta.validarVenta(viewAddVenta, cn)){
                        venta.saveObject(cn);
                        JOptionPane.showMessageDialog(viewAddVenta,"Solicitud de venta enviada");
                         Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                         viewAddVenta.dispose();

                    }else{
                        JOptionPane.showMessageDialog(viewAddVenta,"Favor de verificar los datos");
                    }
                }
            }
        });
        //Seleccionar al cliente
        this.viewAddVenta.btn_searchCliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(viewAddVenta.btn_searchCliente.isEnabled()){
                   Controller_SeleccionarClienteVenta addClienteVenta = new Controller_SeleccionarClienteVenta(login, cn);
                    viewAddVenta.dispose();
                }
            }
        });
        //Seleccionar auto
        this.viewAddVenta.btn_AddAuto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(cliente!=null){
                Controller_SeleccionarAuto seleccionarAuto = new Controller_SeleccionarAuto(login, cn,cliente);
                viewAddVenta.dispose();
                }else{
                    JOptionPane.showMessageDialog(viewAddVenta,"Primero agregue a el cliente","Mensaje de informacion",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        //Agregar descuento al carro
        this.viewAddVenta.cbox_tipoPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auto!=null){
                    viewAddVenta.txt_Descuento.setText(calcularDescuento(viewAddVenta.cbox_tipoPago.getSelectedIndex(), auto.getPrecio_venta())+ "$");
                    viewAddVenta.txt_total.setText(auto.getPrecio_venta()-calcularDescuento(viewAddVenta.cbox_tipoPago.getSelectedIndex(), auto.getPrecio_venta())+ "$");
                }
            }
        });
        this.viewAddVenta.addWindowListener(new WindowAdapter() {
           @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAddVenta,"¿Desea salir de la app?","Warning",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    viewAddVenta.dispose();
                }
            }
        });
        this.viewAddVenta.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                 Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                viewAddVenta.dispose();
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
              this.viewAddVenta.txt_clienteCveElector.setText(cliente.getClaveElector());
              this.viewAddVenta.txt_ClienteNombre.setText(cliente.getNombre()+" "+cliente.getApellido_pat()+" "+cliente.getApellido_mat());
          }
        //update date auto
          if(auto!=null){
              this.viewAddVenta.txt_numeroSerie.setText(auto.getNumero_serie());
              String descripcion = "Marca: "+auto.getMarca()+"\nTipo: "+auto.getTipo()+"\n Modelo: "+auto.getModelo();
              this.viewAddVenta.txt_descripcion.setText(descripcion);
              this.viewAddVenta.txt_precio.setText(auto.getPrecio_venta()+"");
                    viewAddVenta.txt_Descuento.setText(calcularDescuento(viewAddVenta.cbox_tipoPago.getSelectedIndex(), auto.getPrecio_venta())+ "$");
                    viewAddVenta.txt_total.setText(auto.getPrecio_venta()-calcularDescuento(viewAddVenta.cbox_tipoPago.getSelectedIndex(), auto.getPrecio_venta())+ "$");
                
            
          }
     }
    
      public void actualizarTime(){
        time = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    try {
                        viewAddVenta.txt_fecha.setText(DateTime.getFechaNow());
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Controller_JFAgregarCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        time.start();
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
            Logger.getLogger(Controller_JFAgregarVenta.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
    public float calcularDescuento(int tipo,float precio){
        float descuento = 0;
         switch(tipo){
                case 0:
                    float descuento03 = (float) (auto.getPrecio_venta()*0.03);
                    descuento =descuento03;
                    break;
                case 1:
                    float descuento02 = (float) (auto.getPrecio_venta()*0.02);
                    descuento = descuento02;
                    break;
                case 2: 
                    descuento = 0;
                    break;
            }
         return descuento;
    }
}
