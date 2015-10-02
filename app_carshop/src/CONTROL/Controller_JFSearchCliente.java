/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CCliente;
import MODEL.CCompra;
import MODEL.CLogin;
import MODEL.CUsuario;
import VIEW.JFSearchCliente;
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
public class Controller_JFSearchCliente {
    JFSearchCliente viewSearchCliente;
    Connection cn;
    String typeUser;
    CCompra compra;
    
    public Controller_JFSearchCliente(CLogin login,Connection cn,String transc){
        this.typeUser = CUsuario.tipoUser(login.getClave_elector(), cn); 
        
        if(typeUser.equals("Gerente")){
            this.viewSearchCliente = new JFSearchCliente("Show");
        }else{
            this.viewSearchCliente = new JFSearchCliente("Search");
        }
        this.viewSearchCliente.label_usuario.setText("USUARIO: "+login.getUsuario());
        this.viewSearchCliente.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(), login.getNombreImagen()).getImage().getScaledInstance(viewSearchCliente.label_ImageEmpleado.getWidth(),viewSearchCliente.label_ImageEmpleado.getHeight(), Image.SCALE_SMOOTH)));

        loadTable(viewSearchCliente.table_showClientes,CCliente.queryAll(cn));
        //Action when press button seleccionar
        this.viewSearchCliente.btn_seleccionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowSelected = viewSearchCliente.table_showClientes.getSelectedRow();
                if(rowSelected == -1){
                   JOptionPane.showMessageDialog(viewSearchCliente, "Seleccione un registro","Mensaje",JOptionPane.WARNING_MESSAGE);
                }else{
                    Controller_JFAddCompra JFAddCompra = new Controller_JFAddCompra(login, cn);
                    compra.setCliente_cve(viewSearchCliente.table_showClientes.getValueAt(rowSelected, 0).toString());
      
                    JFAddCompra.setCompra(compra);
                    JFAddCompra.setData();
                    viewSearchCliente.dispose();
                }
            }
        });
        //Action  when press  button addCliente
        this.viewSearchCliente.btn_addNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFAddCliente JFAddCliente = new Controller_JFAddCliente(login, cn);
                viewSearchCliente.dispose();
            }
        });
        //Funciones de cerrar this JFRAME
        this.viewSearchCliente.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                switch(typeUser){
                    case ("Gerente"):
                        Controller_JFGerenteHome JFGerenteHome = new Controller_JFGerenteHome(login, cn);
                        viewSearchCliente.dispose();
                        break;
                    case ("Comercio"):
                        if(transc.equals("Compra")){
                            Controller_JFAddCompra JFAddCompra= new Controller_JFAddCompra(login, cn);
                            JFAddCompra.setCompra(compra);
                            JFAddCompra.setData();
                            viewSearchCliente.dispose();
                            
                            
             
                        }else if(transc.equals("Venta")){
                            Controller_JFAddVenta JFAddVenta= new Controller_JFAddVenta(login, cn);
                            viewSearchCliente.dispose();
                        }
                        break;
                    case ("Financiamiento"):
                        //Controller financiamiento
                        viewSearchCliente.dispose();
                        break;                        
                
                }
            }
        });
        //All dar clic en buscar
        this.viewSearchCliente.btn_buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               String value = viewSearchCliente.txt_valueSearch.getText();
               if(value.isEmpty()){
                    JOptionPane.showMessageDialog(viewSearchCliente, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
               }else{
                   switch(viewSearchCliente.cbox_searchFor.getSelectedItem().toString()){
                       case ("Clave elector"):
                           loadTable(viewSearchCliente.table_showClientes, CCliente.queryForClaveElector(cn, value));
                           break;
                       case ("Nombre"):
                           loadTable(viewSearchCliente.table_showClientes, CCliente.queryForName(cn, value));
   
                           break;
                       case ("Apellido paterno"):
                           loadTable(viewSearchCliente.table_showClientes, CCliente.queryForApellido_Pat(cn, value));
                           break;
                       case ("RFC"):
                           loadTable(viewSearchCliente.table_showClientes, CCliente.queryForRFC(cn, value));
                           break;
                   }
               }
            }
        });
        //Al cambiar valor de combo box filtro
        this.viewSearchCliente.cbox_searchFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewSearchCliente.txt_valueSearch.setText(null);
                if(viewSearchCliente.cbox_searchFor.getSelectedItem().toString().equals("Todos")){
                    loadTable(viewSearchCliente.table_showClientes,CCliente.queryAll(cn));
                }
            }
        });
        this.viewSearchCliente.btn_cancelar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                  switch(typeUser){
                    case ("Gerente"):
                        Controller_JFGerenteHome JFGerenteHome = new Controller_JFGerenteHome(login, cn);
                        viewSearchCliente.dispose();
                        break;
                    case ("Comercio"):
                        if(transc.equals("Compra")){
                            Controller_JFAddCompra JFAddCompra= new Controller_JFAddCompra(login, cn);
                            JFAddCompra.setCompra(compra);
                            JFAddCompra.setData();
                            viewSearchCliente.dispose();
                        }else if(transc.equals("Venta")){
                            Controller_JFAddVenta JFAddVenta= new Controller_JFAddVenta(login, cn);
                            viewSearchCliente.dispose();
                        }
                        break;
                    case ("Financiamiento"):
                        //Controller financiamiento
                        viewSearchCliente.dispose();
                        break;                        
                
                }
            }
        });
    }
     public void setCompra(CCompra compra){
         this.compra = compra;
     }
     public CCompra getCompra(){
         return this.compra;
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
            Logger.getLogger(Controller_JFSearchCliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
    
  public void loadTable(JTable tabla,ArrayList<CCliente> clientes){
        
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
	};
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.removeAll();
        model.addColumn("Clave elector");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        model.addColumn("RFC");
        model.addColumn("Ingreso mensual");
        
        for (CCliente cliente : clientes){
            Object[] user = new Object[8];
                user[0] = cliente.getClaveElector();
                user[1] = cliente.getNombre();
                user[2] = cliente.getApellido_pat()+" "+cliente.getApellido_mat();
                user[3] = cliente.getDireccion();
                user[4] = cliente.getTelefono();
                user[5] = cliente.getRfc();
                user[6] = cliente.getIngresoMensual();
            model.addRow(user);
        }
        tabla.setModel(model);
    }
    
}
