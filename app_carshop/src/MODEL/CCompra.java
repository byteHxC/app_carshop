/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import VIEW.JFAddCompra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Byter
 */
public class CCompra {
    private String numero_factura;
    private LocalDateTime fecha;
    private float precio;
    private boolean aprobacion;
    private String auto_numserie;
    private String encargado_cve;
    private String cliente_cve;

    public CCompra() {
        cliente_cve = "";
        encargado_cve = "";
        auto_numserie = "";
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
            PreparedStatement pps = cn.prepareStatement("INSERT INTO compras(fecha,precio,aprobacion,auto_numserie,encargado_cve,cliente_cve) values (?,?,?,?,?,?)");
            pps.setString(1, DateTime.getNow().toString());
            pps.setFloat(2, precio);
            pps.setBoolean(3,aprobacion);
            pps.setString(4,auto_numserie);
            pps.setString(5,encargado_cve);
            pps.setString(6,cliente_cve);
            pps.executeUpdate();
            System.out.println("Compra.saveObject() successful");
        } catch (SQLException ex) {
            Logger.getLogger(CCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean validarCompra(JFAddCompra frame, Connection cn){
        String txtError = "Verificar los siguientes datos de la venta\n";
        boolean errores = false;
         String sAux = getPrecio() + "";
        if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?")){
            txtError += "\t-Precio invalido\n";
            errores = true;
        }
        if (validarAuto(getAuto_numserie(), cn) ==false ) {
            txtError += "\t-Error con numero de serie del auto\n";
            errores = true;
        }
        if (validarCliente(getCliente_cve(), cn) ==false ) {
            txtError += "\t-Error al encontrar al cliente\n";
            errores = true;
        }
        if(errores){
            JOptionPane.showMessageDialog(frame, txtError, "Validacion de datos de la compra", JOptionPane.WARNING_MESSAGE);
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
    
}
