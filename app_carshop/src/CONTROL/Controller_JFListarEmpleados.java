/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODEL.CLogin;
import MODEL.CUsuario;
import VIEW.JFListarEmpleados;
import app_carshop.App_carshop;
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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ByteDrive
 */
public class Controller_JFListarEmpleados {
    JFListarEmpleados viewEmpleados;
    //Cuando entra el gerente
    public Controller_JFListarEmpleados(CLogin login,Connection cn){
        viewEmpleados = new  JFListarEmpleados();
        loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryAll(cn));
        //Botojn de editar empleado
        this.viewEmpleados.btn_EditarEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 if(viewEmpleados.table_showEmpleados.getSelectedRowCount()==0){
                    JOptionPane.showMessageDialog(viewEmpleados,"Seleccione un registro","Error",JOptionPane.WARNING_MESSAGE);

                 }else{
                   Controller_JFModificarEmpleado CTupdateEmpleado = new Controller_JFModificarEmpleado(login, cn,viewEmpleados.table_showEmpleados.getValueAt(viewEmpleados.table_showEmpleados.getSelectedRow(),0).toString(), viewEmpleados.table_showEmpleados.getValueAt(viewEmpleados.table_showEmpleados.getSelectedRow(),1).toString());
                     viewEmpleados.dispose();
                 }
                 
                
                
            }
        });
        
        //Dar de baja un empelado
        this.viewEmpleados.btn_bajaEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(viewEmpleados.table_showEmpleados.getSelectedRowCount()==0){
                   //No hay seleccion
                    JOptionPane.showMessageDialog(viewEmpleados,"Seleccione un registro","Error",JOptionPane.WARNING_MESSAGE);
                }else{
                   boolean bajaExitosa = CUsuario.bajaEstado(viewEmpleados.table_showEmpleados.getValueAt(viewEmpleados.table_showEmpleados.getSelectedRow(),0).toString(), cn);
                   if(bajaExitosa){
                       JOptionPane.showMessageDialog(viewEmpleados,"Empleado dado de baja :D");
                       loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryAll(cn));
                   }else{
                       JOptionPane.showMessageDialog(viewEmpleados,"El empleado ya esta dado de baja :D");

                   }
                }
            }
        });
        
        //Dar de alta un empleado
        this.viewEmpleados.btn_altaEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(viewEmpleados.table_showEmpleados.getSelectedRowCount()==0){
                   //No hay seleccion
                    JOptionPane.showMessageDialog(viewEmpleados,"Seleccione un registro","Error",JOptionPane.WARNING_MESSAGE);
                }else{
                   boolean altaExitosa = CUsuario.altaEstado(viewEmpleados.table_showEmpleados.getValueAt(viewEmpleados.table_showEmpleados.getSelectedRow(),0).toString(), cn);
                   if(altaExitosa){
                       JOptionPane.showMessageDialog(viewEmpleados,"Empleado dado de alta :D");
                       loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryAll(cn));
                   }else{
                       JOptionPane.showMessageDialog(viewEmpleados,"El empleado ya esta dado de alta :D");

                   }
                }
            }
        });
        this.viewEmpleados.btn_search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String value = viewEmpleados.txt_valueSearch.getText();
                switch(viewEmpleados.cbox_searchFor.getSelectedItem().toString()){
                    
                    case ("Clave elector"):
                        
                            if(!value.isEmpty()){
                                  loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryForClaveElector(cn,value));
                            }else{
                                JOptionPane.showMessageDialog(viewEmpleados, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
                            }
                        break;
                    case ("Nombre"):
                        if(!value.isEmpty()){
                                  loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryForNombre(cn,value));
                            }else{
                                JOptionPane.showMessageDialog(viewEmpleados, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
                            }
                        break;
                    case("Tipo"):
                        if(!value.isEmpty()){
                                  loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryForType(cn,value));
                            }else{
                                JOptionPane.showMessageDialog(viewEmpleados, "Ingrese un valor para buscar","Revisar campos",JOptionPane.WARNING_MESSAGE);
                            }
                        
                        break;
                }
            }
        
        });
        
        this.viewEmpleados.cbox_searchFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                  viewEmpleados.txt_valueSearch.setText(null);
                  if(viewEmpleados.cbox_searchFor.getSelectedItem().toString().equals("Todos")){
                       loadTable(viewEmpleados.table_showEmpleados,CUsuario.queryAll(cn));
        
                  }
            }
        });
        //Dar en boton cancelar
        this.viewEmpleados.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFGerenteHome controller = new Controller_JFGerenteHome(login, cn);
                viewEmpleados.dispose();
            }
        });
        //Al cerrar el jframe
        this.viewEmpleados.addWindowListener(new WindowAdapter() {
           @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewEmpleados,"¿Si sale de aqui, se cerrara su sesion?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    App_carshop.init();
                    viewEmpleados.dispose();
                }
                
            }
            });
       
        
    }
    
    public void loadTable(JTable tabla,ArrayList<CUsuario> usuarios){
        
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                    return false;
            }
	};
        tabla.removeAll();
        model.addColumn("Clave elector");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("Direccion");
        model.addColumn("Telefono");
        model.addColumn("Tipo");
        model.addColumn("Salario");
        model.addColumn("Estado");
        
        for (CUsuario usuario  :usuarios){
            Object[] user = new Object[8];
                user[0] = usuario.getClave_elector();
                user[1] = usuario.getNombre();
                user[2] = usuario.getApellido_pat()+" "+usuario.getApellido_mat();
                user[3] = usuario.getDireccion();
                user[4] = usuario.getTelefono();
                user[5] = usuario.getTipo();
                user[6] = usuario.getSalario();
                if(usuario.getEstado()==true){
                      user[7] = "Alta";
                }else{
                      user[7] = "Baja";
                }
            model.addRow(user);
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
            Logger.getLogger(Controller_JFListarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
}
