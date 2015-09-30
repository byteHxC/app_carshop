/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import VIEW.JFAddCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class CCliente {
   private String claveElector;
   private String nombre;
   private String apellido_pat;
   private String apellido_mat;
   private String telefono;
   private String rfc;
   private String direccion;
   private float ingresoMensual;

    public CCliente() {
    }

    public String getClaveElector() {
        return claveElector;
    }

    public void setClaveElector(String claveElector) {
        this.claveElector = claveElector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(float ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }
   
    public void saveObject(Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("INSERT INTO clientes (cve_elector,nombre,apellido_pat,apellido_mat,telefono,rfc,direccion,ingreso_mensual) VALUES (?,?,?,?,?,?,?,?)");
            pps.setString(1,claveElector);
            pps.setString(2, nombre);
            pps.setString(3,apellido_pat);
            pps.setString(4,apellido_mat);
            pps.setString(5,telefono);
            pps.setString(6, rfc);
            pps.setString(7, direccion);
            pps.setFloat(8, ingresoMensual);
            pps.executeUpdate();
            System.out.println("ECliente.saveObject() successful");
        } catch (SQLException ex) {
           Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    public static boolean existClaveElector(Connection cn,String cveElector){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT cve_elector FROM clientes WHERE cve_elector = ?");
            pps.setString(1, cveElector);
            ResultSet rs = pps.executeQuery();
            while(rs.next())
                return true;
        } catch (SQLException ex) {
           Logger.getLogger(CCliente.class.getName()).log(Level.SEVERE, null, ex);
       }
        return false;
    }
    
    public boolean validarDatos(JFAddCliente frame,Connection cn){
        String txtErrores = "Verficar datos del cliente\n";
        boolean errores = false;
        String sAux = getIngresoMensual() + "";
        //Aqui valida mujer
        if(existClaveElector(cn, claveElector)){
            txtErrores += "La clave de elector ya existe, verificar";
            errores = true;
        }
        if(!(claveElector.matches("[a-zA-Z]{6}[0-9]{6}[0-9]{2}[A-Za-z]{1}[0-9]{3}"))){
            txtErrores += "\t-Clave elector invalida [18 caracteres]\n";
            errores = true;
        }
        /*
        El rfc se compone de 13 caracteres
            los primeros dos son las primeras dos letras del apellido paterno
            el siguiente caracter es la primer letra del apellido materno
            el siguiente caracter es la primer letra del primer nombre
            los 6 siguientes caracteres son numeros [fecha de nacimiento], empezando por
                el aÒo, mes, dia
            los ultimos 3 caracteres son asignados por el sat, pueden ser numeros y/o letras    
        */
        if(rfc.length()>0){
            if(!(rfc.matches("[a-zA-Z]{4}[0-9]{6}[a-zA-Z1-9]{3}"))){
            txtErrores += "\t-RFC invalido [13] caracteres]\n";
            errores = true;
        }
        }
        
        if(!(nombre.matches("[a-zA-Z]+([ ]*[a-zA-Z]+)+"))){
            txtErrores += "\t-Nombre invalido\n";
            errores = true;
        }
        if(!(apellido_pat.matches("[a-zA-Z]{3,30}"))){
            txtErrores += "\t-Apellido paterno invalido\n";
            errores = true;
        }
        if(!(apellido_mat.matches("[a-zA-Z]{3,30}"))){
            txtErrores += "\t-Apellido materno invalido\n";
            errores = true;
        }
        if(!(sAux.equals("0.0"))){
            if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?") ){
                txtErrores += "\t-Ingreso mensual invalido\n";
                errores = true;
            }
        }
        if(!(direccion.length()>=200 || direccion.matches("[a-zA-Z]+([ ]*[a-zA-Z0-9]+)+"))){   
                txtErrores += "\t-Verificar direccion [200] caracteres\n";
                errores = true;
        }
        if(!(telefono.matches("[0-9]{5,15}")) ){
            txtErrores += "\t-Telefono invalido\n";
            errores = true;
        }
        if(errores){
            JOptionPane.showMessageDialog(frame, txtErrores, "Validacion de datos de clientes", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
   
    
}
