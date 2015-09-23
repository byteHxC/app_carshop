/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private String AbsolutePathimagen;
    private Blob imageBlob;
    private String nombreImagen;

    public CLogin() {
    }

    public void setAbsolutePathimagen(String AbsolutePathimagen) {
        this.AbsolutePathimagen = AbsolutePathimagen;
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

    public String getAbsolutePathimagen() {
        return AbsolutePathimagen;
    }

    public void setImageBlob(Blob imageBlob) {
        this.imageBlob = imageBlob;
    }

    public Blob getImageBlob() {
        return imageBlob;
    }

    
    

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombre) {
        this.nombreImagen = nombre;
    }
    
    public void saveObject(Connection cn){
        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream(getAbsolutePathimagen());
            PreparedStatement pps = cn.prepareStatement("INSERT INTO login (usuario,password,imagen,nombre_imagen,id_usuario) values (?,?,?,?,?)");
            pps.setString(1, getUsuario());
            pps.setString(2, getPassword());
            pps.setBlob(3, fileIn);
            pps.setString(4, getNombreImagen());
            pps.setInt(5, getId_usuario());
            pps.executeUpdate();
            System.out.println("Login.SaveObject() sucessful");
        } catch (SQLException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fileIn!=null)
                try {
                    fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                user.setPassword(rs.getString("password"));
                user.setImageBlob(rs.getBlob("imagen"));
                user.setNombreImagen(rs.getString("nombre_imagen"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    
    
    
}
