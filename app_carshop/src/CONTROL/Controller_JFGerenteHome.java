/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.AbsJasperReports;
import MODELO.CLogin;
import MODELO.CVenta;
import VISTA.JFGerenteHome;
import app_carshop.app_carshop;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
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
public class Controller_JFGerenteHome {
    JFGerenteHome viewGerenteHome;
    File fileSelected;
    
    public Controller_JFGerenteHome(CLogin login,Connection cn){
        this.viewGerenteHome = new JFGerenteHome();
        //Settings view labels identifications
        this.viewGerenteHome.label_usuario.setText("Bienvenido "+login.getUsuario());
        this.viewGerenteHome.label_ImageEmpleado.setIcon(new ImageIcon(getImageWithBlob(login.getImageBlob(),login.getNombreImagen()).getImage().getScaledInstance(viewGerenteHome.label_ImageEmpleado.getWidth(),viewGerenteHome.label_ImageEmpleado.getHeight(),Image.SCALE_SMOOTH)));
        //evento para instanciar controlador agregarempleado
        this.viewGerenteHome.btn_addEmpleado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
                Controller_AgregarEmpleado agregarEmpleado = new Controller_AgregarEmpleado(login, cn);
                viewGerenteHome.dispose();
            }
        });
        //evento para instanciar controlador modificar empleados
        this.viewGerenteHome.btn_listEmpleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
                Controller_JFListarEmpleados listarEmpleados = new Controller_JFListarEmpleados(login, cn);
                viewGerenteHome.dispose();
            }
        });
        //Evento al precionar btn_empleados, desplegar opciones
        this.viewGerenteHome.btn_empleados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.setVisible(true);
            }
        });
        //cancelar despligue de opciones en empleado
        this.viewGerenteHome.btn_cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                viewGerenteHome.dialog_opcEmpleado.dispose();
            }
            
        });
       
        
        //Button the settings in this account
        this.viewGerenteHome.btn_settings.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_JFSettingsUser JFSettingsUser = new Controller_JFSettingsUser("ControllerGerente",login, cn);
                viewGerenteHome.dispose();
            }
        });
        
       
        
        //Boton para consultar ventas
        this.viewGerenteHome.btn_ventas.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Object [] options = {"Generar factura mensual","Generar factura especifica"};
                    int optS = JOptionPane.showOptionDialog(viewGerenteHome, "Seleccione el tipo de factura: ", "Mensaje",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                    if(optS==0){
                        //Factura mensual
                        try{
                         int month = Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el mes","Reportes",JOptionPane.INFORMATION_MESSAGE));
                         int year = Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el anio ","Reportes",JOptionPane.INFORMATION_MESSAGE));
                         String path = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                             File aux =new File(path);
                            if (aux.isDirectory())
                                path = path + "/Reportes/ListaVentas.jasper";
                            else
                                path = aux.getParent() + "/Reportes/ListaVentas.jasper";

                            AbsJasperReports.createReporteListaV(cn, path, year, month);
                            AbsJasperReports.showViewer();
                            
                        }catch(HeadlessException | NumberFormatException err){
                            JOptionPane.showMessageDialog(viewGerenteHome,"Datos ingresados invalidos","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }else if(optS==1){
                        //Factura especifica
                        try{
                         int num_fact =Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el numero de factura: ","Reportes",JOptionPane.INFORMATION_MESSAGE));
                        CVenta venta = CVenta.getVenta(num_fact, cn); 
                        //Detalle en reporte
                             String pathVenta = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                             File aux =new File(pathVenta);
                            if (aux.isDirectory())
                                pathVenta = pathVenta + "/Reportes/ReporteVenta.jasper";
                            else
                                pathVenta = aux.getParent() + "/Reportes/ReporteVenta.jasper";

                            AbsJasperReports.createReportVenta(cn,pathVenta,venta.getNumero_factura(),cal_pagoMensual(venta.getTipo_pago(),venta.getTotal()));
                            AbsJasperReports.showViewer();
                            
                        }catch(HeadlessException | NumberFormatException err){
                            JOptionPane.showMessageDialog(viewGerenteHome,"No se encontro el numero de factura,verificar","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }
            }
        });
        
        //Boton para consultar compras
        this.viewGerenteHome.btn_compras.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
            
                Object [] options = {"Generar factura mensual","Generar factura especifica"};
                    int optS = JOptionPane.showOptionDialog(viewGerenteHome, "Seleccione el tipo de factura: ", "Mensaje",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                    if(optS==0){
                        //Factura mensual
                        try{
                         int month = Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el mes","Reportes",JOptionPane.INFORMATION_MESSAGE));
                         int year = Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el anio ","Reportes",JOptionPane.INFORMATION_MESSAGE));
                         String path = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                             File aux =new File(path);
                            if (aux.isDirectory())
                                path = path + "/Reportes/ListaCompras.jasper";
                            else
                                path = aux.getParent() + "/Reportes/ListaCompras.jasper";

                            AbsJasperReports.createReporteListaC(cn, path, year, month);
                            AbsJasperReports.showViewer();
                            
                        }catch(HeadlessException | NumberFormatException err){
                            JOptionPane.showMessageDialog(viewGerenteHome,"Datos ingresados invalidos","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }else if(optS==1){
                        //Factura especifica
                        try{
                         int num_fact =Integer.parseInt(JOptionPane.showInputDialog(viewGerenteHome,"Ingrese el numero de factura: ","Reportes",JOptionPane.INFORMATION_MESSAGE));
                         String pathCompra = app_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                           File aux =new File(pathCompra);
                            if (aux.isDirectory())
                                pathCompra = pathCompra + "/Reportes/ReporteComprajrxml.jasper";
                            else
                                pathCompra = aux.getParent() + "/Reportes/ReporteComprajrxml.jasper";

                           AbsJasperReports.createReportCompra(cn,pathCompra, num_fact);
                           AbsJasperReports.showViewer();
                            
                        }catch(HeadlessException | NumberFormatException err){
                            JOptionPane.showMessageDialog(viewGerenteHome,"No se encontro el numero de factura,verificar","Mensaje",JOptionPane.WARNING_MESSAGE);
                        }
                    }
            }
        });
        //Boton de cerrar cesion
        this.viewGerenteHome.btn_logout.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Confirmar cierre de sesion?","Cerrar sesion",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    viewGerenteHome.dispose();
                    app_carshop.init();
                }
            }
        });
        //Boton al cerrar al frame
        this.viewGerenteHome.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewGerenteHome,"¿Si sale de aqui, se cerrara su sesion?","Salir",JOptionPane.YES_NO_OPTION);
                if(resp == JOptionPane.YES_OPTION){
                    app_carshop.init();
                    viewGerenteHome.dispose();
                }
                
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
            Logger.getLogger(Controller_JFGerenteHome.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return image;
     }
      public String cal_pagoMensual(String tipo,float precio){
              String pago = "";
         switch(tipo){
                case ("Contado"):
                    pago = ""+precio+" $";
                    break;
                case ("6 meses"):
                    pago = ""+precio/6+" $";
                    break;
                case ("12 meses"): 
                    pago = ""+precio/12+" $";
            }
         return pago;
      }
}
