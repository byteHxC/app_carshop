/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCompra;
import MODEL.CLogin;
import VIEW.JFFinanciamientoHome;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
        //Button ir a docto
        this.viewFinanHome.btn_irDocto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = viewFinanHome.table_doctos.getSelectedRow();
                if(rowSelected == -1){
                    JOptionPane.showMessageDialog(viewFinanHome,"Seleccionar un documento para su aprobacion","Informacion",JOptionPane.WARNING_MESSAGE);
                }else{
                    String typeDocto = viewFinanHome.table_doctos.getValueAt(rowSelected,1).toString();
                    if(typeDocto.equals("Compra")){
                       CCompra compra = CCompra.getCompra(Integer.parseInt(viewFinanHome.table_doctos.getValueAt(rowSelected,0).toString()), cn);
                       
                        Controller_JFAprobarCompra JFAprobarCompra = new Controller_JFAprobarCompra(login, cn);
                        JFAprobarCompra.loadData(compra);
                        JFAprobarCompra.viewData();
                        viewFinanHome.dispose();
                        
                    }else{
                        //Venta
                    }
                }
            }

        });
          //Button setttings account 
        this.viewFinanHome.btn_settings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsUser JFSEttings = new Controller_JFSettingsUser("ControllerFinanciamiento", login, cn);
                viewFinanHome.dispose();
            }
        });
        this.viewFinanHome.btn_logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewFinanHome,"¿Confirmar cierre de sesion?","Cerrar sesion",JOptionPane.YES_NO_OPTION);

                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewFinanHome.dispose();
                }
            }
        });
        this.viewFinanHome.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                App_carshop.init();
                viewFinanHome.dispose();
            }
        });
       
        
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
