/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import VISTA.JFAgregarAuto;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ByteDrive
 */
public class CAuto {
    private String numero_serie;
    private String marca;
    private String tipo;
    private String modelo;
    private int numero_pasajeros;
    private int cilindros;
    private String color;
    private String detalle;
    private int certificado_mecanico;
    private float precio_venta;
    private float precio_compra;
    private String estado;
    private String AbsolutePathimagen;
    private Blob imageBlob;
    private String nombreImagen;

    public CAuto() {
    }

    public String getAbsolutePathimagen() {
        return AbsolutePathimagen;
    }

    public void setAbsolutePathimagen(String AbsolutePathimagen) {
        this.AbsolutePathimagen = AbsolutePathimagen;
    }

    public Blob getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Blob imageBlob) {
        this.imageBlob = imageBlob;
    }

    public String getNombreImagen() {
        return nombreImagen;
    }

    public void setNombreImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
    }
    
    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getNumero_pasajeros() {
        return numero_pasajeros;
    }

    public void setNumero_pasajeros(int numero_pasajeros) {
        this.numero_pasajeros = numero_pasajeros;
    }

    public int getCilindros() {
        return cilindros;
    }

    public void setCilindros(int cilindros) {
        this.cilindros = cilindros;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getCertificado_mecanico() {
        return certificado_mecanico;
    }

    public void setCertificado_mecanico(int certificado_mecanico) {
        this.certificado_mecanico = certificado_mecanico;
    }

    public float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(float precio_venta) {
        this.precio_venta = precio_venta;
    }

    public float getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(float precio_compra) {
        this.precio_compra = precio_compra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public boolean validarAuto(JFAgregarAuto frame, Connection cn){
        String txtErrores = "Verificar datos del auto: \n";
        boolean errores = false;
        String numPas = getNumero_pasajeros() + "";
        String cilin = getCilindros() + "";
        String certMec = getCertificado_mecanico() + "";
        String precCompra = getPrecio_compra()+ "";
        String precioVenta = getPrecio_venta()+ "";
        // el numero de serie se compone de 17 caracteres, pueden ser numeros o letras revueltas,
        // para no escribir ejemplos con tantos le puse que sÛlo sean 5 caracteres wey  .l.
        if(existNumSerie(cn, numero_serie)){
            txtErrores += "Numero de serie ya existe, verificar\n";
        }
        
        if(!(numero_serie.matches("[a-zA-Z0-9]{5}"))){
            txtErrores += "\tNumero de serie invalido [5] caracteres\n";
            errores = true;
        }
        if(marca.length() >= 20){
            txtErrores += "\tMarca invalida [20] caracteres\n";
            errores = true;
        }
        else if(!(marca.matches("[a-zA-Z]{3,20}([ ]([a-zA-Z]{3,20})+)?"))){
                txtErrores += "\tMarca invalida\n";
                errores = true;
        }
        if(tipo.length() >= 20){
            txtErrores += "\tTipo de vehiculo invalido [20] caracteres\n";
            errores = true;
        }
        else if(!(tipo.matches("[a-zA-Z]{3,20}([ ]([a-zA-Z]{3,20})+)?"))){
                txtErrores += "\tTipo de vehiculo invalido\n";
                errores = true;
            
        }
        Calendar cal = Calendar.getInstance();
        int anioAct = cal.get(cal.YEAR);
        //System.out.println("anio actual: " + anioAct);
        if(!(modelo.matches("[2][0][0-9][0-9]"))){
            txtErrores += "\tModelo del vehiculo invalido.\n";
            errores = true;
        }
        else{
            int anio = Integer.parseInt(modelo);
            if((anioAct - anio) > 5){
                txtErrores += "\tEl vehiculo no puede ser agregado. Modelo retrazado\n";
                errores = true;
            }
        }
        if(!(numPas.matches("[2-8]{1}"))){
            txtErrores += "\tCantidad de pasajeros invalida. [2] - [8]\n";
            errores = true;
        }
        if(!(cilin.matches("[2-8]{1,1}"))){
            txtErrores += "\tCilindro invalido\n";
            errores = true;
        }
        else{
            if(!(cilindros % 2 == 0)){
                txtErrores += "\tCilindrada invalida\n";
                errores = true;
            }
        }
        if(color.length() >= 20){
            txtErrores += "\tColor del vehiculo invalido [20] caracteres\n";
            errores = true;
        }
        else{
            if(!(color.matches("[a-zA-Z]{3,20}([ ]([a-zA-Z]{3,20})+)?"))){
                txtErrores += "\tColor de vehiculo invalido\n";
                errores = true;
            }
        }
        if(detalle.length() >= 200){
            txtErrores += "\tDetalle del vehiculo erroneo [200] caracteres\n";
            errores = true;
        }
        if(!(precCompra.equals("0.0"))){
            if(!precCompra.matches("([1-9])[0-9]+(([.])([0-9]{1,2})?)?") ){
                txtErrores += "\t-Precio invalido\n";
                errores = true;
            }
        }
        if(errores){
            JOptionPane.showMessageDialog(frame, txtErrores, "Validacion de datos del autos", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    public void saveObject(Connection cn){
        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream(AbsolutePathimagen);
            PreparedStatement pps = cn.prepareStatement("INSERT INTO catalogo_autos (numero_serie,marca,tipo,modelo,numero_pasajeros,cilindros,color,detalle,certificado_mecanico,precio_venta,precio_compra,imagen,nombre_imagen,estado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pps.setString(1,numero_serie);
            pps.setString(2,marca);
            pps.setString(3, tipo);
            pps.setString(4, modelo);
            pps.setInt(5, numero_pasajeros);
            pps.setInt(6, cilindros);
            pps.setString(7, color);
            pps.setString(8, detalle);
            pps.setInt(9, certificado_mecanico);
            pps.setFloat(10, precio_venta);
            pps.setFloat(11, precio_compra);
            pps.setBlob(12, fileIn);
            System.out.println(nombreImagen);
            pps.setString(13, nombreImagen);
            pps.setString(14, estado);
            pps.executeUpdate();
            System.out.println("CAuto.saveObject() successful");
        } catch (SQLException | FileNotFoundException ex) {
            Logger.getLogger(CAuto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static CAuto getObject(Connection cn,String numero_serie){
         CAuto auto = new CAuto();
        try{
           
            PreparedStatement pps = cn.prepareStatement("SELECT *FROM catalogo_autos where numero_serie = ?");
            pps.setString(1,numero_serie);
            ResultSet rs = pps.executeQuery();
            if(rs.next()){
                auto = new CAuto();
                auto.setNumero_serie(rs.getString("numero_serie"));
                auto.setMarca(rs.getString("marca"));
                auto.setTipo(rs.getString("tipo"));
                auto.setModelo(rs.getString("modelo"));
                auto.setNumero_pasajeros(rs.getInt("numero_pasajeros"));
                auto.setCilindros(rs.getInt("cilindros"));
                auto.setColor(rs.getString("color"));
                auto.setDetalle(rs.getString("detalle"));
                auto.setCertificado_mecanico(rs.getInt("certificado_mecanico"));
                auto.setPrecio_compra(rs.getFloat("precio_compra"));
                try{
                    auto.setPrecio_venta(rs.getFloat("precio_compra"));
                }catch(Exception e){
                    auto.setPrecio_venta(0);
                }
                auto.setImageBlob(rs.getBlob("imagen"));
                auto.setNombreImagen(rs.getString("nombre_imagen"));
                auto.setEstado(rs.getString("estado"));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CAuto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return auto;
    }

    private boolean existNumSerie(Connection cn, String numero_serie) {
        try{
            PreparedStatement pps = cn.prepareStatement("SELECT * FROM catalogo_autos where numero_serie = ?");
            pps.setString(1,numero_serie);
            ResultSet rs = pps.executeQuery();
            if(rs.next())
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(CAuto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
