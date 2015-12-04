package app_carshop;
import CONTROL.Controller_JFLoginUser;
import MODELO.AbsJasperReports;
import MODELO.ConnectionMySQL;
import MODELO.UserROOT;
import VISTA.JFLoginUser;
import java.io.File;
import java.sql.Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ByteDrive
 */
public class App_carshop{
    //Instancia de la clase principal LOGIN
    static Controller_JFLoginUser controladorLogin;
    static Connection cn;
    public static void main(String[] args) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
         
      // UserROOT.escribirDefaultROOTDB("123456","localhost","3306","root","#%mysql/1");
       getConnection(UserROOT.getROOTDB());
       controladorLogin = new Controller_JFLoginUser(cn);
       //Prueba reporte
       /*
       String pathCompra = App_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
       File aux =new File(pathCompra);
        if (aux.isDirectory())
            pathCompra = pathCompra + "/Reportes/ReporteComprajrxml.jasper";
        else
            pathCompra = aux.getParent() + "/Reportes/ReporteComprajrxml.jasper";
            
       AbsJasperReports.createReportCompra(cn,pathCompra, 9);
       AbsJasperReports.showViewer();
       */
       
          
       
        String pathVenta = App_carshop.class.getProtectionDomain().getCodeSource().getLocation().getPath();
       File aux =new File(pathVenta);
        if (aux.isDirectory())
            pathVenta = pathVenta + "/Reportes/ReporteVenta.jasper";
        else
            pathVenta = aux.getParent() + "/Reportes/ReporteVenta.jasper";
       
        AbsJasperReports.createReportVenta(cn,pathVenta,5,"300 $");
        AbsJasperReports.showViewer();
        
      
   
    }
    public static void  init(){
       getConnection(UserROOT.getROOTDB());
       controladorLogin = new Controller_JFLoginUser(cn);
    }
    
      //Metodo para leer la configuracion de la base de datos y acceder a ella
    
    private static void getConnection(UserROOT userObj){
        cn = ConnectionMySQL.getConnection(userObj.getIp(),userObj.getPort(),userObj.getUserDB(),userObj.getPassDB());
     
    }
}
