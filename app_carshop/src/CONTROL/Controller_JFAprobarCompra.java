/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.AbsJasperReports;
import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CCompra;
import MODELO.CLogin;
import MODELO.CUsuario;
import VISTA.JFAprobarCompra;
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
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Byter
 */
public class Controller_JFAprobarCompra {
    JFAprobarCompra viewAprobarCompra;
    Connection cn;
    float precio_compra = 0;
    
    CCompra compra;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
    
    public Controller_JFAprobarCompra(CLogin login,Connection cn){
        this.viewAprobarCompra = new JFAprobarCompra();
        this.cn = cn;
        compra = new CCompra();
        this.viewAprobarCompra.btn_aceptar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {  
                viewAprobarCompra.dialog_aprobo.dispose();
                if(CCompra.Aprobar(compra.getNumero_factura(),viewAprobarCompra.txt_comentario.getText(), cn) && CAuto.setPrecioVenta(cn,precio_compra+(precio_compra/100*viewAprobarCompra.porcentaje.getValue()) , auto.getNumero_serie())){
                    Object [] options = {"Generar factura","Ir al menu principal"};
                    int optS = JOptionPane.showOptionDialog(viewAprobarCompra, "La compra ha sido aprobada, el auto esta disponible para vender!", "Mensaje",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                    if(optS==0){
                        AbsJasperReports.createReportCompra(cn,"Reports/FacturaCompra/reporteCompra.jasper",compra.getNumero_factura());
                        AbsJasperReports.showViewer();
                    } 
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                    viewAprobarCompra.dispose();
                    
                }else{
                    JOptionPane.showMessageDialog(viewAprobarCompra,"Error en aprobacion de compra,posiblemente ya la aprobaron","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                    viewAprobarCompra.dispose();
                }
            }
        });
        //Action when press button Aprobacion
        this.viewAprobarCompra.btn_aprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                precio_compra = auto.getPrecio_compra();
                viewAprobarCompra.txt_precioCompra.setText(precio_compra+" $");
                viewAprobarCompra.txt_precioVenta.setText(precio_compra+(precio_compra/100*viewAprobarCompra.porcentaje.getValue())+ " $");
        
                viewAprobarCompra.dialog_aprobo.setVisible(true);
            }
        });
        
       this.viewAprobarCompra.porcentaje.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               viewAprobarCompra.txt_precioVenta.setText(precio_compra+(precio_compra/100*viewAprobarCompra.porcentaje.getValue())+ " $");
               viewAprobarCompra.label_porcentaje.setText(viewAprobarCompra.porcentaje.getValue()+" %");
            }
        });
        
        //Action when press button no Aprobacion
        this.viewAprobarCompra.btn_NOAprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAprobarCompra,"¿Esta seguro que NO quiere aprobar esta compra?","Mensaje de advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(resp == JOptionPane.YES_OPTION){
                    JTextArea area = new JTextArea();
                    area.setLineWrap(true);
                    area.setColumns(20);
                    area.setRows(10);
                    //area.setSize(400, 200);
                    String comentario = "";
                    JOptionPane.showMessageDialog(viewAprobarCompra,area,"Comentario porque NO aprobo la compra:",JOptionPane.INFORMATION_MESSAGE);
                    comentario += area.getText();
                    if(!CCompra.noAprobar(compra.getNumero_factura(),comentario, cn)){
                        JOptionPane.showMessageDialog(viewAprobarCompra,"Error en aprobacion de compra,posiblemente ya la aprobaron","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                         Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                         viewAprobarCompra.dispose();
                    }else{
                    CAuto.setEstado(cn,compra.getAuto_numserie(),"No disponible");
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                    viewAprobarCompra.dispose();
                    }
                }
            }
        });
        //See more details for car
        this.viewAprobarCompra.btn_detalles_auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_DetallesAuto detalles_auto = new Controller_DetallesAuto(login, cn, "Compra");
                detalles_auto.setData(compra, encargado, auto, cliente);
                viewAprobarCompra.dispose();
            }
        });
        //See more details for Custom
        this.viewAprobarCompra.btn_detalles_cliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_DetallesCliente detallesCliente = new Controller_DetallesCliente(login, cn,"Compra");
                detallesCliente.setData(compra, encargado, auto, cliente);
                detallesCliente.viewData();
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
        String descripcion = "Marca: "+auto.getMarca()+"\nTipo: "+auto.getTipo()+"\n Modelo: "+auto.getModelo();
        this.viewAprobarCompra.txtArea_descripcion.setText(descripcion);
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
        this.encargado = CUsuario.getObject(compra.getEncargado_cve(), cn);
        this.cliente = CCliente.getObject(cn,compra.getCliente_cve());
        this.auto = CAuto.getObject(cn, compra.getAuto_numserie());
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
