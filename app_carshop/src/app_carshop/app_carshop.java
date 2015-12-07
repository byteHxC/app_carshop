package app_carshop;
import CONTROL.Controller_JFLoginUser;
import MODELO.ConnectionMySQL;
import MODELO.DateTime;
import MODELO.UserROOT;
import VISTA.JFLoginUser;
import java.io.File;
import java.sql.Connection;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.MonthDay;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ByteDrive
 */
public class app_carshop{
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
         
         
      //UserROOT.escribirDefaultROOTDB("123456","localhost","3306","root","#%mysql/1");
      //DateTime.getDiaNow()
        System.out.println("hora "+DateTime.getHour());
      if(DateTime.getDiaNow().equals("Domingo")){
          JOptionPane.showMessageDialog(null,"Los dias domingo no se puede acceder al sistema","Mensaje de error",JOptionPane.ERROR_MESSAGE);
          System.exit(0);
      }else{
         int hourDay = DateTime.getHour();
         if(hourDay>=9 && hourDay<20 ){  
            getConnection(UserROOT.getROOTDB());
            controladorLogin = new Controller_JFLoginUser(cn);
         }else{
          JOptionPane.showMessageDialog(null,"El horario de uso es de 08:00 - 20:00 horas","Mensaje de error",JOptionPane.ERROR_MESSAGE);
          System.exit(0);
         }
      }
    
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
