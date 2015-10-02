/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCompra;
import MODEL.CLogin;
import VIEW.JFFinanciamientoHome;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Byter
 */
public class Controller_JFFinanciamientoHome {
    JFFinanciamientoHome viewFinanHome;
    Connection cn;
    
    public Controller_JFFinanciamientoHome(CLogin login, Connection cn){
        this.viewFinanHome = new JFFinanciamientoHome();
        this.cn = cn;
          
        //Settings view labels identifications
        this.viewFinanHome.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewFinanHome.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewFinanHome.label_ImageEmpleado.getWidth(),viewFinanHome.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        loadTable(viewFinanHome.table_doctos, cn);
       
        
    }
    
    private void loadTable(JTable tabla,Connection cn){
        ArrayList<CCompra> compras = CCompra.getCompras(cn);
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
            return false;
            }
        };
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.removeAll();
        model.addColumn("Nº factura");
        model.addColumn("Compra/Venta");
        model.addColumn("Total");
        model.addColumn("Fecha");
        for(CCompra compra: compras){
            Object[] oCompra = new Object[4];
            oCompra[0] = compra.getNumero_factura();
            oCompra[1] = "Compra";
            oCompra[2] = compra.getPrecio()+"$";
            oCompra[3] = compra.getFecha();
            model.addRow(oCompra);
        }
        tabla.setModel(model);
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
            Logger.getLogger(Controller_JFFinanciamientoHome.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
}
