/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ByteDrive
 */
public class JFComercioHome extends javax.swing.JFrame {

    /**
     * Creates new form JFComercioHome
     */
    public JFComercioHome() {
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
        panel_addCliente.setBackground(colorBlack);
        panel_addCompra.setBackground(colorBlack);
        panel_addVenta.setBackground(colorBlack);
        panel_verAutos.setBackground(colorBlack);
        
        //Set icons in labels btn
        Image clienteIcon = new ImageIcon(getClass().getResource("/ASSETS/add110.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        Image ventaIcon = new ImageIcon(getClass().getResource("/ASSETS/commercial18(1).png")).getImage().getScaledInstance(120,100,Image.SCALE_SMOOTH);
        Image compraIcon = new ImageIcon(getClass().getResource("/ASSETS/receipt10.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        Image vAutos = new ImageIcon(getClass().getResource("/ASSETS/vAuto.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        
        btn_cliente.setIcon(new ImageIcon(clienteIcon));
        btn_addVenta.setIcon(new ImageIcon(ventaIcon));
        btn_addCompra.setIcon(new ImageIcon(compraIcon));
        btn_verAutos.setIcon(new ImageIcon(vAutos));
        
//        JLabel lcliente = new JLabel("Clientes",SwingConstants.CENTER);
//        lcliente.setForeground(Color.white);
//        lcliente.setFont(new Font("Gulim",Font.PLAIN, 16));
//        panel_addCliente.add(lcliente);
//        lcliente.setSize(panel_addCliente.getWidth(), 50);
//        lcliente.setLocation(0,120);
//        
//        JLabel lventa = new JLabel("Agregar venta",SwingConstants.CENTER);
//        lventa.setForeground(Color.white);
//        lventa.setFont(new Font("Gulim",Font.PLAIN, 16));
//        panel_addVenta.add(lventa);
//        lventa.setSize(panel_addVenta.getWidth(), 50);
//        lventa.setLocation(0,120);
//        
//        JLabel lcompra = new JLabel("Agregar compra",SwingConstants.CENTER);
//        lcompra.setForeground(Color.white);
//        lcompra.setFont(new Font("Gulim",Font.PLAIN, 16));
//        panel_addCompra.add(lcompra);
//        lcompra.setSize(panel_addCompra.getWidth(), 50);
//        lcompra.setLocation(0,120);
        
        
        
         //configuracion jdialog
        dialog_opcionesCliente.setResizable(false);
        dialog_opcionesCliente.setLocationRelativeTo(btn_cliente);
        dialog_opcionesCliente.setSize(375, 240);
        dialog_opcionesCliente.dispose();
        
        panel_agreCliente.setBackground(colorBlack);
        panel_modCliente.setBackground(colorBlack);
        
        Image addClienteIcon = new ImageIcon(getClass().getResource("/ASSETS/add110.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        Image modClienteIcon = new ImageIcon(getClass().getResource("/ASSETS/list.png")).getImage().getScaledInstance(80,80,Image.SCALE_SMOOTH);
        
        btn_addCliente.setIcon(new ImageIcon(addClienteIcon));
        btn_modCliente.setIcon(new ImageIcon(modClienteIcon));
       
        
          //Set image in background panel
            JLabel backImage2 = new JLabel("");
            ImageIcon image2 = new ImageIcon(getClass().getResource("/ASSETS/carrr.jpg"));
            panel_backDialog.setSize(404, 245);
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

        dialog_opcionesCliente = new javax.swing.JDialog();
        panel_backDialog = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panel_agreCliente = new javax.swing.JPanel();
        btn_addCliente = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panel_modCliente = new javax.swing.JPanel();
        btn_modCliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        label_image = new javax.swing.JLabel();
        label_usuario = new javax.swing.JLabel();
        label_ImageEmpleado = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btn_logout = new javax.swing.JLabel();
        btn_settings = new javax.swing.JLabel();
        panel_image = new javax.swing.JPanel();
        panel_inicio = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        panel_addCliente = new javax.swing.JPanel();
        btn_cliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel_addCompra = new javax.swing.JPanel();
        btn_addCompra = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panel_addVenta = new javax.swing.JPanel();
        btn_addVenta = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel_verAutos = new javax.swing.JPanel();
        btn_verAutos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Opciones");
        jLabel4.setToolTipText("");

        btn_addCliente.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_addCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_addCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_addCliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_addCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Agregar cliente");

        javax.swing.GroupLayout panel_agreClienteLayout = new javax.swing.GroupLayout(panel_agreCliente);
        panel_agreCliente.setLayout(panel_agreClienteLayout);
        panel_agreClienteLayout.setHorizontalGroup(
            panel_agreClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_agreClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_agreClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_addCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        panel_agreClienteLayout.setVerticalGroup(
            panel_agreClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_agreClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_addCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        btn_modCliente.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_modCliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_modCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_modCliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_modCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Modificar cliente");

        javax.swing.GroupLayout panel_modClienteLayout = new javax.swing.GroupLayout(panel_modCliente);
        panel_modCliente.setLayout(panel_modClienteLayout);
        panel_modClienteLayout.setHorizontalGroup(
            panel_modClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_modClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_modClienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_modCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        panel_modClienteLayout.setVerticalGroup(
            panel_modClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_modClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_modCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_cancelar.setBackground(new java.awt.Color(255, 102, 102));
        btn_cancelar.setFont(new java.awt.Font("Gulim", 0, 17)); // NOI18N
        btn_cancelar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_cancelar.setText("Cancelar");
        btn_cancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cancelar.setOpaque(true);

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panel_backDialogLayout = new javax.swing.GroupLayout(panel_backDialog);
        panel_backDialog.setLayout(panel_backDialogLayout);
        panel_backDialogLayout.setHorizontalGroup(
            panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(panel_modCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panel_agreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_backDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        panel_backDialogLayout.setVerticalGroup(
            panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_backDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(panel_backDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_modCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_agreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialog_opcionesClienteLayout = new javax.swing.GroupLayout(dialog_opcionesCliente.getContentPane());
        dialog_opcionesCliente.getContentPane().setLayout(dialog_opcionesClienteLayout);
        dialog_opcionesClienteLayout.setHorizontalGroup(
            dialog_opcionesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialog_opcionesClienteLayout.setVerticalGroup(
            dialog_opcionesClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_backDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CARSHOP: AGENTE DE COMERCIO");

        label_usuario.setFont(new java.awt.Font("Neou", 0, 13)); // NOI18N
        label_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_usuario.setText("Usuario: pedro@carshop");

        label_ImageEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btn_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/power96(1).png"))); // NOI18N
        btn_logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btn_settings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ASSETS/gears10(1).png"))); // NOI18N
        btn_settings.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        panel_inicio.setBackground(java.awt.Color.lightGray);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Neou", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Agente de comercio");

        btn_cliente.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_cliente.setForeground(new java.awt.Color(255, 255, 255));
        btn_cliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_cliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Agregar cliente");

        javax.swing.GroupLayout panel_addClienteLayout = new javax.swing.GroupLayout(panel_addCliente);
        panel_addCliente.setLayout(panel_addClienteLayout);
        panel_addClienteLayout.setHorizontalGroup(
            panel_addClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addClienteLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(panel_addClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_addClienteLayout.setVerticalGroup(
            panel_addClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addClienteLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        btn_addCompra.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_addCompra.setForeground(new java.awt.Color(255, 255, 255));
        btn_addCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_addCompra.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_addCompra.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Agregar compra");

        javax.swing.GroupLayout panel_addCompraLayout = new javax.swing.GroupLayout(panel_addCompra);
        panel_addCompra.setLayout(panel_addCompraLayout);
        panel_addCompraLayout.setHorizontalGroup(
            panel_addCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addCompraLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_addCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(panel_addCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_addCompraLayout.setVerticalGroup(
            panel_addCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addCompraLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_addCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        btn_addVenta.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_addVenta.setForeground(new java.awt.Color(255, 255, 255));
        btn_addVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_addVenta.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_addVenta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Agregar venta");

        javax.swing.GroupLayout panel_addVentaLayout = new javax.swing.GroupLayout(panel_addVenta);
        panel_addVenta.setLayout(panel_addVentaLayout);
        panel_addVentaLayout.setHorizontalGroup(
            panel_addVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_addVentaLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_addVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(panel_addVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_addVentaLayout.setVerticalGroup(
            panel_addVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_addVentaLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_addVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(10, 10, 10))
        );

        btn_verAutos.setFont(new java.awt.Font("Gulim", 1, 18)); // NOI18N
        btn_verAutos.setForeground(new java.awt.Color(255, 255, 255));
        btn_verAutos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_verAutos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_verAutos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Catalogo autos");

        javax.swing.GroupLayout panel_verAutosLayout = new javax.swing.GroupLayout(panel_verAutos);
        panel_verAutos.setLayout(panel_verAutosLayout);
        panel_verAutosLayout.setHorizontalGroup(
            panel_verAutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_verAutosLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btn_verAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(panel_verAutosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel_verAutosLayout.setVerticalGroup(
            panel_verAutosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_verAutosLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btn_verAutos, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(10, 10, 10))
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
                        .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(169, 169, 169))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panel_inicioLayout.createSequentialGroup()
                                        .addComponent(panel_verAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel_addCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_inicioLayout.createSequentialGroup()
                                        .addComponent(panel_addVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(panel_addCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(219, 219, 219))))))
        );
        panel_inicioLayout.setVerticalGroup(
            panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_addCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_verAutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_addCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_addVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
        panel_image.setLayout(panel_imageLayout);
        panel_imageLayout.setHorizontalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panel_imageLayout.setVerticalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addGap(0, 22, Short.MAX_VALUE)
                .addComponent(panel_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(JFComercioHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFComercioHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFComercioHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFComercioHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFComercioHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btn_addCliente;
    public javax.swing.JLabel btn_addCompra;
    public javax.swing.JLabel btn_addVenta;
    public javax.swing.JLabel btn_cancelar;
    public javax.swing.JLabel btn_cliente;
    public javax.swing.JLabel btn_logout;
    public javax.swing.JLabel btn_modCliente;
    public javax.swing.JLabel btn_settings;
    public javax.swing.JLabel btn_verAutos;
    public javax.swing.JDialog dialog_opcionesCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    public javax.swing.JLabel label_ImageEmpleado;
    private javax.swing.JLabel label_image;
    public javax.swing.JLabel label_usuario;
    private javax.swing.JPanel panel_addCliente;
    private javax.swing.JPanel panel_addCompra;
    private javax.swing.JPanel panel_addVenta;
    private javax.swing.JPanel panel_agreCliente;
    private javax.swing.JPanel panel_backDialog;
    private javax.swing.JPanel panel_image;
    private javax.swing.JPanel panel_inicio;
    private javax.swing.JPanel panel_modCliente;
    private javax.swing.JPanel panel_verAutos;
    // End of variables declaration//GEN-END:variables
}
