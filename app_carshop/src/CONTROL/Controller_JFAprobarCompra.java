/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CAuto;
import MODEL.CCliente;
import MODEL.CCompra;
import MODEL.CLogin;
import MODEL.CUsuario;
import VIEW.JFAprobarCompra;
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
public class Controller_JFAprobarCompra {
    JFAprobarCompra viewAprobarCompra;
    Connection cn;
    CCompra compra;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
    
    public Controller_JFAprobarCompra(CLogin login,Connection cn){
        this.viewAprobarCompra = new JFAprobarCompra();
        this.cn = cn;
        compra = new CCompra();
        this.viewAprobarCompra.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewAprobarCompra.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewAprobarCompra.label_ImageEmpleado.getWidth(),viewAprobarCompra.label_ImageEmpleado.getHeight() ,Image.SCALE_SMOOTH )));
        this.viewAprobarCompra.btn_moreDetailsCustom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAddCliente JFviewCliente = new Controller_JFAddCliente("FinanciamientoCompra", login, cn);
                JFviewCliente.setData(compra, encargado, auto, cliente);
                JFviewCliente.viewData();
                viewAprobarCompra.dispose();
            }
        });
        this.viewAprobarCompra.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                viewAprobarCompra.dispose();
            }
        });
    }
    public void viewData(){
       //set fecha
        this.viewAprobarCompra.txt_fecha.setText(compra.getFecha());
      //Set data cliente
        this.viewAprobarCompra.txt_ClienteNombre.setText(cliente.getNombre());
        this.viewAprobarCompra.txt_clienteCveElector.setText(cliente.getClaveElector());
      //Set data auto
        this.viewAprobarCompra.txt_numSerie.setText(auto.getNumero_serie());
        this.viewAprobarCompra.txtArea_descripcion.setText(auto.getDetalle());
        this.viewAprobarCompra.txt_Precio.setText(auto.getPrecio_compra()+"  $");
      //Set data encargado
        this.viewAprobarCompra.txt_encargadoVenta.setText(encargado.getNombre()+" "+encargado.getApellido_pat()+" "+encargado.getApellido_mat());
        
    }
    public CCompra getCompra() {
        return compra;
    }

    public void setData(CCompra compra,CUsuario encargado, CAuto auto,CCliente cliente) {
        this.compra = compra;
        this.auto = auto;
        this.cliente = cliente;
        this.encargado = encargado;
    }
    public void loadData(CCompra compra){
        this.compra = compra;
        auto = CAuto.getObject(cn, compra.getAuto_numserie());
        encargado = CUsuario.getObject(compra.getEncargado_cve(), cn);
        cliente = CCliente.getObject(cn,compra.getCliente_cve());
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
            Logger.getLogger(Controller_JFAprobarCompra.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
}
