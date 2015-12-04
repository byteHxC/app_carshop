/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
        this.setSize(338, 500);
        
        //Set imageIcon logo CARSHOP
        Color colorpa = new Color(0,0,0,130);
        panel_backLogo.setBackground(colorpa);
        jPanelBackI.setBackground(colorpa);
        this.getContentPane().setBackground(colorpa);
        //Set imageIcon logo CARSHOP
        label_tipoUser.setOpaque(true);
        label_tipoUser.setBackground(colorpa);
        ImageIcon imageLogo = new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png"));
        label_image.setIcon(new ImageIcon(imageLogo.getImage().getScaledInstance(label_image.getWidth(),label_image.getHeight(),Image.SCALE_SMOOTH)));
        JLabel backImage = new JLabel("");
        ImageIcon image = new ImageIcon(getClass().getResource("/ASSETS/auto.jpg"));
            
        this.getContentPane().add(backImage);
        backImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(800,500,Image.SCALE_SMOOTH)));          
        backImage.setSize(this.getWidth()+30,this.getHeight()+20);
        backImage.setLocation(0,0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBackI = new javax.swing.JPanel();
        btn_validarPass = new javax.swing.JLabel();
        label_ImageUser = new javax.swing.JLabel();
        txtP_Password = new javax.swing.JPasswordField();
        label_Bienvenido = new javax.swing.JLabel();
        label_contrasena = new javax.swing.JLabel();
        label_tipoUser = new javax.swing.JLabel();
        panel_backLogo = new javax.swing.JPanel();
        label_image = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Validar password");

        btn_validarPass.setBackground(new java.awt.Color(84, 222, 66));
        btn_validarPass.setFont(new java.awt.Font("Gulim", 0, 17)); // NOI18N
        btn_validarPass.setForeground(new java.awt.Color(255, 255, 255));
        btn_validarPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_validarPass.setText("Validar");
        btn_validarPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_validarPass.setOpaque(true);

        label_ImageUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ImageUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/user168-1.png"))); // NOI18N
        label_ImageUser.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtP_Password.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        label_Bienvenido.setFont(new java.awt.Font("Avenir Next", 0, 18)); // NOI18N
        label_Bienvenido.setForeground(new java.awt.Color(255, 255, 255));
        label_Bienvenido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_Bienvenido.setText("Usuario:");

        label_contrasena.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        label_contrasena.setForeground(new java.awt.Color(255, 255, 255));
        label_contrasena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_contrasena.setText("Contraseña:");

        label_tipoUser.setFont(new java.awt.Font("Gulim", 0, 18)); // NOI18N
        label_tipoUser.setForeground(new java.awt.Color(255, 255, 255));
        label_tipoUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_tipoUser.setText("Tipo usuario:");

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
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBackILayout = new javax.swing.GroupLayout(jPanelBackI);
        jPanelBackI.setLayout(jPanelBackILayout);
        jPanelBackILayout.setHorizontalGroup(
            jPanelBackILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_tipoUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBackILayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(btn_validarPass, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackILayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanelBackILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackILayout.createSequentialGroup()
                        .addComponent(label_ImageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackILayout.createSequentialGroup()
                        .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanelBackILayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBackILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_contrasena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelBackILayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(txtP_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(panel_backLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelBackILayout.setVerticalGroup(
            jPanelBackILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackILayout.createSequentialGroup()
                .addComponent(panel_backLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(label_Bienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_ImageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_contrasena)
                .addGap(18, 18, 18)
                .addComponent(txtP_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_validarPass, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(label_tipoUser)
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackI, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    public javax.swing.JLabel btn_validarPass;
    private javax.swing.JPanel jPanelBackI;
    public javax.swing.JLabel label_Bienvenido;
    public javax.swing.JLabel label_ImageUser;
    public javax.swing.JLabel label_contrasena;
    private javax.swing.JLabel label_image;
    public javax.swing.JLabel label_tipoUser;
    private javax.swing.JPanel panel_backLogo;
    public javax.swing.JPasswordField txtP_Password;
    // End of variables declaration//GEN-END:variables
}
