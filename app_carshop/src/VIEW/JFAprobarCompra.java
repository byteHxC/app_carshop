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
import javax.swing.JLabel;

/**
 *
 * @author Byter
 */
public class JFAprobarCompra extends javax.swing.JFrame {

    /**
     * Creates new form JFAprobarVenta
     */
    public JFAprobarCompra() {
        initComponents();  settingsFrame();
    }
 private void settingsFrame(){
        //Configuraciones jframe
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setSize(820, 650);
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
        
        //Opacity: set alpha at panel 
        Color color = new Color(255,255,255,200);
        panel_inicio.setBackground(color);
        Color color2 = new Color(0,0,0,200);
        panel_detallesCompra.setBackground(color2);
        Color color3 = new Color(0,0,0,100);
        panel_cliente.setBackground(color3);
        panel_auto.setBackground(color3);
        
        //Icon buttons
//        Image searchU = new ImageIcon(getClass().getResource("/ASSETS/searchU.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
//        this.btn_searchCliente.setIcon(new ImageIcon(searchU));
//        Image searchA = new ImageIcon(getClass().getResource("/ASSETS/searchA.png")).getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
//        this.btn_AddAuto.setIcon(new ImageIcon(searchA));
//        
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

        label_image = new javax.swing.JLabel();
        label_usuario = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        label_ImageEmpleado = new javax.swing.JLabel();
        panel_image = new javax.swing.JPanel();
        panel_inicio = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        panel_detallesCompra = new javax.swing.JPanel();
        panel_cliente = new javax.swing.JPanel();
        txt_clienteCveElector = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_ClienteNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_moreDetailsCustom = new javax.swing.JLabel();
        panel_auto = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_numSerie = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Precio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea_descripcion = new javax.swing.JTextArea();
        btn_moreDetailsCar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_encargadoVenta = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_NOAprobar = new javax.swing.JLabel();
        btn_aprobar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Aprobar compra");

        label_usuario.setFont(new java.awt.Font("Neou", 0, 13)); // NOI18N
        label_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_usuario.setText("Usuario: pedro@carshop");

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        label_ImageEmpleado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        panel_inicio.setBackground(java.awt.Color.lightGray);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setFont(new java.awt.Font("Neou", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Aprobar compra");

        panel_detallesCompra.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalles de compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gulim", 0, 12))); // NOI18N
        panel_detallesCompra.setOpaque(false);

        panel_cliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gulim", 0, 12))); // NOI18N
        panel_cliente.setOpaque(false);

        txt_clienteCveElector.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel2.setText("Clave elector:");

        txt_ClienteNombre.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel3.setText("Nombre: ");

        btn_moreDetailsCustom.setBackground(new java.awt.Color(51, 153, 255));
        btn_moreDetailsCustom.setFont(new java.awt.Font("Avenir Next", 0, 18)); // NOI18N
        btn_moreDetailsCustom.setForeground(new java.awt.Color(255, 255, 255));
        btn_moreDetailsCustom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_moreDetailsCustom.setText("Más detalles");
        btn_moreDetailsCustom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_moreDetailsCustom.setOpaque(true);

        javax.swing.GroupLayout panel_clienteLayout = new javax.swing.GroupLayout(panel_cliente);
        panel_cliente.setLayout(panel_clienteLayout);
        panel_clienteLayout.setHorizontalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_clienteCveElector, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                    .addComponent(txt_ClienteNombre))
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_clienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_moreDetailsCustom, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_clienteLayout.setVerticalGroup(
            panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_clienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_clienteCveElector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_ClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btn_moreDetailsCustom)
                .addContainerGap())
        );

        panel_auto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos auto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Gulim", 0, 12))); // NOI18N
        panel_auto.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel4.setText("Numero serie:");

        txt_numSerie.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel5.setText("Descripción:");

        jLabel6.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel6.setText("Precio: ");

        txt_Precio.setEditable(false);

        txtArea_descripcion.setEditable(false);
        txtArea_descripcion.setColumns(20);
        txtArea_descripcion.setLineWrap(true);
        txtArea_descripcion.setRows(5);
        jScrollPane1.setViewportView(txtArea_descripcion);

        btn_moreDetailsCar.setBackground(new java.awt.Color(51, 153, 255));
        btn_moreDetailsCar.setFont(new java.awt.Font("Avenir Next", 0, 18)); // NOI18N
        btn_moreDetailsCar.setForeground(new java.awt.Color(255, 255, 255));
        btn_moreDetailsCar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_moreDetailsCar.setText("Más detalles");
        btn_moreDetailsCar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_moreDetailsCar.setOpaque(true);

        javax.swing.GroupLayout panel_autoLayout = new javax.swing.GroupLayout(panel_auto);
        panel_auto.setLayout(panel_autoLayout);
        panel_autoLayout.setHorizontalGroup(
            panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_autoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_numSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_autoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_moreDetailsCar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_autoLayout.setVerticalGroup(
            panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_autoLayout.createSequentialGroup()
                .addGroup(panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_numSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_autoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_moreDetailsCar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setText("Encargado compra:");

        txt_encargadoVenta.setEditable(false);
        txt_encargadoVenta.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N

        txt_fecha.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Gulim", 0, 14)); // NOI18N
        jLabel11.setText("Fecha:");

        javax.swing.GroupLayout panel_detallesCompraLayout = new javax.swing.GroupLayout(panel_detallesCompra);
        panel_detallesCompra.setLayout(panel_detallesCompraLayout);
        panel_detallesCompraLayout.setHorizontalGroup(
            panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                        .addGroup(panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                                .addComponent(panel_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel_auto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_encargadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_detallesCompraLayout.setVerticalGroup(
            panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                .addGroup(panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(panel_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_detallesCompraLayout.createSequentialGroup()
                        .addGroup(panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(15, 15, 15)
                        .addComponent(panel_auto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(panel_detallesCompraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_encargadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        btn_NOAprobar.setBackground(new java.awt.Color(255, 102, 102));
        btn_NOAprobar.setFont(new java.awt.Font("Gulim", 0, 17)); // NOI18N
        btn_NOAprobar.setForeground(new java.awt.Color(255, 255, 255));
        btn_NOAprobar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_NOAprobar.setText("NO aprobar");
        btn_NOAprobar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_NOAprobar.setOpaque(true);

        btn_aprobar.setBackground(new java.awt.Color(84, 222, 66));
        btn_aprobar.setFont(new java.awt.Font("Gulim", 0, 17)); // NOI18N
        btn_aprobar.setForeground(new java.awt.Color(255, 255, 255));
        btn_aprobar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_aprobar.setText("Aprobar");
        btn_aprobar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_aprobar.setOpaque(true);

        javax.swing.GroupLayout panel_inicioLayout = new javax.swing.GroupLayout(panel_inicio);
        panel_inicio.setLayout(panel_inicioLayout);
        panel_inicioLayout.setHorizontalGroup(
            panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panel_inicioLayout.createSequentialGroup()
                        .addComponent(panel_detallesCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_inicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_NOAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_aprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        panel_inicioLayout.setVerticalGroup(
            panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_inicioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_detallesCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_aprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_NOAprobar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_imageLayout = new javax.swing.GroupLayout(panel_image);
        panel_image.setLayout(panel_imageLayout);
        panel_imageLayout.setHorizontalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_imageLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_imageLayout.setVerticalGroup(
            panel_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_imageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(label_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_ImageEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(label_image, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label_usuario)))
                    .addComponent(label_ImageEmpleado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(JFAprobarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFAprobarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFAprobarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFAprobarCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFAprobarCompra().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btn_NOAprobar;
    public javax.swing.JLabel btn_aprobar;
    public javax.swing.JLabel btn_moreDetailsCar;
    public javax.swing.JLabel btn_moreDetailsCustom;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JLabel label_ImageEmpleado;
    private javax.swing.JLabel label_image;
    public javax.swing.JLabel label_usuario;
    private javax.swing.JPanel panel_auto;
    private javax.swing.JPanel panel_cliente;
    private javax.swing.JPanel panel_detallesCompra;
    private javax.swing.JPanel panel_image;
    private javax.swing.JPanel panel_inicio;
    public javax.swing.JTextArea txtArea_descripcion;
    public javax.swing.JTextField txt_ClienteNombre;
    public javax.swing.JTextField txt_Precio;
    public javax.swing.JTextField txt_clienteCveElector;
    public javax.swing.JTextField txt_encargadoVenta;
    public javax.swing.JTextField txt_fecha;
    public javax.swing.JTextField txt_numSerie;
    // End of variables declaration//GEN-END:variables
}
