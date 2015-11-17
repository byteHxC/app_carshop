/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author ByteDrive
 */
public class JFGerenteHome extends javax.swing.JFrame {

    /**
     * Creates new form JFGerenteHome
     */
    public JFGerenteHome() {
        initComponents();
        settingsFrame();
    }
    
    private void settingsFrame(){
         
        //Configuraciones jframe
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(801,588);
        this.getContentPane().setBackground(Color.black);
        
        //Set imageIcon logo
        ImageIcon logo = new ImageIcon(getClass().getResource("/ASSETS/LogoCARSHOP.png"));
        label_image.setIcon(new ImageIcon(logo.getImage().getScaledInstance(label_image.getWidth(),label_image.getHeight(),Image.SCALE_SMOOTH)));
        
        //Set image in background panel
        JLabel backImage = new JLabel("");
        ImageIcon image = new ImageIcon(getClass().getResource("/ASSETS/carrr.jpg"));
        panel_image.add(backImage);
        backImage.setIcon(new ImageIcon(image.getImage().getScaledInstance(panel_image.getWidth()+30, panel_image.getHeight()+20,Image.SCALE_SMOOTH)));
        backImage.setSize(panel_image.getWidth()+30,panel_image.getHeight()+20);
        backImage.setLocation(0,0);
        
        //Opacity: set alpha at panel from tabbedpane
        Color color = new Color(255,255,255,200);
        panel_inicio.setBackground(color);
    
        
        //Opacity: set alpha for panels 
        Color colorBlack = new Color(0,0,0,190);
        panel_clientes.setBackground(colorBlack);
        panel_compras.setBackground(colorBlack);
        panel_empleados.setBackground(colorBlack);
        panel_ventas.setBackground(colorBlack);
        
        //Set icons in labels btn
        Image clientesIcon = new ImageIcon(getClass().getResource("/ASSETS/clientes.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
        Image empleadosIcon = new ImageIcon(getClass().getResource("/ASSETS/empleados.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
        Image ventasIcon = new ImageIcon(getClass().getResource("/ASSETS/entas.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
        Image comprasIcon = new ImageIcon(getClass().getResource("/ASSETS/compras.png")).getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
        
        btn_clientes.setIcon(new ImageIcon(clientesIcon));
        btn_empleados.setIcon(new ImageIcon(empleadosIcon));
        btn_ventas.setIcon(new ImageIcon(ventasIcon));
        btn_compras.setIcon(new ImageIcon(comprasIcon));
        
        JLabel lclientes = new JLabel("Clientes",SwingConstants.CENTER);
        lclientes.setForeground(Color.white);
        lclientes.setFont(new Font("Gulim",Font.PLAIN, 16));
        panel_clientes.add(lclientes);
        lclientes.setSize(panel_clientes.getWidth(), 50);
        lclientes.setLocation(0,120);
        
        JLabel lempleados = new JLabel("Empleados",SwingConstants.CENTER);
        lempleados.setForeground(Color.white);
        lempleados.setFont(new Font("Gulim",Font.PLAIN, 16));
        panel_empleados.add(lempleados);
        lempleados.setSize(panel_clientes.getWidth(), 50);
        lempleados.setLocation(0,120);
        
        JLabel lventas = new JLabel("Ventas",SwingConstants.CENTER);
        lventas.setForeground(Color.white);
        lventas.setFont(new Font("Gulim",Font.PLAIN, 16));
        panel_ventas.add(lventas);
        lventas.setSize(panel_clientes.getWidth(), 50);
        lventas.setLocation(0,120);
        
        JLabel lcompras = new JLabel("Compras",SwingConstants.CENTER);
        lcompras.setForeground(Color.white);
        lcompras.setFont(new Font("Gulim",Font.PLAIN, 16));
        panel_compras.add(lcompras);
        lcompras.setSize(panel_clientes.getWidth(), 50);
        lcompras.setLocation(0,120);
        
        //configuracion jdialog
        dialog_opcEmpleado.setResizable(false);
        dialog_opcEmpleado.setLocationRelativeTo(btn_empleados);
        dialog_opcEmpleado.setSize(404, 245);
        dialog_opcEmpleado.dispose();
        
        panel_addEmpleado.setBackground(colorBlack);
        panel_listEmpleados.setBackground(colorBlack);
        
        Image addEmpleadosIcon = new ImageIcon(getClass().getResource("/ASSETS/add110.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        Image verEmpleadosIcon = new ImageIcon(getClass().getResource("/ASSETS/list.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        
        btn_addEmpleado.setIcon(new ImageIcon(addEmpleadosIcon));
        btn_listEmpleados.setIcon(new ImageIcon(verEmpleadosIcon));
       
        
          //Set image in background panel
            JLabel backImage2 = new JLabel("");
            ImageIcon image2 = new ImageIcon(getClass().getResource("/ASSETS/carrr.jpg"));
            panel_backDialog.setSize(517,360);
            panel_backDialog.add(backImage2);
            backImage2.setIcon(new ImageIcon(image.getImage().getScaledInstance( panel_backDialog.getWidth()+21, panel_backDialog.getHeight(),Image.SCALE_SMOOTH)));
            backImage2.setSize( panel_backDialog.getWidth()+21, panel_backDialog.getHeight());
            backImage2.setLocation(0,0);
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

        dialog_opcEmpleado = new javax.swing.JDialog();
        panel_backDialog = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panel_addEmpleado = new javax.swing.JPanel();
        btn_addEmpleado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_listEmpleados = new javax.swing.JPanel();
        btn_listEmpleados = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        label_usuario = new javax.swing.JLabel();
        label_image = new javax.swing.JLabel();
        btn_logout = new javax.swing.JLabel();
        btn_settings = new javax.swing.JLabel();
        panel_image = new javax.swing.JPanel();
        ConteinterTabed = new javax.swing.JTabbedPane();
        panel_inicio = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        panel_clientes = new javax.swing.JPanel();
        btn_clientes = new javax.swing.JLabel();
        panel_empleados = new javax.swing.JPanel();
        btn_empleados = new javax.swing.JLabel();
        panel_compras = new javax.swing.JPanel();
        btn_compras = new javax.swing.JLabel();
        panel_ventas = new javax.swing.JPanel();
        btn_ventas = new javax.swing.JLabel();
        label_ImageEmpleado = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        dialog_opcEmpleado.setTitle("Opciones empleado");
        dialog_opcEmpleado.setModal(true);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Opciones");
        jLabel1.setToolTipText("");

        btn_addEmpleado.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_addEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        btn_addEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_addEmpleado.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_addEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agregar empleado");

        javax.swing.GroupLayout panel_addEmpleadoLayout = new javax.swing.GroupLayout(panel_addEmpleado);
        panel_addEmpleado.setLayout(panel_addEmpleadoLayout);
        panel_addEmpleadoLayout.setHorizontalGroup(
            panel_addEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addEmpleadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_addEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(panel_addEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_addEmpleadoLayout.setVerticalGroup(
            panel_addEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_addEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btn_listEmpleados.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_listEmpleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_listEmpleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_listEmpleados.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_listEmpleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Modificar empleados");

        javax.swing.GroupLayout panel_listEmpleadosLayout = new javax.swing.GroupLayout(panel_listEmpleados);
        panel_listEmpleados.setLayout(panel_listEmpleadosLayout);
        panel_listEmpleadosLayout.setHorizontalGroup(
            panel_listEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_listEmpleadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_listEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(panel_listEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_listEmpleadosLayout.setVerticalGroup(
            panel_listEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_listEmpleadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_listEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cancelar.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelar.setFont(new java.awt.Font("Gulim", 0, 17)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar.setOpaque(true);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_backDialogLayout = new javax.swing.GroupLayout(panel_backDialog);
        panel_backDialog.setLayout(panel_backDialogLayout);
        panel_backDialogLayout.setHorizontalGroup(
            panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_backDialogLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(panel_listEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panel_addEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panel_backDialogLayout.setVerticalGroup(
            panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_listEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_addEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dialog_opcEmpleadoLayout = new javax.swing.GroupLayout(dialog_opcEmpleado.getContentPane());
        dialog_opcEmpleado.getContentPane().setLayout(dialog_opcEmpleadoLayout);
        dialog_opcEmpleadoLayout.setHorizontalGroup(
            dialog_opcEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialog_opcEmpleadoLayout.setVerticalGroup(
            dialog_opcEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CARSHOP: GERENTE");

        label_usuario.setFont(new java.awt.Font("Neou", 0, 13)); // NOI18N
        label_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_usuario.setText("Usuario: pedro@carshop");

        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/power96(1).png"))); // NOI18N
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/gears10(1).png"))); // NOI18N
        btn_settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        ConteinterTabed.setBackground(new java.awt.Color(255, 102, 102));
        ConteinterTabed.setForeground(new java.awt.Color(255, 255, 255));

        panel_inicio.setBackground(java.awt.Color.lightGray);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Neou", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Inicio");

        btn_clientes.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_clientes.setForeground(new java.awt.Color(255, 255, 255));
        btn_clientes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_clientes.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_clientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_clientes.setIconTextGap(5);

        javax.swing.GroupLayout panel_clientesLayout = new javax.swing.GroupLayout(panel_clientes);
        panel_clientes.setLayout(panel_clientesLayout);
        panel_clientesLayout.setHorizontalGroup(
            panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panel_clientesLayout.setVerticalGroup(
            panel_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_clientes, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        btn_empleados.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_empleados.setForeground(new java.awt.Color(255, 255, 255));
        btn_empleados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_empleados.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_empleados.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panel_empleadosLayout = new javax.swing.GroupLayout(panel_empleados);
        panel_empleados.setLayout(panel_empleadosLayout);
        panel_empleadosLayout.setHorizontalGroup(
            panel_empleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_empleadosLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panel_empleadosLayout.setVerticalGroup(
            panel_empleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_empleadosLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btn_compras.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_compras.setForeground(new java.awt.Color(255, 255, 255));
        btn_compras.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_compras.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_compras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panel_comprasLayout = new javax.swing.GroupLayout(panel_compras);
        panel_compras.setLayout(panel_comprasLayout);
        panel_comprasLayout.setHorizontalGroup(
            panel_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_comprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        panel_comprasLayout.setVerticalGroup(
            panel_comprasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_comprasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_compras, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btn_ventas.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_ventas.setForeground(new java.awt.Color(255, 255, 255));
        btn_ventas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_ventas.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_ventas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panel_ventasLayout = new javax.swing.GroupLayout(panel_ventas);
        panel_ventas.setLayout(panel_ventasLayout);
        panel_ventasLayout.setHorizontalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_ventasLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panel_ventasLayout.setVerticalGroup(
            panel_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_ventasLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout panel_inicioLayout = new javax.swing.GroupLayout(panel_inicio);
        panel_inicio.setLayout(panel_inicioLayout);
        panel_inicioLayout.setHorizontalGroup(
            panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_inicioLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_compras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(219, 219, 219))
        );
        panel_inicioLayout.setVerticalGroup(
            panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_clientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_empleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_compras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_ventas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        ConteinterTabed.addTab("Inicio", panel_inicio);

        javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
        panel_image.setLayout(panel_imageLayout);
        panel_imageLayout.setHorizontalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ConteinterTabed)
                .addContainerGap())
        );
        panel_imageLayout.setVerticalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addComponent(ConteinterTabed)
                .addContainerGap())
        );

        label_ImageEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(panel_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
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
            java.util.logging.Logger.getLogger(JFGerenteHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFGerenteHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFGerenteHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFGerenteHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ConteinterTabed;
    public javax.swing.JLabel btn_addEmpleado;
    public javax.swing.JLabel btn_cancelar;
    public javax.swing.JLabel btn_clientes;
    public javax.swing.JLabel btn_compras;
    public javax.swing.JLabel btn_empleados;
    public javax.swing.JLabel btn_listEmpleados;
    public javax.swing.JLabel btn_logout;
    public javax.swing.JLabel btn_settings;
    public javax.swing.JLabel btn_ventas;
    public javax.swing.JDialog dialog_opcEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JLabel label_ImageEmpleado;
    private javax.swing.JLabel label_image;
    public javax.swing.JLabel label_usuario;
    private javax.swing.JPanel panel_addEmpleado;
    private javax.swing.JPanel panel_backDialog;
    private javax.swing.JPanel panel_clientes;
    private javax.swing.JPanel panel_compras;
    private javax.swing.JPanel panel_empleados;
    private javax.swing.JPanel panel_image;
    private javax.swing.JPanel panel_inicio;
    private javax.swing.JPanel panel_listEmpleados;
    private javax.swing.JPanel panel_ventas;
    // End of variables declaration//GEN-END:variables
}
