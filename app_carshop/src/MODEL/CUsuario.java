/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ByteDrive
 */
public class CUsuario {
    private int id_usuario;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String direccion;
    private String telefono;
    private String tipo;
    private float salario;

    public CUsuario() {
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    public int getId_usuario() {
        return id_usuario;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    public  static CUsuario getObject(String nombre,String apellido_pat,String apellido_mat,Connection cn){
        CUsuario user = null;
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT *from usuarios where nombre=? & apellido_paterno=? & apellido_materno=?");
            pps.setString(1, nombre);
            pps.setString(2, apellido_pat);
            pps.setString(3, apellido_mat);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                user = new CUsuario();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setApellido_pat(rs.getString("apellido_paterno"));
                user.setApellido_mat(rs.getString("apellido_materno"));
                user.setDireccion(rs.getString("direccion"));
                user.setTelefono(rs.getString("telefono"));
                user.setTipo(rs.getString("tipo"));
                user.setSalario(rs.getFloat("salario"));
                return user;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    public void saveObject(Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("INSERT INTO usuarios (nombre,apellido_paterno,apellido_materno,direccion,telefono,tipo,salario) values (?,?,?,?,?,?,?) ");
            pps.setString(1,getNombre());
            pps.setString(2,getApellido_pat());
            pps.setString(3,getApellido_mat());
            pps.setString(4,getDireccion());
            pps.setString(5, getTelefono());
            pps.setString(6,getTipo());
            pps.setFloat(7,getSalario());
            pps.executeUpdate();
            System.out.println("Usuario.SaveObject() sucessful");
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String tipoUser(int id_usuario,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT tipo FROM usuarios WHERE id_usuario =?");
            pps.setInt(1, id_usuario);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                return rs.getString("tipo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error en tipo";
    }
    
}
