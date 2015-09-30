/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCliente;
import MODEL.CLogin;
import VIEW.JFAddCliente;
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
 * @author ByteDrive
 */
public class Controller_JFAddCliente {
    JFAddCliente viewAddCliente;
    Connection cn;
    
    public Controller_JFAddCliente(CLogin login,Connection cn){
        this.viewAddCliente = new JFAddCliente();
        this.cn = cn;
        //Settings view labels identifications
        this.viewAddCliente.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewAddCliente.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewAddCliente.label_ImageEmpleado.getWidth(),viewAddCliente.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        
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
                Controller_JFComercioHome JFComercioHome = new Controller_JFComercioHome(login, cn);
                viewAddCliente.dispose();
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
            Logger.getLogger(Controller_JFAddCliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
}
