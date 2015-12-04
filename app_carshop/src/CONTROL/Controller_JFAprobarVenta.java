/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CONTROL;

import MODELO.AbsJasperReports;
import MODELO.CAuto;
import MODELO.CCliente;
import MODELO.CLogin;
import MODELO.CUsuario;
import MODELO.CVenta;
import VISTA.JFAprobarVenta;
import app_carshop.App_carshop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Byter
 */
public class Controller_JFAprobarVenta {
    JFAprobarVenta viewAprobarVenta;
    Connection cn;
    
    CVenta venta;
    CAuto auto;
    CUsuario encargado;
    CCliente cliente;
    
    public Controller_JFAprobarVenta(CLogin login,Connection cn){
        this.viewAprobarVenta = new JFAprobarVenta();
        this.cn = cn;
        venta = new CVenta();
        //Action when press button Aprobacion
        this.viewAprobarVenta.btn_aprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                   JTextArea area = new JTextArea();
                   area.setLineWrap(true);
                   area.setColumns(20);
                   area.setRows(10);
                   String comentario = "";
                    JOptionPane.showMessageDialog(viewAprobarVenta,area,"Comentario de aprobacion de la venta:",JOptionPane.INFORMATION_MESSAGE);
                    comentario += area.getText();
                if(CVenta.Aprobar(venta.getNumero_factura(), comentario, cn)){
                    CAuto.setEstado(cn,venta.getAuto_numserie(),"Vendido");
                     Object [] options = {"Generar factura","Ir al menu principal"};
                    int optS = JOptionPane.showOptionDialog(viewAprobarVenta, "Venta aprobada,!Auto vendido!", "Mensaje",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null,options,options[0]);
                    if(optS==0){
                        //Detalle en reporte
                         String pathVenta = App_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                       File aux =new File(pathVenta);
                        if (aux.isDirectory())
                            pathVenta = pathVenta + "/Reportes/ReporteVenta.jasper";
                        else
                            pathVenta = aux.getParent() + "/Reportes/ReporteVenta.jasper";

                        AbsJasperReports.createReportVenta(cn,pathVenta,venta.getNumero_factura(),cal_pagoMensual(venta.getTipo_pago(),venta.getTotal()));
                        AbsJasperReports.showViewer();
                    } 
                    Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                         viewAprobarVenta.dispose();
                }else{
                    JOptionPane.showMessageDialog(viewAprobarVenta,"Error al aprobar la venta intente de nuevo","Mensaje",JOptionPane.ERROR_MESSAGE);
                     Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                     viewAprobarVenta.dispose();
                }
            }
        });
        
        //Action when press button no Aprobacion
        this.viewAprobarVenta.btn_NOAprobar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int resp = JOptionPane.showConfirmDialog(viewAprobarVenta,"¿Esta seguro que NO quiere aprobar esta venta?","Mensaje de advertencia",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(resp == JOptionPane.YES_OPTION){ 
                       JTextArea area = new JTextArea();
                       area.setLineWrap(true);
                       area.setColumns(20);
                       area.setRows(10);
                       String comentario = "";
                        JOptionPane.showMessageDialog(viewAprobarVenta,area,"Comentario por que no aprobo la venta:",JOptionPane.INFORMATION_MESSAGE);
                        comentario += area.getText();
                    if(CVenta.noAprobar(venta.getNumero_factura(), comentario, cn)){
                        CAuto.setEstado(cn,venta.getAuto_numserie(),"Disponible");
                         JOptionPane.showMessageDialog(viewAprobarVenta,"Venta NO aprobada,El auto esta disponible nuevamente...","Mensaje",JOptionPane.INFORMATION_MESSAGE);
                         Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                         viewAprobarVenta.dispose(); 
                    }else{
                        JOptionPane.showMessageDialog(viewAprobarVenta,"Error al aprobar la venta intente de nuevo","Mensaje",JOptionPane.ERROR_MESSAGE);
                         Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                         viewAprobarVenta.dispose();
                    }
                 }
            }
        });
        //See more details for car
        this.viewAprobarVenta.btn_detalles_auto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_DetallesAuto detalles_auto = new Controller_DetallesAuto(login, cn, "Venta");
                detalles_auto.setData(venta, encargado, auto, cliente);
                viewAprobarVenta.dispose();
            }
        });
        //See more details for Custom
        this.viewAprobarVenta.btn_detalles_cliente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Controller_DetallesCliente detallesCliente = new Controller_DetallesCliente(login, cn,"Venta");
                detallesCliente.setData(venta, encargado, auto, cliente);
                detallesCliente.viewData();
                viewAprobarVenta.dispose();
            }
        });
        this.viewAprobarVenta.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Controller_JFFinanciamientoHome JFFinHome = new Controller_JFFinanciamientoHome(login, cn);
                viewAprobarVenta.dispose();
            }
        });
    }
    public void viewData(){
       //set fecha
        this.viewAprobarVenta.txt_fecha.setText(venta.getFecha());
      //Set data cliente
        this.viewAprobarVenta.txt_ClienteNombre.setText(cliente.getNombre());
        this.viewAprobarVenta.txt_clienteCveElector.setText(cliente.getClaveElector());
      //Set data auto
        this.viewAprobarVenta.txt_numSerie.setText(auto.getNumero_serie());
        String descripcion = "Marca: "+auto.getMarca()+"\nTipo: "+auto.getTipo()+"\n Modelo: "+auto.getModelo();
        this.viewAprobarVenta.txtArea_descripcion.setText(descripcion);
        this.viewAprobarVenta.txt_Precio.setText(auto.getPrecio_venta()+"  $");
      //Set data encargado
        this.viewAprobarVenta.txt_encargadoVenta.setText(encargado.getNombre()+" "+encargado.getApellido_pat()+" "+encargado.getApellido_mat());
      //Set data venta
        this.viewAprobarVenta.txt_tipoPago.setText(venta.getTipo_pago());
        this.viewAprobarVenta.txt_Descuento.setText(calcularDescuento(venta.getTipo_pago(),auto.getPrecio_venta()) +" $");
        this.viewAprobarVenta.txt_total.setText(venta.getTotal()+ "$");
    }

    public void setData(CVenta venta,CUsuario encargado, CAuto auto,CCliente cliente) {
        this.venta = venta;
        this.auto = auto;
        this.cliente = cliente;
        this.encargado = encargado;
    }
    public void loadData(CVenta venta){
        this.venta = venta;
        this.auto = CAuto.getObject(cn, venta.getAuto_numserie());
        this.encargado = CUsuario.getObject(venta.getEncargado_cve(), cn);
        this.cliente = CCliente.getObject(cn,venta.getCliente_cve());
    }
   
    
    
      public float calcularDescuento(String tipo,float precio){
        float descuento = 0;
         switch(tipo){
                case ("Contado"):
                    float descuento03 = (float) (auto.getPrecio_venta()*0.03);
                    descuento =descuento03;
                    break;
                case ("6 meses"):
                    float descuento02 = (float) (auto.getPrecio_venta()*0.02);
                    descuento = descuento02;
                    break;
                case ("12 meses"): 
                    descuento = 0;
                    break;
            }
         return descuento;
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
