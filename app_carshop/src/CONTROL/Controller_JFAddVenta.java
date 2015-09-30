/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCliente;
import MODEL.CLogin;
import MODEL.CUsuario;
import VIEW.JFAddVenta;
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

/**
 *
 * @author ByteDrive
 */
public class Controller_JFAddVenta {
    JFAddVenta viewAddVenta;
    Connection cn;
    CCliente cliente = null;
    
    

    public Controller_JFAddVenta(CLogin login,Connection cn){
        this.viewAddVenta = new JFAddVenta();
        this.cn = cn;
        CUsuario usuario = CUsuario.getObject(login.getClave_elector(), cn);
        
        this.viewAddVenta.label_usuario.setText("USUARIOS: "+login.getUsuario());
        this.viewAddVenta.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewAddVenta.label_ImageEmpleado.getWidth(),viewAddVenta.label_ImageEmpleado.getHeight(), Image.SCALE_SMOOTH)));
        if(usuario!=null){
            this.viewAddVenta.txt_encargadoVenta.setText(usuario.getNombre()+" "+usuario.getApellido_pat()+" "+usuario.getApellido_pat());
            
        }
        this.viewAddVenta.btn_guardarUsuario.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }
        });
        //Agregar descuento al carro
        this.viewAddVenta.cbox_tipoPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                switch(viewAddVenta.cbox_tipoPago.getSelectedIndex()){
                    case 0:
                        System.out.println("Contado");
                        break;
                    case 1:
                         System.out.println("6 meses");
                        break;
                    case 2: 
                         System.out.println("12 meses");
                        break;
                }
            }
        });
        this.viewAddVenta.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                viewAddVenta.dispose();
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
            Logger.getLogger(Controller_JFAddVenta.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
}
