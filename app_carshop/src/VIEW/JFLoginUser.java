/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * @author ByteDrive
 */
public class JFLoginUser extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public JFLoginUser() {
        initComponents();
        settingsFrame();
    }

    
    private void settingsFrame(){
            //Configuraciones frame
            this.setResizable(false);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
            this.setSize(338, 480);
            this.getContentPane().setBackground(new Color(255,102,102));
            //Set imageIcon logo CARSHOP
            
            ImageIcon imageLogo = new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png"));
            label_image.setIcon(new ImageIcon(imageLogo.getImage().getScaledInstance(label_image.getWidth(),label_image.getHeight(),Image.SCALE_SMOOTH)));
            //Settings button
            btn_validarUser.setOpaque(false);
            btn_validarUser.setBackground(new Color(137,238,90));
            
            
            //Cambiar icono
            Image icon = Toolkit.getDefaultToolkit().getImage("/ASSETS/LogoCARSHOP.png");
            this.setIconImage(icon);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_backLogo = new javax.swing.JPanel();
        label_image = new javax.swing.JLabel();
        label_Bienvenido = new javax.swing.JLabel();
        label_Bienvenido1 = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        btn_validarUser = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Validar usuario");
        setIconImage(new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png")).getImage());

        panel_backLogo.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout panel_backLogoLayout = new javax.swing.GroupLayout(panel_backLogo);
        panel_backLogo.setLayout(panel_backLogoLayout);
        panel_backLogoLayout.setHorizontalGroup(
            panel_backLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backLogoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(label_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
        );
        panel_backLogoLayout.setVerticalGroup(
            panel_backLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backLogoLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        label_Bienvenido.setFont(new java.awt.Font("Walkway UltraExpand", 0, 24)); // NOI18N
        label_Bienvenido.setForeground(new java.awt.Color(255, 255, 255));
        label_Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Bienvenido.setText("Bienvenido");

        label_Bienvenido1.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        label_Bienvenido1.setForeground(new java.awt.Color(255, 255, 255));
        label_Bienvenido1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Bienvenido1.setText("Usuario:");

        txt_user.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setToolTipText("Ingrese su usuario");

        btn_validarUser.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_validarUser.setForeground(new java.awt.Color(255, 255, 255));
        btn_validarUser.setText("Validar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(label_Bienvenido1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(btn_validarUser, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(panel_backLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(label_Bienvenido1)
                .addGap(18, 18, 18)
                .addComponent(txt_user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_validarUser))
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
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLoginUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFLoginUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_validarUser;
    private javax.swing.JLabel label_Bienvenido;
    private javax.swing.JLabel label_Bienvenido1;
    private javax.swing.JLabel label_image;
    private javax.swing.JPanel panel_backLogo;
    public javax.swing.JTextField txt_user;
    // End of variables declaration//GEN-END:variables
}
