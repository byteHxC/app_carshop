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
import javax.swing.JOptionPane;

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
        //Action when press button Aprobacion
        this.viewAprobarCompra.btn_aprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(CCompra.Aprobar(compra.getNumero_factura(), cn)){
                    JOptionPane.showMessageDialog(viewAprobarCompra,"La compra ha sido aprobada, el auto esta disponible para vender!","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                   Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                     viewAprobarCompra.dispose();
                }else{
                    JOptionPane.showMessageDialog(viewAprobarCompra,"Error en aprobacion de compra,posiblemente ya la aprobaron","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                    viewAprobarCompra.dispose();
                }
            }
        });
        
        //Action when press button no Aprobacion
        this.viewAprobarCompra.btn_NOAprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAprobarCompra,"¿Esta seguro que NO quiere aprobar esta compra?\n*Al hacer esto se borrara el registro del auto y compra.","Mensaje de advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(resp == JOptionPane.YES_OPTION){
                    if(!CCompra.noAprobar(compra.getAuto_numserie(), cn)){
                        JOptionPane.showMessageDialog(viewAprobarCompra,"Error en aprobacion de compra,posiblemente ya la aprobaron","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                         Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                         viewAprobarCompra.dispose();
                    }else{
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                    viewAprobarCompra.dispose();
                    }
                }
            }
        });
        //See more details for car
        this.viewAprobarCompra.btn_moreDetailsCar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAddAuto JFViewAuto = new Controller_JFAddAuto("FinanciamientoCompra", login, cn);
                JFViewAuto.setData(compra, encargado, auto, cliente);
                JFViewAuto.viewData();
                viewAprobarCompra.dispose();
            }
        });
        //See more details for Custom
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
        this.auto = CAuto.getObject(cn, compra.getAuto_numserie());
        this.encargado = CUsuario.getObject(compra.getEncargado_cve(), cn);
        this.cliente = CCliente.getObject(cn,compra.getCliente_cve());
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
