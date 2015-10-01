/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCliente;
import MODEL.CLogin;
import MODEL.CUsuario;
import MODEL.DateTime;
import VIEW.JFAddCompra;
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

/**
 *
 * @author Byter
 */
public class Controller_JFAddCompra {
    JFAddCompra viewAddCompra;
    Connection cn;
    CCliente cliente;
    Thread time;
    
    public Controller_JFAddCompra(CLogin login, Connection cn){
        this.viewAddCompra = new JFAddCompra();
        this.cn = cn;
        this.cliente = new CCliente();
         CUsuario usuario = CUsuario.getObject(login.getClave_elector(), cn);
        actualizarTime();
        this.viewAddCompra.label_usuario.setText("USUARIOS: "+login.getUsuario());
        this.viewAddCompra.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewAddCompra.label_ImageEmpleado.getWidth(),viewAddCompra.label_ImageEmpleado.getHeight(), Image.SCALE_SMOOTH)));
        
        if(usuario!=null){
            this.viewAddCompra.txt_encargadoVenta.setText(usuario.getNombre()+" "+usuario.getApellido_pat()+" "+usuario.getApellido_pat());
            
        }
        this.viewAddCompra.btn_AddAuto.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //show add auto
            }
        });
        this.viewAddCompra.btn_searchCliente.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSearchCliente serachCliente = new Controller_JFSearchCliente(login, cn,"Compra");
                viewAddCompra.dispose();
            }
        });
        this.viewAddCompra.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                viewAddCompra.dispose();
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
     public void setCliente(CCliente cliente){
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
            Logger.getLogger(Controller_JFAddCompra.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(Controller_JFAddCompra.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        time.start();
    }
      
}
