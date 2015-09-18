/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ByteDrive
 */
public class JFLoginPassword extends javax.swing.JFrame {

    /**
     * Creates new form JFLoginPassword
     */
    public JFLoginPassword() {
        initComponents();
        settingsFrame();
    }
    private void settingsFrame(){
        //Configuraciones frame 
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(338, 520);
        this.getContentPane().setBackground(new Color(255,102,102));
       
       
        //Set imageIcon logo CARSHOP
     
       ImageIcon imageLogo = new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png"));
       label_image.setIcon(new ImageIcon(imageLogo.getImage().getScaledInstance(label_image.getWidth(),label_image.getHeight(),Image.SCALE_SMOOTH)));
        //Settings button
        btn_validarPassword.setOpaque(false);
        btn_validarPassword.setBackground(new Color(137,238,90));
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
        btn_validarPassword = new javax.swing.JButton();
        jLabel_ImageUser = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        panel_backLogo1 = new javax.swing.JPanel();
        label_image1 = new javax.swing.JLabel();
        label_tipoUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Validar password");

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
                .addContainerGap(28, Short.MAX_VALUE))
        );

        label_Bienvenido.setFont(new java.awt.Font("Walkway UltraExpand", 0, 24)); // NOI18N
        label_Bienvenido.setForeground(new java.awt.Color(255, 255, 255));
        label_Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Bienvenido.setText("Bienvenido");

        label_Bienvenido1.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        label_Bienvenido1.setForeground(new java.awt.Color(255, 255, 255));
        label_Bienvenido1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Bienvenido1.setText("Contraseña:");

        btn_validarPassword.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_validarPassword.setForeground(new java.awt.Color(255, 255, 255));
        btn_validarPassword.setText("Validar");

        jLabel_ImageUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_ImageUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/user168-1.png"))); // NOI18N

        jPasswordField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        panel_backLogo1.setBackground(new java.awt.Color(255, 204, 204));

        label_tipoUser.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        label_tipoUser.setForeground(new java.awt.Color(255, 255, 255));
        label_tipoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_tipoUser.setText("Tipo usuario:");

        javax.swing.GroupLayout panel_backLogo1Layout = new javax.swing.GroupLayout(panel_backLogo1);
        panel_backLogo1.setLayout(panel_backLogo1Layout);
        panel_backLogo1Layout.setHorizontalGroup(
            panel_backLogo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backLogo1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(label_image1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(38, 38, 38))
            .addGroup(panel_backLogo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_tipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_backLogo1Layout.setVerticalGroup(
            panel_backLogo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backLogo1Layout.createSequentialGroup()
                .addComponent(label_tipoUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_image1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Bienvenido1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_validarPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel_ImageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(panel_backLogo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_backLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel_ImageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_Bienvenido1)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_validarPassword)
                .addGap(37, 37, 37)
                .addComponent(panel_backLogo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            java.util.logging.Logger.getLogger(JFLoginPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLoginPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLoginPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLoginPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFLoginPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_validarPassword;
    public javax.swing.JLabel jLabel_ImageUser;
    private javax.swing.JPasswordField jPasswordField1;
    public javax.swing.JLabel label_Bienvenido;
    private javax.swing.JLabel label_Bienvenido1;
    private javax.swing.JLabel label_image;
    private javax.swing.JLabel label_image1;
    public javax.swing.JLabel label_tipoUser;
    private javax.swing.JPanel panel_backLogo;
    private javax.swing.JPanel panel_backLogo1;
    // End of variables declaration//GEN-END:variables
}
