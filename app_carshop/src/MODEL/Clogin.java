/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.awt.Image;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ByteDrive
 */
public class Clogin {
    private int id_user;
    private String usuario;
    private String password;
    private Blob imagen;
    private String nombreImagen;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blob getImagen() {
        return imagen;
    }

    public void setImagen(Blob imagen) {
        this.imagen = imagen;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombre) {
        this.nombreImagen = nombre;
    }
    
    public static Clogin verificarUsuario(String txtUser,Connection cn){
         Clogin user = null;
        try {
            PreparedStatement pps = cn.prepareStatement("SELECT *from login where usuario = ?");
            pps.setString(1, txtUser);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                user = new Clogin();
                user.setId_user(rs.getInt("id_usuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setUsuario(rs.getString("password"));
                user.setImagen(rs.getBlob("imagen"));
                user.setNombreImagen(rs.getString("nombre_imagen"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Clogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
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
            Logger.getLogger(Clogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error en tipo";
    }
    
    
}
