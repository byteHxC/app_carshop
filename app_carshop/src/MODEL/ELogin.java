/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;
import VIEW.JFGerenteHome;
import VIEW.JFSettingsDB;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class ELogin {
    private String clave_elector;
    private String usuario;
    private String password;
    private String AbsolutePathimagen;
    private Blob imageBlob;
    private String nombreImagen;

    public String getClave_elector() {
        return clave_elector;
    }

    public void setClave_elector(String clave_elector) {
        this.clave_elector = clave_elector;
    }

    public ELogin() {
    }

    public void setAbsolutePathimagen(String AbsolutePathimagen) {
        this.AbsolutePathimagen = AbsolutePathimagen;
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
    
    public boolean updateObject(Connection cn){
        FileInputStream fileIn = null;
        try{
            PreparedStatement pps = cn.prepareStatement("UPDATE login set imagen=?,nombre_imagen=?,usuario=?,password=? where usuario_cve=?");
          
            fileIn = new FileInputStream(getAbsolutePathimagen());
            pps.setBlob(1,fileIn);
            pps.setString(2,getNombreImagen());
            pps.setString(3,getUsuario());
            pps.setString(4,getPassword());
            pps.setString(5,getClave_elector());
            pps.executeUpdate();
            System.out.println("Login.UpdateObject() successful");
            return true;
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
           if(fileIn!=null)
            try {
                fileIn.close();
           } catch (IOException ex) {
               Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
    public void saveObject(Connection cn){
        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream(getAbsolutePathimagen());
            PreparedStatement pps = cn.prepareStatement("INSERT INTO login (usuario,password,imagen,nombre_imagen,usuario_cve) values (?,?,?,?,?)");
            pps.setString(1, getUsuario());
            pps.setString(2, getPassword());
            pps.setBlob(3, fileIn);
            pps.setString(4, getNombreImagen());
            pps.setString(5, getClave_elector());
            pps.executeUpdate();
            System.out.println("Login.SaveObject() sucessful");
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fileIn!=null)
                try {
                    fileIn.close();
            } catch (IOException ex) {
                Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static ELogin verificarLogin(String txtUser,Connection cn){
         ELogin login = null;
        try {
     
            PreparedStatement pps = cn.prepareStatement("select usuario,password,imagen,nombre_imagen,usuarios.cve_elector,estado from login join usuarios on login.usuario_cve=usuarios.cve_elector where login.usuario=?;");
            pps.setString(1, txtUser);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                login = new ELogin();
                login.setClave_elector(rs.getString("cve_elector"));
                login.setUsuario(rs.getString("usuario"));
                login.setPassword(rs.getString("password"));
                login.setImageBlob(rs.getBlob("imagen"));
                login.setNombreImagen(rs.getString("nombre_imagen"));
                if(rs.getBoolean("estado")==true)
                    return login;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean validarDatos(JFSettingsDB frame, String confirmationPass,Connection cn){
        String txtError = "Verificar datos login\n";
        Boolean errores = false;
        if(getPassword().length() < 6){
            txtError += "\t-La contraseña debe ser mayor a 6 caracteres\n";
            errores = true;
        }
        
        if(!(getPassword().equals(confirmationPass))){
            txtError += "\t-La confirmacion de contraseña es diferente\n";
            errores = true;
        }
         if(!(getUsuario().matches("[a-zA-Z0-9]+[@][a-zA-Z]+"))){
             txtError += "\t-El nombre de usuario debe tener mas de 8 caracteres\n";
             errores= true;
         }
         
         if(existsUsuario(cn,getUsuario())){
            txtError += "\t-El usuario ya existe elija otro.\n";
            errores =true;
         }
         if(errores){
              JOptionPane.showMessageDialog(frame, txtError, "Validación de datos del login", JOptionPane.WARNING_MESSAGE);
               return false;
         }
         return true;
    }
    public boolean validarDatos(JFGerenteHome frame, String confirmationPass,Connection cn){
        String txtError = "Verificar datos login\n";
        Boolean errores = false;
        if(getPassword().length() < 6){
            txtError += "\t-La contraseña debe ser mayor a 6 caracteres\n";
            errores = true;
        }
        
        if(!(getPassword().equals(confirmationPass))){
            txtError += "\t-La confirmacion de contraseña es diferente\n";
            errores = true;
        }
         if(!(getUsuario().matches("[a-zA-Z0-9]+[@][a-zA-Z]+"))){
             txtError += "\t-El nombre de usuario debe tener mas de 8 caracteres\n";
             errores= true;
         }
         
         if(existsUsuario(cn,getUsuario())){
            txtError += "\t-El usuario ya existe elija otro.\n";
            errores =true;
         }
         if(errores){
              JOptionPane.showMessageDialog(frame, txtError, "Validación de datos del login", JOptionPane.WARNING_MESSAGE);
               return false;
         }
         return true;
    }
    
    public static boolean existsUsuario(Connection cn,String txtUser){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT usuario from login where usuario = ?");
            pps.setString(1, txtUser);
            ResultSet rs = pps.executeQuery();
            if(rs.next())
                return true;
                
            
        } catch (SQLException ex) {
            Logger.getLogger(ELogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
    
    
    
    
}
