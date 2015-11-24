/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import VISTA.JFAgregarVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Byter
 */
public class CVenta {
    
    private int numero_factura;
    private String fecha;
    private float total;
    private String tipo_pago;
    private boolean aprobacion;
    private String auto_numserie;
    private String encargado_cve;
    private String cliente_cve;
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public CVenta() {
        this.auto_numserie = "";
        this.encargado_cve = "";
        this.cliente_cve = "";
    }

    public int getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(int numero_factura) {
        this.numero_factura = numero_factura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public boolean isAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

    public String getAuto_numserie() {
        return auto_numserie;
    }

    public void setAuto_numserie(String auto_numserie) {
        this.auto_numserie = auto_numserie;
    }

    public String getEncargado_cve() {
        return encargado_cve;
    }

    public void setEncargado_cve(String encargado_cve) {
        this.encargado_cve = encargado_cve;
    }

    public String getCliente_cve() {
        return cliente_cve;
    }

    public void setCliente_cve(String cliente_cve) {
        this.cliente_cve = cliente_cve;
    }

     public void saveObject(Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("INSERT INTO ventas(fecha,total,tipo_pago,auto_numserie,encargado_cve,cliente_cve) values (?,?,?,?,?,?)");
            pps.setString(1, DateTime.getNow().toString());
            pps.setFloat(2, total);
            pps.setString(3, tipo_pago);
            pps.setString(4, auto_numserie);
            pps.setString(5,encargado_cve);
            pps.setString(6,cliente_cve);
            pps.executeUpdate();
            //poner el auto en estado ocupado para que no lo pueda ocupar otro wey :p mientras se aproba o no la venta
            CAuto.setEstado(cn, auto_numserie,"Ocupado");
            System.out.println("Venta.saveObject() successful");
        } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public boolean validarVenta(JFAgregarVenta frame, Connection cn){
        String txtError = "Verificar los siguientes datos de la venta\n";
        boolean errores = false;
         String sAux = getTotal() + "";
         
        if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?")){
            txtError += "\t-Total invalido\n";
            errores = true;
        }
        if (validarCliente(getCliente_cve(), cn) ==false ) {
            txtError += "\t-Error al encontrar al cliente\n";
            errores = true;
        }
        
        if(errores){
            JOptionPane.showMessageDialog(frame, txtError, "Validacion de datos de la venta", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
     public boolean validarAuto(String numero_serie,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT numero_serie from catalogo_autos where numero_serie = ?");
            pps.setString(1, numero_serie);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     public boolean validarCliente(String cve_elector,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT cve_elector from clientes where cve_elector = ?");
            pps.setString(1, cve_elector);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     public static ArrayList<CVenta> getVentas(Connection cn){
         ArrayList<CVenta> ventas = new ArrayList<>();
         try{
             PreparedStatement pps = cn.prepareStatement("SELECT * FROM ventas where aprobacion is null");
             
             ResultSet rs = pps.executeQuery();
             while(rs.next()){
                 CVenta venta = new CVenta();
                 venta.setNumero_factura(rs.getInt("numero_factura"));
                 venta.setFecha(rs.getString("fecha"));
                 venta.setTotal(rs.getFloat("total"));
                 venta.setTipo_pago(rs.getString("tipo_pago"));
                 venta.setAprobacion(rs.getBoolean("aprobacion"));
                 venta.setAuto_numserie(rs.getString("auto_numserie"));
                 venta.setEncargado_cve(rs.getString("encargado_cve"));
                 venta.setCliente_cve(rs.getString("cliente_cve"));
                 venta.setComentario(rs.getString("comentario"));
                ventas.add(venta);
             }
             
         } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ventas;
     }
     public static CVenta getVenta(int numero_factura, Connection cn){
         CVenta venta = new CVenta();
         try{
             PreparedStatement pps = cn.prepareStatement("SELECT * FROM ventas where numero_factura = ?");
             pps.setInt(1, numero_factura);
             ResultSet rs = pps.executeQuery();
             if(rs.next()){
                 venta.setNumero_factura(rs.getInt("numero_factura"));
                 venta.setFecha(rs.getString("fecha"));
                 venta.setTotal(rs.getFloat("total"));
                 venta.setTipo_pago(rs.getString("tipo_pago"));
                 venta.setAprobacion(rs.getBoolean("aprobacion"));
                 venta.setAuto_numserie(rs.getString("auto_numserie"));
                 venta.setEncargado_cve(rs.getString("encargado_cve"));
                 venta.setCliente_cve(rs.getString("cliente_cve"));
                 venta.setComentario(rs.getString("comentario"));
             }
         } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
         return venta;
     }
     
     
      public static boolean Aprobar(int numero_factura,String comentario,Connection cn){
         try{
             PreparedStatement pps = cn.prepareStatement("update ventas set aprobacion = true,comentario= ? where numero_factura = ?");
             pps.setString(1, comentario);
             pps.setInt(2, numero_factura);
             pps.executeUpdate();
             System.out.println("venta aprobada");
             return true;
         } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
     }
     public static boolean noAprobar(int numero_factura,String comentario,Connection cn){
          try{
             PreparedStatement pps = cn.prepareStatement("update ventas set aprobacion = false,comentario = ?  where numero_factura = ?");
             pps.setString(1, comentario);
             pps.setInt(2, numero_factura);
             pps.executeUpdate();
              System.out.println("venta no aprobada");
             return true;
         } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
     }
    
    
}
