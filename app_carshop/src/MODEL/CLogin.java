/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ByteDrive
 */
public class CLogin {
    private int id_usuario;
    private String usuario;
    private String password;
    private Blob imagen;
    private String nombreImagen;

    public CLogin() {
    }
    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_user) {
        this.id_usuario = id_user;
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
    
    public static CLogin verificarLogin(String txtUser,Connection cn){
         CLogin user = null;
        try {
            PreparedStatement pps = cn.prepareStatement("SELECT *from login where usuario = ?");
            pps.setString(1, txtUser);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                user = new CLogin();
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setUsuario(rs.getString("usuario"));
                user.setUsuario(rs.getString("password"));
                user.setImagen(rs.getBlob("imagen"));
                user.setNombreImagen(rs.getString("nombre_imagen"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    
    
}
