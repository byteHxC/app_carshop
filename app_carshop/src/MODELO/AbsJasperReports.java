/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Byter
 */
public abstract class AbsJasperReports {
    
    private static  JasperReport report;
    private static JasperPrint reportFilled;
    private static JasperViewer viewer;
    public static void createReportCompra(Connection cn,String path,int pNum){
        try {
            Map parametro = new HashMap();
            parametro.put("pFactura", pNum);
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            reportFilled = JasperFillManager.fillReport(report,parametro, cn);
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbsJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      public static void createReportVenta(Connection cn,String path,int pNum,String pagoMens){
        try {
            Map parametro = new HashMap();
            parametro.put("pFactura", pNum);
            parametro.put("pago_mensual", pagoMens);
            report = (JasperReport) JRLoader.loadObjectFromFile(path);
            reportFilled = JasperFillManager.fillReport(report,parametro, cn);
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(AbsJasperReports.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void showViewer(){
        viewer = new JasperViewer(reportFilled,false);
      
        viewer.setVisible(true);
        
    }
}
