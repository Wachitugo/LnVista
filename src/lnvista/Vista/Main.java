package lnvista.Vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import lnvista.Conexion;

public class Main extends javax.swing.JFrame {

    Conexion conexion = new Conexion();
    Color azul = new Color(0x12, 0x34, 0x55);

    public Main() {
        buscaCho = new javax.swing.JTextField();
        initComponents();
        conexion.llenarTablaChofer(tablaChofer);
        conexion.llenarTablaMaquina(tablaMaquina);
        conexion.llenarTablaReporte(tablaReporte);
        conexion.cargarEmpresasEnComboBox(jCBEmpresa);
        conexion.cargarEmpresasEnComboBox(jCBEmpresaObra);
        conexion.llenarTablaobra(tablaObra);
        conexion.cargarEmpresaMaq(jCBObraMaq);
        String nombreEmpresaSeleccionada = (String) jCBEmpresa.getSelectedItem();
        conexion.cargarObrasEnComboBox(nombreEmpresaSeleccionada, jCBObra);
        String JCBobra = (String) jCBObra.getSelectedItem();
        conexion.cargarMaquinasPorObra(JCBobra, jCBMaquina);

        conexion.cargarTelefonoYCorreo(FonoEmpObra, correoEmObra, jCBEmpresaObra, this);

        conexion.cargarSuperEnComboBox(jCBSupervisorObra);
        tablaReporte.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        ajustarAnchoColumnas(tablaReporte);

        tablaChofer.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                llenarCamposDesdeFilaSeleccionada();
            }
        });
        tablaObra.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                llenarCamposDesdeFilaSeleccionadaObra();
            }
        });
        tablaMaquina.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                FilaSeleccionadaMaquina();
            }
        });
    }

    private void ajustarAnchoColumnas(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();

        for (int col = 0; col < table.getColumnCount(); col++) {
            int maxWidth = 0;

            // Obtener el ancho del encabezado
            TableColumn column = columnModel.getColumn(col);
            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer == null) {
                headerRenderer = table.getTableHeader().getDefaultRenderer();
            }
            Component headerComp = headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, col);
            int headerWidth = headerComp.getPreferredSize().width;

            // Calcular el ancho máximo del contenido de la columna
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(row, col);
                Object value = table.getValueAt(row, col);
                Component rendererComponent = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, col);
                maxWidth = Math.max(rendererComponent.getPreferredSize().width + table.getIntercellSpacing().width, maxWidth);
            }

            // Establecer el ancho de la columna al máximo de los dos (encabezado y contenido)
            int finalWidth = Math.max(headerWidth, maxWidth);
            column.setPreferredWidth(finalWidth);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        Chofer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaChofer = new javax.swing.JTable();
        runCho = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        EliminarCh = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        AgregarCh = new javax.swing.JLabel();
        jCBEmpresa = new javax.swing.JComboBox<>();
        jCBObra = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        ModificarCh = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        buscaCho = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nomEmp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCBMaquina = new javax.swing.JComboBox<>();
        Maquina = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaMaquina = new javax.swing.JTable();
        patenteMaq = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        EliminarMaq = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        AgregarMaq = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        ModificarMaq = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buscaMaquina = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        kilometrosMaq = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        nombreMaquinaMaq = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        horometroMaq = new javax.swing.JTextField();
        jCBObraMaq = new javax.swing.JComboBox<>();
        Obra = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaObra = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        EliminarObra = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        AgregarObra = new javax.swing.JLabel();
        jCBEmpresaObra = new javax.swing.JComboBox<>();
        jPanel16 = new javax.swing.JPanel();
        ModificarObra = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        buscatodoObra = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        correoEmObra = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jCBSupervisorObra = new javax.swing.JComboBox<>();
        descObra = new javax.swing.JTextField();
        FonoEmpObra = new javax.swing.JTextField();
        nomObraObra = new javax.swing.JTextField();
        Reporte = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaReporte = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        EliminarReporte = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        buscaReporte = new javax.swing.JTextField();
        navbar = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        selectChofer = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        selectCamiones = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        selectObra = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        selectReporte = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        selectSupervisor = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Chofer.setPreferredSize(new java.awt.Dimension(800, 600));
        Chofer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaChofer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Run", "Nombre", "Empresa", "Obra", "Maquina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaChofer);
        if (tablaChofer.getColumnModel().getColumnCount() > 0) {
            tablaChofer.getColumnModel().getColumn(0).setResizable(false);
            tablaChofer.getColumnModel().getColumn(1).setResizable(false);
            tablaChofer.getColumnModel().getColumn(2).setResizable(false);
            tablaChofer.getColumnModel().getColumn(3).setResizable(false);
            tablaChofer.getColumnModel().getColumn(4).setResizable(false);
        }

        Chofer.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 780, 440));

        runCho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        runCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runChoActionPerformed(evt);
            }
        });
        runCho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                runChoKeyPressed(evt);
            }
        });
        Chofer.add(runCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 172, 30));

        jPanel3.setBackground(new java.awt.Color(153, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setVerifyInputWhenFocusTarget(false);

        EliminarCh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EliminarCh.setForeground(new java.awt.Color(255, 255, 255));
        EliminarCh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EliminarCh.setText("Eliminar");
        EliminarCh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarChMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarCh, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarCh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Chofer.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, 30));

        jPanel4.setBackground(new java.awt.Color(51, 153, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setPreferredSize(new java.awt.Dimension(65, 30));

        AgregarCh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AgregarCh.setForeground(new java.awt.Color(255, 255, 255));
        AgregarCh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AgregarCh.setText("Agregar");
        AgregarCh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AgregarChMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarCh, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarCh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Chofer.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 71, -1));

        jCBEmpresa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEmpresaActionPerformed(evt);
            }
        });
        Chofer.add(jCBEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 172, 30));

        jCBObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBObraActionPerformed(evt);
            }
        });
        Chofer.add(jCBObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 172, 30));

        jPanel5.setBackground(new java.awt.Color(0, 0, 153));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setPreferredSize(new java.awt.Dimension(112, 53));

        ModificarCh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ModificarCh.setForeground(new java.awt.Color(255, 255, 255));
        ModificarCh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModificarCh.setText("Modificar");
        ModificarCh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarChMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarCh, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarCh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        Chofer.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, 71, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Buscar");
        Chofer.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Empresa:");
        Chofer.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Obra:");
        Chofer.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, -1, -1));

        buscaCho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscaCho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaChoActionPerformed(evt);
            }
        });
        buscaCho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaChoKeyPressed(evt);
            }
        });
        Chofer.add(buscaCho, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 230, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Run:");
        Chofer.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 520, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nombre:");
        Chofer.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, 62, -1));

        nomEmp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nomEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomEmpActionPerformed(evt);
            }
        });
        nomEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomEmpKeyPressed(evt);
            }
        });
        Chofer.add(nomEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 172, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Maquina");
        Chofer.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, -1));

        jCBMaquina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBMaquina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBMaquinaActionPerformed(evt);
            }
        });
        Chofer.add(jCBMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 166, 30));

        jTabbedPane1.addTab("tab1", Chofer);

        Maquina.setPreferredSize(new java.awt.Dimension(800, 600));
        Maquina.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaMaquina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Patente", "Nombre Maquina", "Kilometros", "Horometro", "Obra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaMaquina);
        if (tablaMaquina.getColumnModel().getColumnCount() > 0) {
            tablaMaquina.getColumnModel().getColumn(0).setResizable(false);
            tablaMaquina.getColumnModel().getColumn(1).setResizable(false);
            tablaMaquina.getColumnModel().getColumn(2).setResizable(false);
            tablaMaquina.getColumnModel().getColumn(3).setResizable(false);
            tablaMaquina.getColumnModel().getColumn(4).setResizable(false);
        }

        Maquina.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 780, 440));

        patenteMaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        patenteMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patenteMaqActionPerformed(evt);
            }
        });
        patenteMaq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                patenteMaqKeyPressed(evt);
            }
        });
        Maquina.add(patenteMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 490, 172, 30));

        jPanel8.setBackground(new java.awt.Color(153, 0, 0));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setVerifyInputWhenFocusTarget(false);

        EliminarMaq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EliminarMaq.setForeground(new java.awt.Color(255, 255, 255));
        EliminarMaq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EliminarMaq.setText("Eliminar");
        EliminarMaq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarMaqMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarMaq, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarMaq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Maquina.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, -1, 30));

        jPanel9.setBackground(new java.awt.Color(51, 153, 0));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setPreferredSize(new java.awt.Dimension(65, 30));

        AgregarMaq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AgregarMaq.setForeground(new java.awt.Color(255, 255, 255));
        AgregarMaq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AgregarMaq.setText("Agregar");
        AgregarMaq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AgregarMaqMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarMaq, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarMaq, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Maquina.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 540, 71, -1));

        jPanel11.setBackground(new java.awt.Color(0, 0, 153));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel11.setPreferredSize(new java.awt.Dimension(112, 53));

        ModificarMaq.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ModificarMaq.setForeground(new java.awt.Color(255, 255, 255));
        ModificarMaq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModificarMaq.setText("Modificar");
        ModificarMaq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarMaqMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarMaq, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarMaq, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        Maquina.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 540, 71, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Buscar");
        Maquina.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Nombre Maquina:");
        Maquina.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 470, -1, -1));

        buscaMaquina.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaMaquinaActionPerformed(evt);
            }
        });
        buscaMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaMaquinaKeyPressed(evt);
            }
        });
        Maquina.add(buscaMaquina, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 230, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Patente:");
        Maquina.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Obra:");
        Maquina.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 520, 62, -1));

        kilometrosMaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        kilometrosMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kilometrosMaqActionPerformed(evt);
            }
        });
        kilometrosMaq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kilometrosMaqKeyPressed(evt);
            }
        });
        Maquina.add(kilometrosMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 172, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Kilometros:");
        Maquina.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 470, -1, -1));

        nombreMaquinaMaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nombreMaquinaMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreMaquinaMaqActionPerformed(evt);
            }
        });
        nombreMaquinaMaq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreMaquinaMaqKeyPressed(evt);
            }
        });
        Maquina.add(nombreMaquinaMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 490, 172, 30));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Horometro");
        Maquina.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 520, -1, -1));

        horometroMaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        horometroMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                horometroMaqActionPerformed(evt);
            }
        });
        horometroMaq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                horometroMaqKeyPressed(evt);
            }
        });
        Maquina.add(horometroMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, 172, 30));

        jCBObraMaq.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBObraMaq.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBObraMaq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBObraMaqActionPerformed(evt);
            }
        });
        Maquina.add(jCBObraMaq, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 540, 172, 30));

        jTabbedPane1.addTab("tab1", Maquina);

        Obra.setPreferredSize(new java.awt.Dimension(800, 600));
        Obra.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tablaObra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre Empresa ", "Fono Empresa", "Correo Empresa", "Obra", "Supervisor", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaObra);
        if (tablaObra.getColumnModel().getColumnCount() > 0) {
            tablaObra.getColumnModel().getColumn(0).setResizable(false);
            tablaObra.getColumnModel().getColumn(1).setResizable(false);
            tablaObra.getColumnModel().getColumn(2).setResizable(false);
            tablaObra.getColumnModel().getColumn(3).setResizable(false);
            tablaObra.getColumnModel().getColumn(4).setResizable(false);
            tablaObra.getColumnModel().getColumn(5).setResizable(false);
        }

        Obra.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 780, 370));

        jPanel14.setBackground(new java.awt.Color(153, 0, 0));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setVerifyInputWhenFocusTarget(false);

        EliminarObra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EliminarObra.setForeground(new java.awt.Color(255, 255, 255));
        EliminarObra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EliminarObra.setText("Eliminar");
        EliminarObra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarObraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarObra, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Obra.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 550, -1, 30));

        jPanel15.setBackground(new java.awt.Color(51, 153, 0));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setPreferredSize(new java.awt.Dimension(65, 30));

        AgregarObra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        AgregarObra.setForeground(new java.awt.Color(255, 255, 255));
        AgregarObra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AgregarObra.setText("Agregar");
        AgregarObra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AgregarObraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarObra, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AgregarObra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Obra.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 71, -1));

        jCBEmpresaObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBEmpresaObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBEmpresaObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBEmpresaObraActionPerformed(evt);
            }
        });
        Obra.add(jCBEmpresaObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 172, 30));

        jPanel16.setBackground(new java.awt.Color(0, 0, 153));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setPreferredSize(new java.awt.Dimension(112, 53));

        ModificarObra.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ModificarObra.setForeground(new java.awt.Color(255, 255, 255));
        ModificarObra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ModificarObra.setText("Modificar");
        ModificarObra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ModificarObraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarObra, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ModificarObra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        Obra.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 71, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("Buscar");
        Obra.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("Empresa:");
        Obra.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("Obra:");
        Obra.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, -1, -1));

        buscatodoObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscatodoObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscatodoObraActionPerformed(evt);
            }
        });
        buscatodoObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscatodoObraKeyPressed(evt);
            }
        });
        Obra.add(buscatodoObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 230, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setText("Fono Empresa:");
        Obra.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setText("Correo Empresa:");
        Obra.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 410, 100, -1));

        correoEmObra.setEditable(false);
        correoEmObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        correoEmObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoEmObraActionPerformed(evt);
            }
        });
        correoEmObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                correoEmObraKeyPressed(evt);
            }
        });
        Obra.add(correoEmObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 430, 172, 30));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Descripcion obra:");
        Obra.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 510, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setText("Supervisor:");
        Obra.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 460, -1, -1));

        jCBSupervisorObra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jCBSupervisorObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jCBSupervisorObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSupervisorObraActionPerformed(evt);
            }
        });
        Obra.add(jCBSupervisorObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 480, 170, 30));

        descObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Obra.add(descObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, 530, 50));

        FonoEmpObra.setEditable(false);
        FonoEmpObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FonoEmpObra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FonoEmpObraActionPerformed(evt);
            }
        });
        FonoEmpObra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FonoEmpObraKeyPressed(evt);
            }
        });
        Obra.add(FonoEmpObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 172, 30));

        nomObraObra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Obra.add(nomObraObra, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 480, 170, 30));

        jTabbedPane1.addTab("tab1", Obra);

        Reporte.setPreferredSize(new java.awt.Dimension(800, 600));
        Reporte.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane4.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setPreferredSize(new java.awt.Dimension(800, 600));

        tablaReporte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "N° Reporte", "Chofer", "Fecha", "Empresa", "Obra", "Maquina", "Supervisor", "Litros", "Km/Hora", "Horario Mañana", "Horario Tarde", "Observacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaReporte.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane4.setViewportView(tablaReporte);
        if (tablaReporte.getColumnModel().getColumnCount() > 0) {
            tablaReporte.getColumnModel().getColumn(0).setResizable(false);
            tablaReporte.getColumnModel().getColumn(1).setResizable(false);
            tablaReporte.getColumnModel().getColumn(2).setResizable(false);
            tablaReporte.getColumnModel().getColumn(3).setResizable(false);
            tablaReporte.getColumnModel().getColumn(4).setResizable(false);
            tablaReporte.getColumnModel().getColumn(5).setResizable(false);
            tablaReporte.getColumnModel().getColumn(6).setResizable(false);
            tablaReporte.getColumnModel().getColumn(7).setResizable(false);
            tablaReporte.getColumnModel().getColumn(8).setResizable(false);
            tablaReporte.getColumnModel().getColumn(9).setResizable(false);
            tablaReporte.getColumnModel().getColumn(10).setResizable(false);
            tablaReporte.getColumnModel().getColumn(11).setResizable(false);
        }

        Reporte.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 780, 440));

        jPanel18.setBackground(new java.awt.Color(153, 0, 0));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel18.setVerifyInputWhenFocusTarget(false);

        EliminarReporte.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        EliminarReporte.setForeground(new java.awt.Color(255, 255, 255));
        EliminarReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EliminarReporte.setText("Eliminar");
        EliminarReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarReporteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(EliminarReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Reporte.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 550, -1, 30));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setText("Buscar");
        Reporte.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, -1));

        buscaReporte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscaReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscaReporteActionPerformed(evt);
            }
        });
        buscaReporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaReporteKeyPressed(evt);
            }
        });
        Reporte.add(buscaReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 230, 30));

        jTabbedPane1.addTab("tab1", Reporte);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, -40, 800, 640));

        navbar.setBackground(new java.awt.Color(18, 52, 85));
        navbar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo ln.png"))); // NOI18N
        navbar.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 17, -1, -1));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        selectChofer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectChofer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectChofer.setText("CHOFER");
        selectChofer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectChoferMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectChofer, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectChofer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        navbar.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 200, 40));

        jPanel10.setBackground(new java.awt.Color(18, 52, 85));

        selectCamiones.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectCamiones.setForeground(new java.awt.Color(255, 255, 255));
        selectCamiones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectCamiones.setText("MAQUINA");
        selectCamiones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectCamionesMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectCamiones, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(selectCamiones, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        navbar.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 40));

        jPanel13.setBackground(new java.awt.Color(18, 52, 85));

        selectObra.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectObra.setForeground(new java.awt.Color(255, 255, 255));
        selectObra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectObra.setText("OBRA");
        selectObra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectObraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectObra, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectObra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        navbar.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 200, 40));

        jPanel17.setBackground(new java.awt.Color(18, 52, 85));

        selectReporte.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectReporte.setForeground(new java.awt.Color(255, 255, 255));
        selectReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectReporte.setText("REPORTE");
        selectReporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectReporteMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectReporte, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        navbar.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 200, 40));

        jPanel21.setBackground(new java.awt.Color(18, 52, 85));

        selectSupervisor.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        selectSupervisor.setForeground(new java.awt.Color(255, 255, 255));
        selectSupervisor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectSupervisor.setText("SUPERVISOR");
        selectSupervisor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectSupervisorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectSupervisor, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(selectSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        navbar.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 200, 40));

        getContentPane().add(navbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void selectChoferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectChoferMouseClicked
        jTabbedPane1.setSelectedIndex(0);
        selectCamiones.setForeground(Color.WHITE);
        jPanel10.setBackground(azul);
        jPanel12.setBackground(Color.WHITE);
        selectChofer.setForeground(Color.BLACK);
        selectObra.setForeground(Color.WHITE);
        jPanel13.setBackground(azul);
        jPanel17.setBackground(azul);
        selectReporte.setForeground(Color.WHITE);
        jPanel21.setBackground(azul);
        selectSupervisor.setForeground(Color.WHITE);
    }//GEN-LAST:event_selectChoferMouseClicked

    private void selectCamionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectCamionesMouseClicked
        jTabbedPane1.setSelectedIndex(1);
        selectChofer.setForeground(Color.WHITE);
        jPanel12.setBackground(azul);
        jPanel10.setBackground(Color.WHITE);
        selectCamiones.setForeground(Color.BLACK);
        selectObra.setForeground(Color.WHITE);
        jPanel13.setBackground(azul);
        jPanel17.setBackground(azul);
        selectReporte.setForeground(Color.WHITE);
        jPanel21.setBackground(azul);
        selectSupervisor.setForeground(Color.WHITE);
    }//GEN-LAST:event_selectCamionesMouseClicked

    private void selectObraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectObraMouseClicked
        jTabbedPane1.setSelectedIndex(2);
        selectCamiones.setForeground(Color.WHITE);
        jPanel10.setBackground(azul);
        jPanel12.setBackground(azul);
        selectChofer.setForeground(Color.WHITE);
        selectObra.setForeground(Color.BLACK);
        jPanel13.setBackground(Color.WHITE);
        jPanel17.setBackground(azul);
        selectReporte.setForeground(Color.WHITE);
        jPanel17.setBackground(azul);
        selectReporte.setForeground(Color.WHITE);
        jPanel21.setBackground(azul);
        selectSupervisor.setForeground(Color.WHITE);
    }//GEN-LAST:event_selectObraMouseClicked

    private void selectReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectReporteMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        jPanel17.setBackground(Color.WHITE);
        selectReporte.setForeground(Color.BLACK);
        selectCamiones.setForeground(Color.WHITE);
        jPanel10.setBackground(azul);
        jPanel12.setBackground(azul);
        selectChofer.setForeground(Color.WHITE);
        selectObra.setForeground(Color.WHITE);
        jPanel13.setBackground(azul);
        jPanel21.setBackground(azul);
        selectSupervisor.setForeground(Color.WHITE); 
     }//GEN-LAST:event_selectReporteMouseClicked

    private void buscaReporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaReporteKeyPressed
        conexion.buscarReporte(buscaReporte, tablaReporte);
    }//GEN-LAST:event_buscaReporteKeyPressed

    private void buscaReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscaReporteActionPerformed

    private void EliminarReporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarReporteMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_EliminarReporteMouseClicked

    private void kilometrosMaqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kilometrosMaqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kilometrosMaqKeyPressed

    private void kilometrosMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kilometrosMaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kilometrosMaqActionPerformed

    private void buscaMaquinaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaMaquinaKeyPressed
        conexion.buscarRegistrosMaquina(buscaMaquina, tablaMaquina);
    }//GEN-LAST:event_buscaMaquinaKeyPressed

    private void buscaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaMaquinaActionPerformed

    }//GEN-LAST:event_buscaMaquinaActionPerformed

    private void ModificarMaqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarMaqMouseClicked
        int filaSeleccionada = tablaMaquina.getSelectedRow();
        String patenteAntigua = (String) tablaMaquina.getValueAt(filaSeleccionada, 0);
        String patente = patenteMaq.getText();
        String nombre = nombreMaquinaMaq.getText();
        String nombreAntiguo = (String) tablaMaquina.getValueAt(filaSeleccionada, 1);
        String obra = (String) jCBObraMaq.getSelectedItem();
        String kilometros = kilometrosMaq.getText();
        String horometro = horometroMaq.getText();
        conexion.modificarMaquina(patenteAntigua, patente, nombre, nombreAntiguo, obra, kilometros, horometro, this);
        conexion.llenarTablaMaquina(tablaMaquina);
    }//GEN-LAST:event_ModificarMaqMouseClicked

    private void EliminarMaqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarMaqMouseClicked
        eliminarFilaSeleccionadaMaquina();
        String JCBobra = (String) jCBObra.getSelectedItem();
        conexion.cargarMaquinasPorObra(JCBobra, jCBMaquina);

    }//GEN-LAST:event_EliminarMaqMouseClicked

    private void patenteMaqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patenteMaqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_patenteMaqKeyPressed

    private void patenteMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patenteMaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patenteMaqActionPerformed

    private void jCBMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBMaquinaActionPerformed

    private void nomEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomEmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomEmpKeyPressed

    private void nomEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomEmpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomEmpActionPerformed

    private void buscaChoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaChoKeyPressed
        conexion.buscarChofer(buscaCho, tablaChofer);
    }//GEN-LAST:event_buscaChoKeyPressed

    private void buscaChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscaChoActionPerformed

    }//GEN-LAST:event_buscaChoActionPerformed

    private void ModificarChMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarChMouseClicked
        int filaSeleccionada = tablaChofer.getSelectedRow();
        Object runAObj = tablaChofer.getValueAt(filaSeleccionada, 0);
        Object maqAObj = tablaChofer.getValueAt(filaSeleccionada, 4);
        String runA = runAObj.toString();
        String maqA = maqAObj.toString();
        int run = Integer.parseInt(runCho.getText());
        int runAnt = Integer.parseInt(runA);
        String nombre = nomEmp.getText();
        String maquina = (String) jCBMaquina.getSelectedItem();
        String obra = (String) jCBObra.getSelectedItem();
        conexion.modificarChofer(nombre,run, runAnt,obra,maquina,maqA, this);
        conexion.llenarTablaChofer(tablaChofer);
    }//GEN-LAST:event_ModificarChMouseClicked

    private void jCBObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBObraActionPerformed
        String JCBobra = (String) jCBObra.getSelectedItem();
        conexion.cargarMaquinasPorObra(JCBobra, jCBMaquina);

    }//GEN-LAST:event_jCBObraActionPerformed

    private void jCBEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEmpresaActionPerformed

        String nombreEmpresaSeleccionada = (String) jCBEmpresa.getSelectedItem();
        conexion.cargarObrasEnComboBox(nombreEmpresaSeleccionada, jCBObra);
    }//GEN-LAST:event_jCBEmpresaActionPerformed

    private void AgregarChMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarChMouseClicked
        int rut = Integer.parseInt(runCho.getText());
        String nombre = nomEmp.getText();
        String obra = (String) jCBObra.getSelectedItem();
        String maquina = (String) jCBMaquina.getSelectedItem();
        conexion.agregarChofer(rut, nombre, obra, maquina, this);
        conexion.llenarTablaChofer(tablaChofer);
    }//GEN-LAST:event_AgregarChMouseClicked

    private void EliminarChMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarChMouseClicked
        eliminarFilaSeleccionadaCho();
        limpiarCampos();
    }//GEN-LAST:event_EliminarChMouseClicked

    private void runChoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_runChoKeyPressed

    }//GEN-LAST:event_runChoKeyPressed

    private void runChoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runChoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_runChoActionPerformed

    private void FonoEmpObraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FonoEmpObraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_FonoEmpObraKeyPressed

    private void FonoEmpObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FonoEmpObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FonoEmpObraActionPerformed

    private void jCBSupervisorObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSupervisorObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBSupervisorObraActionPerformed

    private void correoEmObraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_correoEmObraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoEmObraKeyPressed

    private void correoEmObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoEmObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoEmObraActionPerformed

    private void buscatodoObraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscatodoObraKeyPressed

        conexion.buscarRegistrosObra(buscatodoObra, tablaObra);
    }//GEN-LAST:event_buscatodoObraKeyPressed

    private void buscatodoObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscatodoObraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscatodoObraActionPerformed

    private void ModificarObraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ModificarObraMouseClicked
        int filaSeleccionada = tablaObra.getSelectedRow();
        String nombreObraAnterior = (String) tablaObra.getValueAt(filaSeleccionada, 3);
        String nuevoNombreObra = nomObraObra.getText();
        String nombreCliente = (String) jCBEmpresaObra.getSelectedItem();
        String nombreSupervisor = (String) jCBSupervisorObra.getSelectedItem();
        String nuevaDescripcion = descObra.getText();

        conexion.modificarObra(nombreObraAnterior, nuevoNombreObra, nombreCliente, nombreSupervisor, nuevaDescripcion, this);
        conexion.llenarTablaobra(tablaObra);
        conexion.llenarTablaMaquina(tablaMaquina);
        conexion.llenarTablaChofer(tablaChofer);
    }//GEN-LAST:event_ModificarObraMouseClicked

    private void jCBEmpresaObraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBEmpresaObraActionPerformed
        if (tablaObra.getSelectedRow() == -1) { // Verifica si no hay fila seleccionada en la tabla
            limpiarCamposObra();
        }
        conexion.cargarTelefonoYCorreo(FonoEmpObra, correoEmObra, jCBEmpresaObra, this);
    }//GEN-LAST:event_jCBEmpresaObraActionPerformed

    private void AgregarObraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarObraMouseClicked
        String nombreCliente = (String) jCBEmpresaObra.getSelectedItem();
        String nombreSupervisor = (String) jCBSupervisorObra.getSelectedItem();
        String descripcion = descObra.getText();
        String nombreObra = nomObraObra.getText();

        conexion.agregarObra(nombreObra, nombreCliente, nombreSupervisor, descripcion, this);
        conexion.llenarTablaobra(tablaObra);
        conexion.llenarTablaMaquina(tablaMaquina);
        conexion.llenarTablaChofer(tablaChofer);
        conexion.cargarEmpresaMaq(jCBObraMaq);

    }//GEN-LAST:event_AgregarObraMouseClicked

    private void EliminarObraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarObraMouseClicked
        eliminarFilaSeleccionadaObra();

    }//GEN-LAST:event_EliminarObraMouseClicked

    private void nombreMaquinaMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreMaquinaMaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreMaquinaMaqActionPerformed

    private void nombreMaquinaMaqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreMaquinaMaqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreMaquinaMaqKeyPressed

    private void horometroMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_horometroMaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_horometroMaqActionPerformed

    private void horometroMaqKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_horometroMaqKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_horometroMaqKeyPressed

    private void jCBObraMaqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBObraMaqActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCBObraMaqActionPerformed

    private void AgregarMaqMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AgregarMaqMouseClicked
        String obra = (String) jCBObraMaq.getSelectedItem();
        String patente = patenteMaq.getText();
        String kilometros = kilometrosMaq.getText();
        String horometro = horometroMaq.getText();
        String nombre = nombreMaquinaMaq.getText();

        conexion.agregarMaquina(obra, patente, kilometros, horometro, nombre, this);
        conexion.llenarTablaMaquina(tablaMaquina);
        String JCBobra = (String) jCBObra.getSelectedItem();
        conexion.cargarMaquinasPorObra(JCBobra, jCBMaquina);
    }//GEN-LAST:event_AgregarMaqMouseClicked

    private void selectSupervisorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectSupervisorMouseClicked
        jTabbedPane1.setSelectedIndex(3);
        jPanel17.setBackground(azul);
        selectReporte.setForeground(Color.WHITE);
        selectCamiones.setForeground(Color.WHITE);
        jPanel10.setBackground(azul);
        jPanel12.setBackground(azul);
        selectChofer.setForeground(Color.WHITE);
        selectObra.setForeground(Color.WHITE);
        jPanel13.setBackground(azul);
        jPanel21.setBackground(Color.WHITE);
        selectSupervisor.setForeground(Color.BLACK); 


    }//GEN-LAST:event_selectSupervisorMouseClicked

    private void eliminarFilaSeleccionadaCho() {
        int filaSeleccionada = tablaChofer.getSelectedRow();
        Integer runInteger = (Integer) tablaChofer.getValueAt(filaSeleccionada, 0);
        String run = String.valueOf(runInteger);

        if (filaSeleccionada != -1) {
            Object[] options = {"Sí", "No"};
            int confirmacion = JOptionPane.showOptionDialog(
                    this,
                    "¿Estás seguro de eliminar esta fila?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                conexion.eliminarChofer(run, this);
                conexion.llenarTablaChofer(tablaChofer);
                JOptionPane.showMessageDialog(null, "Fila eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFilaSeleccionadaObra() {
        int filaSeleccionada = tablaObra.getSelectedRow();
        String nomOb = (String) tablaObra.getValueAt(filaSeleccionada, 3);
        if (filaSeleccionada != -1) {
            Object[] options = {"Sí", "No"};
            int confirmacion = JOptionPane.showOptionDialog(
                    this,
                    "¿Estás seguro de eliminar esta fila?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                conexion.eliminarObra(nomOb);
                conexion.llenarTablaobra(tablaObra);
                JOptionPane.showMessageDialog(null, "Fila eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarFilaSeleccionadaMaquina() {
        int filaSeleccionada = tablaMaquina.getSelectedRow();
        String patente = (String) tablaMaquina.getValueAt(filaSeleccionada, 0);
        if (filaSeleccionada != -1) {
            Object[] options = {"Sí", "No"};
            int confirmacion = JOptionPane.showOptionDialog(
                    this,
                    "¿Estás seguro de eliminar esta fila?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (confirmacion == JOptionPane.YES_OPTION) {
                conexion.eliminarMaquina(patente);
                conexion.llenarTablaMaquina(tablaMaquina);
                JOptionPane.showMessageDialog(null, "Fila eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void FilaSeleccionadaMaquina() {
        int filaSeleccionada = tablaMaquina.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener datos de la fila seleccionada
            String patente = (String) tablaMaquina.getValueAt(filaSeleccionada, 0);
            String tipomaq = (String) tablaMaquina.getValueAt(filaSeleccionada, 1);
            String kilometros = String.valueOf(tablaMaquina.getValueAt(filaSeleccionada, 2));
            String horometro = String.valueOf(tablaMaquina.getValueAt(filaSeleccionada, 3));
            String nomObra = (String) tablaMaquina.getValueAt(filaSeleccionada, 4);

            // Llenar JTextField
            patenteMaq.setText(patente);
            nombreMaquinaMaq.setText(tipomaq);
            kilometrosMaq.setText(kilometros);
            horometroMaq.setText(horometro);

            jCBObraMaq.setSelectedItem(nomObra);

        }
    }

    private void llenarCamposDesdeFilaSeleccionada() {
        int filaSeleccionada = tablaChofer.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener datos de la fila seleccionada
            String runEmpe = String.valueOf(tablaChofer.getValueAt(filaSeleccionada, 0));
            String nomEmpe = (String) tablaChofer.getValueAt(filaSeleccionada, 1);
            String nombreObraSeleccionada = (String) tablaChofer.getValueAt(filaSeleccionada, 3);
            String nombreEmpresaSeleccionada = (String) tablaChofer.getValueAt(filaSeleccionada, 2);
            String nombreCamionSeleccionado = (String) tablaChofer.getValueAt(filaSeleccionada, 4);

            // Llenar JTextField
            runCho.setText(runEmpe);
            nomEmp.setText(nomEmpe);

            jCBEmpresa.setSelectedItem(nombreEmpresaSeleccionada);
            jCBMaquina.setSelectedItem(nombreCamionSeleccionado);
            jCBObra.setSelectedItem(nombreObraSeleccionada);
        }
    }

    private void llenarCamposDesdeFilaSeleccionadaObra() {
        int filaSeleccionada = tablaObra.getSelectedRow();

        if (filaSeleccionada != -1) {
            // Obtener datos de la fila seleccionada
            String nomEmpl = (String) tablaObra.getValueAt(filaSeleccionada, 0);
            String fonoEmp = String.valueOf(tablaObra.getValueAt(filaSeleccionada, 1));
            String correoEmp = (String) tablaObra.getValueAt(filaSeleccionada, 2);
            String nomOb = (String) tablaObra.getValueAt(filaSeleccionada, 3);
            String SuperOb = (String) tablaObra.getValueAt(filaSeleccionada, 4);
            String DescOb = (String) tablaObra.getValueAt(filaSeleccionada, 5);
            // Llenar JTextField
            correoEmObra.setText(correoEmp);
            descObra.setText(DescOb);
            FonoEmpObra.setText(fonoEmp);
            nomObraObra.setText(nomOb);
            jCBEmpresaObra.setSelectedItem(nomEmpl);
            jCBSupervisorObra.setSelectedItem(SuperOb);
        }
    }

    private void limpiarCampos() {
        // Limpiar los JTextField
        runCho.setText("");
        nomEmp.setText("");

        // Limpiar los JComboBox
        jCBEmpresa.setSelectedIndex(0);
        jCBObra.setSelectedIndex(0);
        jCBMaquina.setSelectedIndex(0);
    }

    private void limpiarCamposObra() {
        // Limpiar los JTextField
        FonoEmpObra.setText("");
        correoEmObra.setText("");
        descObra.setText("");
        nomObraObra.setText("");

        // Limpiar los JComboBox
        jCBSupervisorObra.setSelectedIndex(0);
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AgregarCh;
    private javax.swing.JLabel AgregarMaq;
    private javax.swing.JLabel AgregarObra;
    private javax.swing.JPanel Chofer;
    private javax.swing.JLabel EliminarCh;
    private javax.swing.JLabel EliminarMaq;
    private javax.swing.JLabel EliminarObra;
    private javax.swing.JLabel EliminarReporte;
    private javax.swing.JTextField FonoEmpObra;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel Maquina;
    private javax.swing.JLabel ModificarCh;
    private javax.swing.JLabel ModificarMaq;
    private javax.swing.JLabel ModificarObra;
    private javax.swing.JPanel Obra;
    private javax.swing.JPanel Reporte;
    private javax.swing.JTextField buscaCho;
    private javax.swing.JTextField buscaMaquina;
    private javax.swing.JTextField buscaReporte;
    private javax.swing.JTextField buscatodoObra;
    private javax.swing.JTextField correoEmObra;
    private javax.swing.JTextField descObra;
    private javax.swing.JTextField horometroMaq;
    private javax.swing.JComboBox<String> jCBEmpresa;
    private javax.swing.JComboBox<String> jCBEmpresaObra;
    private javax.swing.JComboBox<String> jCBMaquina;
    private javax.swing.JComboBox<String> jCBObra;
    private javax.swing.JComboBox<String> jCBObraMaq;
    private javax.swing.JComboBox<String> jCBSupervisorObra;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField kilometrosMaq;
    private javax.swing.JPanel navbar;
    private javax.swing.JTextField nomEmp;
    private javax.swing.JTextField nomObraObra;
    private javax.swing.JTextField nombreMaquinaMaq;
    private javax.swing.JTextField patenteMaq;
    private javax.swing.JTextField runCho;
    private javax.swing.JLabel selectCamiones;
    private javax.swing.JLabel selectChofer;
    private javax.swing.JLabel selectObra;
    private javax.swing.JLabel selectReporte;
    private javax.swing.JLabel selectSupervisor;
    private javax.swing.JTable tablaChofer;
    private javax.swing.JTable tablaMaquina;
    private javax.swing.JTable tablaObra;
    private javax.swing.JTable tablaReporte;
    // End of variables declaration//GEN-END:variables
}
