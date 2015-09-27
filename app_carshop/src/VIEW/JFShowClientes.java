/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import ASSETS.CellRender;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author ByteDrive
 */
public class JFShowClientes extends javax.swing.JFrame {

    /**
     * Creates new form JFShowClientes
     */
    public JFShowClientes() {
        initComponents();
        settingsFrame();
    }
    
    private void settingsFrame(){
         //Configuraciones jframe
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(801,579);
        this.getContentPane().setBackground(Color.black);
        ImageIcon logo = new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png"));
        label_image.setIcon(new ImageIcon(logo.getImage().getScaledInstance(label_image.getWidth(),label_image.getHeight(),Image.SCALE_SMOOTH)));
        
        //Set image in background panel
        JLabel backImage = new JLabel("");
        ImageIcon image = new ImageIcon(getClass().getResource("/ASSETS/carrr.jpg"));
        panel_image.add(backImage);
        backImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(panel_image.getWidth()+30, panel_image.getHeight()+20,Image.SCALE_SMOOTH)));
        backImage.setSize(panel_image.getWidth()+30,panel_image.getHeight()+20);
        backImage.setLocation(0,0);
        
        //Set opacity showClientes panel
        Color color = new Color(255,255,255,200);
        panel_showClientes.setBackground(color);
        
        table_showClientes.setOpaque(false);
        table_showClientes.setBackground(null);
        cellRenderer(table_showClientes);
        
         //Cambiar icono
        Image icon = Toolkit.getDefaultToolkit().getImage("/ASSETS/LogoCARSHOP.png");
        this.setIconImage(icon);
     
        
    }
    
    public void cellRenderer(JTable table){
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while(en.hasMoreElements()){
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRender());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_image = new javax.swing.JPanel();
        panel_showClientes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txt_valueSearch = new javax.swing.JTextField();
        cbox_searchFor = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_showClientes = new javax.swing.JTable();
        label_usuario = new javax.swing.JLabel();
        label_image = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        label_ImageEmpleado = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        btn_settings = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Mostrar clientes");

        jLabel1.setFont(new java.awt.Font("Neou", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTES");

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Gulim", 0, 16)); // NOI18N
        jLabel2.setText("Buscar por:");

        cbox_searchFor.setBackground(java.awt.Color.lightGray);
        cbox_searchFor.setFont(new java.awt.Font("Gulim", 0, 16)); // NOI18N
        cbox_searchFor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Clave elector", "Nombre", "Apellido paterno", "RFC" }));

        jLabel3.setBackground(new java.awt.Color(51, 153, 255));
        jLabel3.setFont(new java.awt.Font("Gulim", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/database22_1.png"))); // NOI18N
        jLabel3.setText("Buscar");
        jLabel3.setOpaque(true);

        table_showClientes.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        table_showClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_showClientes.setSelectionBackground(new java.awt.Color(153, 204, 255));
        table_showClientes.setShowGrid(false);
        jScrollPane2.setViewportView(table_showClientes);

        javax.swing.GroupLayout panel_showClientesLayout = new javax.swing.GroupLayout(panel_showClientes);
        panel_showClientes.setLayout(panel_showClientesLayout);
        panel_showClientesLayout.setHorizontalGroup(
            panel_showClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_showClientesLayout.createSequentialGroup()
                .addGroup(panel_showClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_showClientesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panel_showClientesLayout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_showClientesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbox_searchFor, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_valueSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
            .addGroup(panel_showClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        panel_showClientesLayout.setVerticalGroup(
            panel_showClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_showClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_showClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_showClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_showClientesLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(txt_valueSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addComponent(cbox_searchFor)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
        panel_image.setLayout(panel_imageLayout);
        panel_imageLayout.setHorizontalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_showClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_imageLayout.setVerticalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_imageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_showClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );

        label_usuario.setFont(new java.awt.Font("Neou", 0, 13)); // NOI18N
        label_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_usuario.setText("Usuario: pedro@carshop");

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        label_ImageEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/power96(1).png"))); // NOI18N
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/gears10(1).png"))); // NOI18N
        btn_settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(label_ImageEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_logout)
                    .addComponent(btn_settings))
                .addContainerGap())
            .addComponent(panel_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btn_settings, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(label_usuario))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(label_ImageEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_image, javax.swing.GroupLayout.PREFERRED_SIZE, 471, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFShowClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFShowClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFShowClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFShowClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFShowClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btn_logout;
    public javax.swing.JLabel btn_settings;
    private javax.swing.JComboBox cbox_searchFor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JLabel label_ImageEmpleado;
    private javax.swing.JLabel label_image;
    public javax.swing.JLabel label_usuario;
    private javax.swing.JPanel panel_image;
    private javax.swing.JPanel panel_showClientes;
    private javax.swing.JTable table_showClientes;
    private javax.swing.JTextField txt_valueSearch;
    // End of variables declaration//GEN-END:variables
}
