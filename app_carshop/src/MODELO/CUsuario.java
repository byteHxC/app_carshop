/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import VISTA.JFGerenteHome;
import VISTA.JFInfoEmpelado;
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
 * @author ByteDrive
 */
public class CUsuario {
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String direccion;
    private String telefono;
    private String tipo;
    private Boolean estado;
    private String clave_elector;
     private float salario;

    public CUsuario() {
    }
    public Boolean getEstado() {
        return estado;
    }

    public String getClave_elector() {
        return clave_elector;
    }

    public void setClave_elector(String clave_elector) {
        this.clave_elector = clave_elector;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
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
    public static boolean ifExistsTipo(Connection cn,String tipo){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT *from usuarios where tipo=?");
            pps.setString(1, tipo);
            ResultSet rs = pps.executeQuery();
           
            while(rs.next()){
                if(rs.getBoolean("estado"))
                    return true;
            }
               
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public  static CUsuario getObject(String clave_elector,Connection cn){
        CUsuario user = new CUsuario();
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT *from usuarios where cve_elector=?");
            pps.setString(1, clave_elector);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                user = new CUsuario();
                user.setNombre(rs.getString("nombre"));
                user.setApellido_pat(rs.getString("apellido_pat"));
                user.setApellido_mat(rs.getString("apellido_mat"));
                user.setDireccion(rs.getString("direccion"));
                user.setTelefono(rs.getString("telefono"));
                user.setTipo(rs.getString("tipo"));
                user.setSalario(rs.getFloat("salario"));
                user.setEstado(rs.getBoolean("estado"));
                user.setClave_elector(rs.getString("cve_elector"));
                return user;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public void saveObject(Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("INSERT INTO usuarios (nombre,apellido_pat,apellido_mat,direccion,telefono,tipo,salario,estado,cve_elector) values (?,?,?,?,?,?,?,?,?) ");
            pps.setString(1,getNombre());
            pps.setString(2,getApellido_pat());
            pps.setString(3,getApellido_mat());
            pps.setString(4,getDireccion());
            pps.setString(5, getTelefono());
            pps.setString(6,getTipo());
            pps.setFloat(7,getSalario());
            pps.setBoolean(8,getEstado());
            pps.setString(9, getClave_elector());
            pps.executeUpdate();
            System.out.println("Usuario.SaveObject() sucessful");
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String tipoUser(String cve_elector,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT tipo FROM usuarios WHERE cve_elector =?");
            pps.setString(1,cve_elector);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                return rs.getString("tipo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error en tipo";
    }
    
    public boolean validarDatos(JFInfoEmpelado frame,Connection cn){
        //try{
        String txtError = "Verificar datos de  usuario incorrectos\n";
        Boolean errores = false;
        String sAux = getSalario() + "";
        if(!(clave_elector.matches("[a-zA-Z]{6}[0-9]{6}[0-9]{2}[A-Za-z]{1}[0-9]{3}"))){
            txtError += "\t-Clave elector invalida [18 caracteres]\n";
            errores = true;
        }
        if(exist_cve_elector(cn,clave_elector)){
            txtError += "\t-La clave de elector ya existe, verificar\n";
            errores = true;
        }
        if(!(nombre.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Nombre invalido\n";
            errores = true;
        }
        if(!(apellido_pat.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Apellido paterno invalido\n";
            errores = true;
        }
        if(!(apellido_mat.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Apellido materno invalido\n";
            errores = true;
        }  
        if(!(direccion.length()>=200 || direccion.matches("[a-zA-Z]+([ ]*[a-zA-Z0-9]+)+"))){   
                txtError += "\t-Verificar direccion [200] caracteres\n";
                errores = true;
        }
        if(!(telefono.matches("[0-9]{5,15}"))){
            txtError += "\t-Telefono invalido\n";
            errores = true;
        }
        if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?")){
            txtError += "\t-Salario invalido\n";
            errores = true;
        }
        if(errores){
            JOptionPane.showMessageDialog(frame, txtError, "Validacion de datos de usuarios", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public boolean validarDatos(JFGerenteHome frame,Connection cn){
        //try{
        String txtError = "Verificar datos de  usuario incorrectos\n";
        Boolean errores = false;
        String sAux = getSalario() + "";
        if(!(clave_elector.matches("[a-zA-Z]{6}[0-9]{6}[0-9]{2}[A-Za-z]{1}[0-9]{3}"))){
            txtError += "\t-Clave elector invalida [18 caracteres]\n";
            errores = true;
        }
        if(exist_cve_elector(cn,clave_elector)){
            txtError += "\t-La clave de elector ya existe,ingresar otra\n";
            errores = true;
        }
        if(!(nombre.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Nombre invalido\n";
            errores = true;
        }
        if(!(apellido_pat.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Apellido paterno invalido\n";
            errores = true;
        }
        if(!(apellido_mat.matches("[a-zA-Z]{3,30}"))){
            txtError += "\t-Apellido materno invalido\n";
            errores = true;
        }  
        if(!(direccion.length()>=200 || direccion.matches("[a-zA-Z]+([ ]*[a-zA-Z0-9]+)+"))){   
                txtError += "\t-Verificar direccion [200] caracteres\n";
                errores = true;
        }
        if(!(telefono.matches("[0-9]{5,15}"))){
            txtError += "\t-Telefono invalido\n";
            errores = true;
        }
        if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?")){
            txtError += "\t-Salario invalido\n";
            errores = true;
        }
        if(errores){
            JOptionPane.showMessageDialog(frame, txtError, "Validacion de datos de usuarios", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public static void bajaEstado(Connection cn,String tipo){
        try{
            PreparedStatement pps = cn.prepareStatement("update usuarios set estado=? where tipo=?");
            pps.setBoolean(1,false);
            pps.setString(2,tipo);
            pps.executeUpdate();
            System.out.println("Baja usuario");
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static boolean bajaEstado(String cve_elector,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("select *from usuarios where cve_elector=?");
            pps.setString(1, cve_elector);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                boolean resp = rs.getBoolean("estado");
                if(resp == true){
                    PreparedStatement pps2 = cn.prepareStatement("update usuarios set estado=? where cve_elector=?");
                    pps2.setBoolean(1,false);
                    pps2.setString(2,cve_elector);
                    pps2.executeUpdate();
                    System.out.println("Baja usuario");
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
      public static boolean altaEstado(String cve_elector,Connection cn){
        try{
            PreparedStatement pps = cn.prepareStatement("select * from usuarios where cve_elector=?");
            pps.setString(1, cve_elector);

            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                boolean resp = rs.getBoolean("estado");
                if(resp == false){
                    PreparedStatement pps2 = cn.prepareStatement("update usuarios set estado=? where cve_elector=?");
                    pps2.setBoolean(1,true);
                    pps2.setString(2,cve_elector);
                    pps2.executeUpdate();
                    System.out.println("alta usuario");
                    return true;
                }else{
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
 
    //Validar que no exista al pk cve_elector

    public static boolean exist_cve_elector(Connection cn,String cve_elector){
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT cve_elector from usuarios where cve_elector=?");
            pps.setString(1, cve_elector);
            ResultSet rs = pps.executeQuery();
            while(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ArrayList<CUsuario> queryAll(Connection cn){
       ArrayList<CUsuario> usuarios = new ArrayList<>();
       try{
           PreparedStatement pps = cn.prepareStatement("SELECT *FROM USUARIOS");
           ResultSet rs = pps.executeQuery();
           while(rs.next()){
               CUsuario usuario = new CUsuario();
               usuario.setClave_elector(rs.getString("cve_elector"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setApellido_pat(rs.getString("apellido_pat"));
               usuario.setApellido_mat(rs.getString("apellido_mat"));
               usuario.setDireccion(rs.getString("direccion"));
               usuario.setTelefono(rs.getString("telefono"));
               usuario.setTipo(rs.getString("tipo"));
               usuario.setSalario(rs.getFloat("salario"));
               usuario.setEstado(rs.getBoolean("estado"));
               if(!rs.getString("tipo").equals("Gerente"))
                   usuarios.add(usuario);
           }
       }catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }  
       return usuarios;
    }
    
     public static ArrayList<CUsuario> queryForClaveElector(Connection cn,String cveElector){
       ArrayList<CUsuario> usuarios = new ArrayList<>();
       try{
           PreparedStatement pps = cn.prepareStatement("SELECT *FROM USUARIOS where cve_elector = ?");
           pps.setString(1, cveElector);
           ResultSet rs = pps.executeQuery();
           while(rs.next()){
               CUsuario usuario = new CUsuario();
               usuario.setClave_elector(rs.getString("cve_elector"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setApellido_pat(rs.getString("apellido_pat"));
               usuario.setApellido_mat(rs.getString("apellido_mat"));
               usuario.setDireccion(rs.getString("direccion"));
               usuario.setTelefono(rs.getString("telefono"));
               usuario.setTipo(rs.getString("tipo"));
               usuario.setSalario(rs.getFloat("salario"));
               usuario.setEstado(rs.getBoolean("estado"));
               if(!rs.getString("tipo").equals("Gerente"))
                   usuarios.add(usuario);
           }
       }catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }  
       return usuarios;
    }
     
     public static ArrayList<CUsuario> queryForNombre(Connection cn,String nombre){
       ArrayList<CUsuario> usuarios = new ArrayList<>();
       try{
           PreparedStatement pps = cn.prepareStatement("SELECT *FROM USUARIOS where nombre = ?");
           pps.setString(1, nombre);
           ResultSet rs = pps.executeQuery();
           while(rs.next()){
               CUsuario usuario = new CUsuario();
               usuario.setClave_elector(rs.getString("cve_elector"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setApellido_pat(rs.getString("apellido_pat"));
               usuario.setApellido_mat(rs.getString("apellido_mat"));
               usuario.setDireccion(rs.getString("direccion"));
               usuario.setTelefono(rs.getString("telefono"));
               usuario.setTipo(rs.getString("tipo"));
               usuario.setSalario(rs.getFloat("salario"));
               usuario.setEstado(rs.getBoolean("estado"));
               if(!rs.getString("tipo").equals("Gerente"))
                   usuarios.add(usuario);
           }
       }catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }  
       return usuarios;
    }
     
     public static ArrayList<CUsuario> queryForType(Connection cn,String type){
       ArrayList<CUsuario> usuarios = new ArrayList<>();
       try{
           PreparedStatement pps = cn.prepareStatement("SELECT *FROM USUARIOS where tipo = ?");
           pps.setString(1, type);
           ResultSet rs = pps.executeQuery();
           while(rs.next()){
               CUsuario usuario = new CUsuario();
               usuario.setClave_elector(rs.getString("cve_elector"));
               usuario.setNombre(rs.getString("nombre"));
               usuario.setApellido_pat(rs.getString("apellido_pat"));
               usuario.setApellido_mat(rs.getString("apellido_mat"));
               usuario.setDireccion(rs.getString("direccion"));
               usuario.setTelefono(rs.getString("telefono"));
               usuario.setTipo(rs.getString("tipo"));
               usuario.setSalario(rs.getFloat("salario"));
               usuario.setEstado(rs.getBoolean("estado"));
               if(!rs.getString("tipo").equals("Gerente"))
                   usuarios.add(usuario);
           }
       }catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
       }  
       return usuarios;
    }
     
    public static boolean updateObject(String cve_elector,String direccion,String telefono,float salario,Connection cn){
        try{
        String sAux = salario + "";
        if(!(direccion.length()>=200 || direccion.matches("[a-zA-Z]+([ ]*[a-zA-Z0-9]+)+"))) return false;
        if(!(telefono.matches("[0-9]{5,15}"))) return false;
        if(!sAux.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?")) return false;
        
        PreparedStatement pps = cn.prepareStatement("update usuarios set direccion = ?, telefono = ?, salario = ? where cve_elector = ?");
            pps.setString(1,direccion);
            pps.setString(2,telefono);
            pps.setFloat(3,salario);
            pps.setString(4,cve_elector);
        pps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
            return true;
    }

    
    
 
}
