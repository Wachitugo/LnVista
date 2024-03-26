package lnvista;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Conexion {

    private final String url = "jdbc:postgresql://localhost:5432/ln";
    private final String user = "postgres";
    private final String password = "12345";

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    // LLENAR TABLAS
    public void llenarTablaChofer(JTable tablaChofer) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT c.rut_cho AS run,c.nombre_cho AS nombre,cl.nombre_cliente AS empresa,o.nombre_ob AS obra,m.nombre_maquina AS maquina \n"
                + "FROM lnmaqui.chofer c\n"
                + "LEFT JOIN lnmaqui.obracho oc ON c.id_cho = oc.id_choo\n"
                + "LEFT JOIN lnmaqui.obra o ON oc.id_obrac = o.id_obra\n"
                + "LEFT JOIN lnmaqui.cliente cl ON o.id_cliente = cl.id_cliente\n"
                + "LEFT JOIN lnmaqui.maquinacho mc ON mc.id_chom = c.id_cho\n"
                + "LEFT JOIN lnmaqui.maquina m ON m.id_maquina = mc.id_maquinac"); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaChofer.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getInt("run"), rs.getString("nombre"), rs.getString("empresa"), rs.getString("obra"), rs.getString("maquina")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarTablaMaquina(JTable tablaMaquina) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT patente,nombre_maquina,kilometros,horometro,nombre_ob FROM lnmaqui.maquina m FULL JOIN lnmaqui.obramaquina om ON m.id_maquina=om.id_maquinao LEFT JOIN lnmaqui.obra o ON o.id_obra=om.id_obram"); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaMaquina.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("patente"), rs.getString("nombre_maquina"), rs.getString("kilometros"), rs.getString("horometro"), rs.getString("nombre_ob")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarTablaSupervisor(JTable tablaSupervisor) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT rut_superv,nombre_su,nombre_cliente,nombre_ob FROM lnmaqui.supervisor s "
                + "LEFT JOIN lnmaqui.obrasup os ON os.id_superv=s.id_superv "
                + "LEFT JOIN lnmaqui.obra o ON os.id_obra=o.id_obra "
                + "LEFT JOIN lnmaqui.cliente c ON c.id_cliente = o.id_cliente "); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaSupervisor.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getInt("rut_superv"), rs.getString("nombre_su"), rs.getString("nombre_cliente"), rs.getString("nombre_ob")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarTablaobra(JTable tablaObra) {
        String sql = "SELECT nombre_cliente,fono_cliente,correo_cliente,nombre_ob,o.descripcion AS descripcion \n"
                + "FROM lnmaqui.cliente c \n"
                + "LEFT JOIN lnmaqui.obra o ON c.id_cliente = o.id_cliente \n";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaObra.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("nombre_cliente"), rs.getInt("fono_cliente"), rs.getString("correo_cliente"), rs.getString("nombre_ob"), rs.getString("descripcion")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarTablaPrecio(JTable tablaPrecio) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT nombre_maquina,nombre_cliente,nombre_ob,TO_CHAR(precio,'$999G999G999') AS precio "
                + "FROM lnmaqui.maquinaPrecio mp\n"
                + "JOIN lnmaqui.maquina m ON m.id_maquina = mp.id_maquina\n"
                + "JOIN lnmaqui.obra o ON o.id_obra = mp.id_obra \n"
                + "JOIN lnmaqui.cliente c ON c.id_cliente = mp.id_cliente"); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaPrecio.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("nombre_maquina"), rs.getString("nombre_cliente"), rs.getString("nombre_ob"), rs.getString("precio")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarTablaReporte(JTable tablaReporte) {

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT nom_chofer,TO_CHAR(fecha,'DD/MM/YY') AS fecha,nom_empresa,nom_obra,nombre_maquina,nom_supervisor,litros,kmhora,TO_CHAR(horario_manini,'HH:mi')||' a '||TO_CHAR(horario_manfin,'HH:mi') AS horarioMan,TO_CHAR(horario_tarini,'HH:mi')||' a '||TO_CHAR(horario_tarfin,'HH:mi') AS horarioTar,observacion FROM lnmaqui.reporte "); ResultSet rs = stmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) tablaReporte.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("nom_chofer"), rs.getString("fecha"), rs.getString("nom_empresa"), rs.getString("nom_obra"), rs.getString("nombre_maquina"), rs.getString("nom_supervisor"), rs.getString("litros"), rs.getString("kmhora"), rs.getString("horarioMan"), rs.getString("horarioTar"), rs.getString("observacion")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CARGAR COMBOBOX
    public void cargarEmpresasEnComboBox(JComboBox<String> jCBEmpresa) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT nombre_cliente FROM lnmaqui.cliente";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreEmpresa = resultSet.getString("nombre_cliente");
                    comboBoxModel.addElement(nombreEmpresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCBEmpresa.setModel(comboBoxModel);
    }

    public void cargarEmpresaMaq(JComboBox<String> jCBObraMaq) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT nombre_ob FROM lnmaqui.obra ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreEmpresa = resultSet.getString("nombre_ob");
                    comboBoxModel.addElement(nombreEmpresa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCBObraMaq.setModel(comboBoxModel);
    }

    public void cargarObrasEnComboBox(String nombreEmpresa, JComboBox<String> jCBObra) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String queryCliente = "SELECT id_cliente FROM lnmaqui.cliente WHERE nombre_cliente = ?";
            try (PreparedStatement preparedStatementCliente = connection.prepareStatement(queryCliente)) {
                preparedStatementCliente.setString(1, nombreEmpresa);
                try (ResultSet resultSetCliente = preparedStatementCliente.executeQuery()) {
                    if (resultSetCliente.next()) {
                        int idCliente = resultSetCliente.getInt("id_cliente");

                        String queryObras = "SELECT nombre_ob FROM lnmaqui.obra WHERE id_cliente = ?";
                        try (PreparedStatement preparedStatementObras = connection.prepareStatement(queryObras)) {
                            preparedStatementObras.setInt(1, idCliente);
                            try (ResultSet resultSetObras = preparedStatementObras.executeQuery()) {
                                boolean hayObras = false;

                                while (resultSetObras.next()) {
                                    hayObras = true;
                                    comboBoxModel.addElement(resultSetObras.getString("nombre_ob"));
                                }

                                if (!hayObras) {
                                    comboBoxModel.addElement("No hay obras");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCBObra.setModel(comboBoxModel);
    }

    public void cargarMaquina(JComboBox<String> jCBMaquinaPre) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String query = "SELECT nombre_maquina FROM lnmaqui.maquina ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String nombreEmpresa = resultSet.getString("nombre_maquina");
                    comboBoxModel.addElement(nombreEmpresa);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        jCBMaquinaPre.setModel(comboBoxModel);
    }

    public void cargarMaquinasPorObra(String nombreObra, JComboBox<String> jCBMaquina) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String queryCliente = "SELECT id_obra FROM lnmaqui.obra WHERE nombre_ob = ?";
            try (PreparedStatement preparedStatementCliente = connection.prepareStatement(queryCliente)) {
                preparedStatementCliente.setString(1, nombreObra);
                try (ResultSet resultSetCliente = preparedStatementCliente.executeQuery()) {
                    if (resultSetCliente.next()) {
                        int idObra = resultSetCliente.getInt("id_obra");
                        String queryObras = "SELECT nombre_maquina FROM lnmaqui.maquina m JOIN lnmaqui.obramaquina om ON m.id_maquina=om.id_maquinao WHERE id_obram=?";
                        try (PreparedStatement preparedStatementObras = connection.prepareStatement(queryObras)) {
                            preparedStatementObras.setInt(1, idObra);
                            try (ResultSet resultSetObras = preparedStatementObras.executeQuery()) {
                                boolean hayObras = false;

                                while (resultSetObras.next()) {
                                    hayObras = true;
                                    comboBoxModel.addElement(resultSetObras.getString("nombre_maquina"));
                                }

                                if (!hayObras) {
                                    comboBoxModel.addElement("No hay máquinas");
                                } else {
                                    comboBoxModel.addElement("Sin máquina");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCBMaquina.setModel(comboBoxModel);
    }

    public void cargarSuperEnComboBox(JComboBox<String> jCBSuper) {
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

        try (Connection connection = getConnection()) {
            String querySuper = "SELECT nombre_su FROM lnmaqui.supervisor";
            try (PreparedStatement preparedStatementObras = connection.prepareStatement(querySuper)) {
                try (ResultSet resultSetObras = preparedStatementObras.executeQuery()) {
                    while (resultSetObras.next()) {
                        comboBoxModel.addElement(resultSetObras.getString("nombre_su"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        jCBSuper.setModel(comboBoxModel);
    }

    // CARGAR DATOS
    public void cargarTelefonoYCorreo(JTextField textFieldFono, JTextField textFieldCorreo, JComboBox<String> comboEmpresas, Component parentComponent) {
        String nombreEmpresaSeleccionada = (String) comboEmpresas.getSelectedItem();

        if (nombreEmpresaSeleccionada != null) {
            String query = "SELECT fono_cliente, correo_cliente FROM lnmaqui.cliente WHERE nombre_cliente = ?";

            try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombreEmpresaSeleccionada);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String telefono = resultSet.getString("fono_cliente");
                        String correo = resultSet.getString("correo_cliente");

                        textFieldFono.setText(telefono);
                        textFieldCorreo.setText(correo);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(parentComponent, "Error al cargar el teléfono y correo desde la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //BARRA DE BUSQUEDA}
    public void buscarSuper(JTextField buscatodo, JTable tablaSupervisor, JComboBox<String> jCBSupervisor) {
        String sql = "SELECT rut_superv, nombre_su, nombre_cliente, nombre_ob "
                + "FROM lnmaqui.supervisor s "
                + "JOIN lnmaqui.obrasup os ON os.id_superv = s.id_superv "
                + "JOIN lnmaqui.obra o ON o.id_obra = os.id_obra "
                + "JOIN lnmaqui.cliente c ON c.id_cliente = o.id_cliente "
                + "WHERE CAST(s.rut_superv AS VARCHAR) ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR s.nombre_su ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR c.nombre_cliente ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR o.nombre_ob ILIKE '%" + buscatodo.getText().trim() + "%'";

        DefaultTableModel model = (DefaultTableModel) tablaSupervisor.getModel();
        if (!buscatodo.getText().isEmpty()) {
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                model.setRowCount(0);

                while (rs.next()) {
                    Object[] row = {rs.getInt("rut_superv"), rs.getString("nombre_su"), rs.getString("nombre_cliente"), rs.getString("nombre_ob")};
                    model.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            llenarTablaSupervisor(tablaSupervisor);
        }
    }

    public void buscarChofer(JTextField buscatodo, JTable tablaChofer) {
        String sql = "SELECT c.rut_cho AS run, c.nombre_cho AS nombre, cl.nombre_cliente AS empresa, o.nombre_ob AS obra, m.nombre_maquina AS maquina "
                + "FROM lnmaqui.chofer c "
                + "JOIN lnmaqui.obracho oc ON c.id_cho = oc.id_choo "
                + "JOIN lnmaqui.obra o ON oc.id_obrac = o.id_obra "
                + "JOIN lnmaqui.cliente cl ON o.id_cliente = cl.id_cliente "
                + "JOIN lnmaqui.maquinacho mc ON mc.id_chom = c.id_cho "
                + "JOIN lnmaqui.maquina m ON m.id_maquina = mc.id_maquinac "
                + "WHERE CAST(c.rut_cho AS VARCHAR) ILIKE '%" + buscatodo.getText().trim()
                + "%' OR c.nombre_cho ILIKE '" + buscatodo.getText().trim() + "%' OR cl.nombre_cliente ILIKE '%" + buscatodo.getText().trim()
                + "%' OR o.nombre_ob ILIKE '%" + buscatodo.getText().trim() + "%' OR m.nombre_maquina ILIKE '%" + buscatodo.getText().trim() + "%'";

        DefaultTableModel model = (DefaultTableModel) tablaChofer.getModel();
        if (!buscatodo.getText().isEmpty()) {
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

                model.setRowCount(0);

                while (rs.next()) {
                    Object[] row = {rs.getInt("run"), rs.getString("nombre"), rs.getString("empresa"), rs.getString("obra"), rs.getString("maquina")};
                    model.addRow(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            llenarTablaChofer(tablaChofer);
        }

    }

    public void buscarRegistrosObra(JTextField buscatodo, JTable tablaObra) {
        String sql = "SELECT c.nombre_cliente, c.fono_cliente, c.correo_cliente, o.nombre_ob, s.nombre_su, o.descripcion "
                + "FROM lnmaqui.cliente c "
                + "JOIN lnmaqui.obra o ON c.id_cliente = o.id_cliente "
                + "JOIN lnmaqui.obrasup os ON o.id_obra = os.id_obra"
                + "JOIN lnmaqui.supervisor s ON s.id_superv = ossssssssss.id_superv "
                + "WHERE CAST(c.fono_cliente AS VARCHAR) ILIKE '%" + buscatodo.getText().trim()
                + "%' OR c.nombre_cliente ILIKE '" + buscatodo.getText().trim()
                + "%' OR o.nombre_ob ILIKE '%" + buscatodo.getText().trim()
                + "%' OR c.correo_cliente ILIKE '%" + buscatodo.getText().trim()
                + "%' OR s.nombre_su ILIKE '%" + buscatodo.getText().trim()
                + "%' OR o.descripcion ILIKE '%" + buscatodo.getText().trim() + "%'";

        DefaultTableModel model = (DefaultTableModel) tablaObra.getModel();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("nombre_cliente"), rs.getString("fono_cliente"), rs.getString("correo_cliente"),
                    rs.getString("nombre_ob"), rs.getString("nombre_su"), rs.getString("descripcion")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarRegistrosMaquina(JTextField buscatodo, JTable tablaMaquina) {
        String sql = "SELECT m.patente, m.nombre_maquina, m.kilometros, m.horometro, o.nombre_ob "
                + "FROM lnmaqui.maquina m "
                + "JOIN lnmaqui.obramaquina om ON om.id_maquinao = m.id_maquina "
                + "JOIN lnmaqui.obra o ON om.id_obram = o.id_obra "
                + "WHERE CAST(m.kilometros AS VARCHAR) ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR CAST(m.horometro AS VARCHAR) ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR m.nombre_maquina ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR o.nombre_ob ILIKE '%" + buscatodo.getText().trim() + "%' "
                + "OR m.patente ILIKE '%" + buscatodo.getText().trim() + "%'";

        DefaultTableModel model = (DefaultTableModel) tablaMaquina.getModel();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getString("patente"), rs.getString("nombre_maquina"),
                    rs.getString("kilometros"), rs.getString("horometro"), rs.getString("nombre_ob")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarReporte(JTextField buscaReporte, JTable tablaReporte) {
        String sql = "SELECT id_reporte, nom_chofer, TO_CHAR(fecha, 'DD/MM/YY') AS fecha, nom_empresa, "
                + "nom_obra, nombre_maquina, nom_supervisor, litros, kmhora, "
                + "TO_CHAR(horario_manini, 'HH:mi') || ' a ' || TO_CHAR(horario_manfin, 'HH:mi') AS horarioMan, "
                + "TO_CHAR(horario_tarini, 'HH:mi') || ' a ' || TO_CHAR(horario_tarfin, 'HH:mi') AS horarioTar, "
                + "observacion FROM lnmaqui.reporte CROSS JOIN lnmaqui.idrepo id "
                + "WHERE CAST(id.id_reporte AS VARCHAR) ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nom_chofer ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR TO_CHAR(fecha, 'DD/MM/YY') ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nom_empresa ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nom_obra ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nombre_maquina ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nom_supervisor ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR CAST(litros AS VARCHAR) ILIKE '%" + buscaReporte.getText() + "%' "
                + "OR kmhora ILIKE '%" + buscaReporte.getText() + "%'";

        DefaultTableModel model = (DefaultTableModel) tablaReporte.getModel();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getInt("id_reporte"), rs.getString("nom_chofer"), rs.getString("fecha"), rs.getString("nom_empresa"), rs.getString("nom_obra"), rs.getString("nombre_maquina"), rs.getString("nom_supervisor"), rs.getString("litros"), rs.getString("kmhora"), rs.getString("horarioMan"), rs.getString("horarioTar"), rs.getString("observacion")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void buscarPrecio(JTextField buscaReporte, JTable tablaReporte) {
        String sql = "SELECT nombre_maquina,nombre_cliente,nombre_ob,TO_CHAR(precio,'$999G999G999') AS precio "
                + "FROM lnmaqui.maquinaPrecio mp "
                + "JOIN lnmaqui.maquina m ON m.id_maquina = mp.id_maquina "
                + "JOIN lnmaqui.obra o ON o.id_obra = mp.id_maquina "
                + "JOIN lnmaqui.cliente c ON c.id_cliente = mp.id_cliente"
                + "WHERE nombre_maquina ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nombre_cliente ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR nombre_ob ILIKE '%" + buscaReporte.getText().trim() + "%' "
                + "OR ,TO_CHAR(precio,'$999G999G999') ILIKE '%" + buscaReporte.getText().trim() + "%' ";

        DefaultTableModel model = (DefaultTableModel) tablaReporte.getModel();

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {rs.getInt("nombre_maquina"), rs.getString("nombre_cliente"), rs.getString("nombre_ob"), rs.getString("precio")};
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD CHOFER
    public boolean rutValido(int rut) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.chofer WHERE rut_cho = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, rut);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    private int obtenerIdMaquinaPorNombre(String nombreMaquina) throws SQLException {
        String query = "SELECT id_maquina FROM lnmaqui.maquina WHERE nombre_maquina = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombreMaquina);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_maquina");
                } else {
                    return -1;
                }
            }
        }
    }

    private int obtenerIdchofer(int rut) throws SQLException {
        String query = "SELECT id_cho FROM lnmaqui.chofer WHERE rut_cho = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rut);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_cho");
                } else {
                    return -1;
                }
            }
        }
    }

    public void agregarChofer(int rut, String nombre, String obra, String nomMaquina, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int idObra = obtenerIdObraPorNombre(obra);
            int idMaquina = obtenerIdMaquinaPorNombre(nomMaquina);
            if (!rutValido(rut)) {
                JOptionPane.showMessageDialog(parentComponent, "El rut ingresado ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (idObra == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Obra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (maquinaTieneChofer(idMaquina)) {
                JOptionPane.showMessageDialog(parentComponent, "La máquina seleccionada ya tiene un chofer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertChofer = "INSERT INTO lnmaqui.chofer(rut_cho, nombre_cho) VALUES(?,?)";
            try (PreparedStatement psChofer = connection.prepareStatement(insertChofer, Statement.RETURN_GENERATED_KEYS)) {
                psChofer.setInt(1, rut);
                psChofer.setString(2, nombre);

                int affectedRowsChofer = psChofer.executeUpdate();

                if (affectedRowsChofer > 0) {
                    ResultSet generatedKeysChofer = psChofer.getGeneratedKeys();
                    if (generatedKeysChofer.next() & !nomMaquina.isEmpty()) {
                        int idChofer = generatedKeysChofer.getInt(1);

                        String insertObraChoferQuery = "INSERT INTO lnmaqui.obracho(id_choo, id_obrac) VALUES (?, ?)";
                        try (PreparedStatement preparedStatementObraChofer = connection.prepareStatement(insertObraChoferQuery)) {
                            preparedStatementObraChofer.setInt(1, idChofer);
                            preparedStatementObraChofer.setInt(2, idObra);
                            preparedStatementObraChofer.executeUpdate();
                        }
                        if (idMaquina != -1) {
                            String insertMaquinaChoferQuery = "INSERT INTO lnmaqui.maquinacho(id_maquinac, id_chom) VALUES (?, ?)";
                            try (PreparedStatement preparedStatementMaquinaChofer = connection.prepareStatement(insertMaquinaChoferQuery)) {
                                preparedStatementMaquinaChofer.setInt(1, idMaquina);
                                preparedStatementMaquinaChofer.setInt(2, idChofer);
                                preparedStatementMaquinaChofer.executeUpdate();
                            }
                        }
                        JOptionPane.showMessageDialog(parentComponent, "Chofer agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean maquinaTieneChofer(int idMaquina) {
        try (Connection connection = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquinacho WHERE id_maquinac = ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, idMaquina);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean obraChoExiste(int id_cho) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.obraCho WHERE id_choo = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id_cho);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public boolean maquinaChoExiste(int id_cho) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquinacho WHERE id_chom = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id_cho);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public void modificarChofer(String nombre, int run, int runAnterior, String obra, String maquina, String maquinaAnt, Component parentComponent) {
        try (Connection connection = getConnection()) {

            int idCho = obtenerIdchofer(run);
            int idObra = obtenerIdObraPorNombre(obra);
            int idMaquina = obtenerIdMaquinaPorNombre(maquina);
            if (!rutValido(run) && runAnterior != run) {
                JOptionPane.showMessageDialog(parentComponent, "El rut ingresado ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (idObra == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Obra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (maquinaTieneChofer(idMaquina) && !maquinaAnt.equals(maquina)) {
                JOptionPane.showMessageDialog(parentComponent, "La máquina seleccionada ya tiene un chofer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                if (obraChoExiste(idCho)) {
                    String updateObracho = "INSERT INTO lnmaqui.obracho (id_obrac,id_choo)VALUES (?,?)";
                    try (PreparedStatement preparedStatementObracho = connection.prepareStatement(updateObracho)) {
                        preparedStatementObracho.setInt(1, idObra);
                        preparedStatementObracho.setInt(2, idCho);
                        preparedStatementObracho.executeUpdate();

                    }
                } else {
                    String updateObracho = "UPDATE lnmaqui.obracho SET id_obrac = ? WHERE id_choo = ?";
                    try (PreparedStatement preparedStatementObracho = connection.prepareStatement(updateObracho)) {
                        preparedStatementObracho.setInt(1, idObra);
                        preparedStatementObracho.setInt(2, idCho);
                        preparedStatementObracho.executeUpdate();
                    }
                }
                if (maquinaChoExiste(idCho) & idMaquina != -1) {
                    String updateObracho = "INSERT INTO lnmaqui.maquinacho (id_maquinac,id_chom)VALUES (?,?)";
                    try (PreparedStatement preparedStatementObracho = connection.prepareStatement(updateObracho)) {
                        preparedStatementObracho.setInt(1, idMaquina);
                        preparedStatementObracho.setInt(2, idCho);
                        preparedStatementObracho.executeUpdate();
                    }
                } else if (idMaquina != -1) {
                    String updateMaquinacho = "UPDATE lnmaqui.maquinacho SET id_maquinac = ? WHERE id_chom = ?";
                    try (PreparedStatement preparedStatementMaquinacho = connection.prepareStatement(updateMaquinacho)) {
                        preparedStatementMaquinacho.setInt(1, idMaquina);
                        preparedStatementMaquinacho.setInt(2, idCho);
                        preparedStatementMaquinacho.executeUpdate();
                    }
                }

            }

            String updateChofer = "UPDATE lnmaqui.chofer SET rut_cho = ?, nombre_cho = ? WHERE id_cho = ?";
            try (PreparedStatement preparedStatementChofer = connection.prepareStatement(updateChofer)) {
                preparedStatementChofer.setInt(1, run);
                preparedStatementChofer.setString(2, nombre);
                preparedStatementChofer.setInt(3, idCho);
                preparedStatementChofer.executeUpdate();
            }

            JOptionPane.showMessageDialog(parentComponent, "Chofer modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Error al modificar chofer en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void eliminarChofer(String rutChoo) {
        try (Connection connection = getConnection()) {
            if (!rutChoo.isEmpty()) {
                String query = "DELETE FROM lnmaqui.chofer WHERE rut_cho = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    int rutCho = Integer.parseInt(rutChoo);
                    preparedStatement.setInt(1, rutCho);
                    preparedStatement.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CRUD OBRA
    public int obtenerIdSupervisor(String nombreSupervisor) throws SQLException {
        try (Connection connection = getConnection()) {
            String query = "SELECT id_superv FROM lnmaqui.supervisor WHERE nombre_su=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, nombreSupervisor);

                try (ResultSet resultSet = ps.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("id_superv");
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

    public void eliminarObra(String nombreObra) {
        try (Connection connection = getConnection()) {

            if (nombreObra != null) {
                String query = "DELETE FROM lnmaqui.obra WHERE nombre_ob = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, nombreObra);
                    preparedStatement.executeUpdate();
                }

            } else {
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean nombreObraValido(String nombreObra) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.obra WHERE nombre_ob = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nombreObra);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public int obtenerIdCliente(String nombreCliente) throws SQLException {
        String query = "SELECT id_cliente FROM lnmaqui.cliente WHERE nombre_cliente=?";
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, nombreCliente);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_cliente");
                } else {
                    return -1;
                }
            }
        }
    }

    private boolean supervisorTieneObra(int idSupervisor, int idObra) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM lnmaqui.obra WHERE id_superv = ? AND id_obra <> ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idSupervisor);
            preparedStatement.setInt(2, idObra);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                } else {
                    return false;
                }
            }
        }
    }

    public void agregarObra(String nombreObra, String nombreCliente, String nombreSupervisor, String descripcion, Component parentComponent) {
        try (Connection connection = getConnection()) {
            if (!nombreObraValido(nombreObra)) {
                JOptionPane.showMessageDialog(parentComponent, "Nombre de obra inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCliente = obtenerIdCliente(nombreCliente);
            int idSupervisor = obtenerIdSupervisor(nombreSupervisor);

            if (idCliente == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (idSupervisor == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Supervisor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (supervisorTieneObra(idSupervisor, -1)) {
                JOptionPane.showMessageDialog(parentComponent, "El supervisor seleccionado ya tiene obras asociadas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO lnmaqui.obra(nombre_ob, id_cliente, id_superv, descripcion) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nombreObra);
                preparedStatement.setInt(2, idCliente);
                preparedStatement.setInt(3, idSupervisor);

                if (descripcion.isEmpty()) {
                    preparedStatement.setString(4, "Sin descripción");
                } else {
                    preparedStatement.setString(4, descripcion);
                }

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(parentComponent, "Obra agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al agregar obra a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean nombreObraValidoModificar(String nombreObra, String nombreObraAnterior) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.obra WHERE nombre_ob = ? AND nombre_ob != ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nombreObra);
                ps.setString(2, nombreObraAnterior);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private int obtenerIdObraPorNombre(String nombreObra) throws SQLException {
        String query = "SELECT id_obra FROM lnmaqui.obra WHERE nombre_ob = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombreObra);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_obra");
                } else {
                    return -1;
                }
            }
        }
    }

    public void modificarObra(String nombreObraAnterior, String nuevoNombreObra, String nombreCliente, String nombreSupervisor, String nuevaDescripcion, Component parentComponent) {
        try (Connection connection = getConnection()) {

            int idObra = obtenerIdObraPorNombre(nombreObraAnterior);

            if (idObra != -1) {
                int idCliente = obtenerIdCliente(nombreCliente);
                int idSupervisor = obtenerIdSupervisor(nombreSupervisor);

                if (idCliente != -1 && idSupervisor != -1) {

                    if (supervisorTieneObra(idSupervisor, idObra)) {
                        JOptionPane.showMessageDialog(parentComponent, "El supervisor seleccionado tiene obras asociadas. No se puede modificar la obra.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (!nombreObraValidoModificar(nuevoNombreObra, nombreObraAnterior)) {
                        JOptionPane.showMessageDialog(parentComponent, "Nombre de obra inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String sql = "UPDATE lnmaqui.obra SET nombre_ob = ?, id_cliente = ?, id_superv = ?, descripcion = ? WHERE id_obra = ?";

                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setString(1, nuevoNombreObra);
                        preparedStatement.setInt(2, idCliente);
                        preparedStatement.setInt(3, idSupervisor);

                        if (nuevaDescripcion.isEmpty()) {
                            preparedStatement.setNull(4, Types.NULL);
                        } else {
                            preparedStatement.setString(4, nuevaDescripcion);
                        }

                        preparedStatement.setInt(5, idObra);

                        int filasModificadas = preparedStatement.executeUpdate();

                        if (filasModificadas > 0) {
                            JOptionPane.showMessageDialog(parentComponent, "Obra modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(parentComponent, "No se encontró la obra para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(parentComponent, "Cliente o supervisor no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(parentComponent, "No se encontró la obra con el nombre anterior.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Error al modificar obra en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // CRUD MAQUINA
    public void eliminarMaquina(String patente) {
        try (Connection connection = getConnection()) {

            if (patente != null) {
                String query = "DELETE FROM lnmaqui.maquina WHERE patente = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, patente);
                    preparedStatement.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean patenteModificar(String patente, String patenteAnterior) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquina WHERE patente = ? AND patente != ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, patente);
                ps.setString(2, patenteAnterior);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int horometroAnterior(String patente) {
        try (Connection conn = getConnection()) {
            String query = "SELECT horometro FROM lnmaqui.maquina WHERE patente = ? ";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, patente);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int horometro = rs.getInt("horometro");
                        return horometro;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        }
        return 0;
    }

    public int kilometrosAnterior(String patente) {
        try (Connection conn = getConnection()) {
            String query = "SELECT kilometros FROM lnmaqui.maquina WHERE patente = ? ";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, patente);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int kilometros = rs.getInt("kilometros");
                        return kilometros;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;

        }
        return 0;
    }

    public boolean nombreMaqModificar(String nombre, String nombreAnterior) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquina WHERE nombre_maquina = ? AND nombre_maquina != ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nombre);
                ps.setString(2, nombreAnterior);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private int obtenerIdPorPatente(String patente) throws SQLException {
        String query = "SELECT id_maquina FROM lnmaqui.maquina WHERE patente = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_maquina");
                } else {
                    return -1;
                }
            }
        }
    }

    public boolean patenteValido(String patente) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquina WHERE patente = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, patente);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public boolean nombreMaquinaValida(String nombre) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquina WHERE nombre_maquina = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, nombre);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public void agregarMaquina(String obra, String patente, String kilometros, String horometro, String nombre, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int idObra = obtenerIdObraPorNombre(obra);
            if (idObra == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Obra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!patenteValido(patente)) {
                JOptionPane.showMessageDialog(parentComponent, "Esta patente ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!nombreMaquinaValida(nombre)) {
                JOptionPane.showMessageDialog(parentComponent, "Ya existe una maquina con este nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertMaquinaQuery = "INSERT INTO lnmaqui.maquina(patente, nombre_maquina, kilometros, horometro) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatementMaquina = connection.prepareStatement(insertMaquinaQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatementMaquina.setString(1, patente);
                preparedStatementMaquina.setString(2, nombre);

                if (kilometros.isEmpty()) {
                    preparedStatementMaquina.setInt(3, 0);
                } else {
                    int kilometrosS = Integer.parseInt(kilometros);
                    preparedStatementMaquina.setInt(3, kilometrosS);
                }

                if (horometro.isEmpty()) {
                    preparedStatementMaquina.setInt(4, 0);
                } else {
                    int horometroS = Integer.parseInt(horometro);
                    preparedStatementMaquina.setInt(4, horometroS);
                }

                int affectedRows = preparedStatementMaquina.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = preparedStatementMaquina.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idMaquina = generatedKeys.getInt(1);

                        String insertObraMaquinaQuery = "INSERT INTO lnmaqui.obramaquina(id_obram, id_maquinao) VALUES (?, ?)";
                        try (PreparedStatement preparedStatementObraMaquina = connection.prepareStatement(insertObraMaquinaQuery)) {
                            preparedStatementObraMaquina.setInt(1, idObra);
                            preparedStatementObraMaquina.setInt(2, idMaquina);
                            preparedStatementObraMaquina.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(parentComponent, "Maquina agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al agregar la máquina y relación con obra a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean maquinaObraExiste(int id_maquina) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.obramaquina WHERE id_maquinao = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id_maquina);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public void modificarMaquina(String patenteAntigua, String patente, String nombre, String nombreAntiguo, String obra, String kilometros, String horometro, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int idObra = obtenerIdObraPorNombre(obra);
            int idMaquina = obtenerIdPorPatente(patenteAntigua);
            int kilometrosAnt = kilometrosAnterior(patenteAntigua);
            int horoemtroAnt = horometroAnterior(patenteAntigua);

            if (idObra == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Obra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nombreMaquinaValida(nombre) && !nombreAntiguo.equals(nombre)) {
                JOptionPane.showMessageDialog(parentComponent, "Ya existe una máquina con este nombre.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!patenteModificar(patente, patenteAntigua) && !patenteAntigua.equals(patente)) {
                JOptionPane.showMessageDialog(parentComponent, "Patente inválida o ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!nombreMaqModificar(nombre, nombreAntiguo)) {
                JOptionPane.showMessageDialog(parentComponent, "Nombre de máquina inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String sqlMaquina = "UPDATE lnmaqui.maquina SET patente = ?,nombre_maquina = ?, kilometros = ?, horometro = ? WHERE id_maquina = ?";
            try (PreparedStatement preparedStatementMaquina = connection.prepareStatement(sqlMaquina)) {
                preparedStatementMaquina.setString(1, patente);
                preparedStatementMaquina.setString(2, nombre);

                if (kilometros.isEmpty()) {
                    preparedStatementMaquina.setInt(3, kilometrosAnt);
                } else {
                    int kilometrosS = Integer.parseInt(kilometros);
                    preparedStatementMaquina.setInt(3, kilometrosS);
                }

                if (horometro.isEmpty()) {
                    preparedStatementMaquina.setInt(4, horoemtroAnt);
                } else {
                    int horometroS = Integer.parseInt(horometro);
                    preparedStatementMaquina.setInt(4, horometroS);
                }

                preparedStatementMaquina.setInt(5, idMaquina);

                int filasModificadasMaquina = preparedStatementMaquina.executeUpdate();
                if (maquinaObraExiste(idMaquina)) {
                    String sqlObraMaquina = "INSERT INTO lnmaqui.obramaquina(id_obram,id_maquinao) VALUES (?,?) ";
                    try (PreparedStatement ps = connection.prepareStatement(sqlObraMaquina)) {
                        ps.setInt(1, idObra);
                        ps.setInt(2, idMaquina);
                        ps.executeUpdate();
                    }

                } else {
                    String sqlObraMaquina = "UPDATE lnmaqui.obramaquina SET id_obram = ? WHERE id_maquinao = ?";

                    try (PreparedStatement preparedStatementObraMaquina = connection.prepareStatement(sqlObraMaquina)) {
                        preparedStatementObraMaquina.setInt(1, idObra);
                        preparedStatementObraMaquina.setInt(2, idMaquina);

                        int filasModificadasObraMaquina = preparedStatementObraMaquina.executeUpdate();

                        if (filasModificadasMaquina > 0 && filasModificadasObraMaquina > 0) {
                            JOptionPane.showMessageDialog(parentComponent, "Máquina modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(parentComponent, "No se encontró la máquina para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentComponent, "Error al modificar la máquina y relación con obra en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //CRUD PRECIO
    public void eliminarPrecio(String nomMaquina) {
        try (Connection connection = getConnection()) {
            int idMaquina = obtenerIdMaquinaPorNombre(nomMaquina);
            if (idMaquina != 1) {
                String query = "DELETE FROM lnmaqui.maquinaprecio WHERE id_maquina = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, idMaquina);
                    preparedStatement.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean maquinaNoPrecio(int id_maquina) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.maquinaprecio WHERE id_maquina = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id_maquina);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public void agregarPrecio(String obra, String maquina, String cliente, String precio, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int obraI = obtenerIdObraPorNombre(obra);
            int maquinaI = obtenerIdMaquinaPorNombre(maquina);
            int clienteI = obtenerIdCliente(cliente);
            int precioI = Integer.parseInt(precio);
            if (!maquinaNoPrecio(maquinaI)) {
                JOptionPane.showMessageDialog(parentComponent, "Esta maquina ya esta en una obra.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insert = "INSERT INTO lnmaqui.maquinaprecio(id_maquina,id_cliente,id_obra,precio) VALUES(?,?,?,?)";

            try (PreparedStatement ps = connection.prepareStatement(insert)) {
                ps.setInt(1, maquinaI);
                ps.setInt(2, clienteI);
                ps.setInt(3, obraI);
                ps.setInt(4, precioI);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(parentComponent, "Maquina agregada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al agregar la maquina a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);

            e.printStackTrace();

        }
    }

    public void modificarPrecio(String obra, String obraAnt, String maquina, String cliente, String precio, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int obraI = obtenerIdObraPorNombre(obra);
            int maquinaI = obtenerIdMaquinaPorNombre(maquina);
            int clienteI = obtenerIdCliente(cliente);
            int precioI = Integer.parseInt(precio);

            if (!obra.equals(obraAnt)) {
                try (PreparedStatement ps = connection.prepareStatement("UPDATE lnmaqui.obramaquina SET id_obram = ? WHERE id_maquinao = ?")) {
                    ps.setInt(1, obraI);
                    ps.setInt(2, maquinaI);
                    ps.executeUpdate();
                }
            }

            String update = "UPDATE lnmaqui.maquinaprecio SET id_cliente = ?,id_obra = ?,precio = ? WHERE id_maquina= ?";
            try (PreparedStatement ps = connection.prepareStatement(update)) {
                ps.setInt(1, clienteI);
                ps.setInt(2, obraI);
                ps.setInt(3, precioI);
                ps.setInt(4, maquinaI);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(parentComponent, "Precio modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al modificar el precio en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    //CRUD SUPERVISOR
    public boolean supervisorValido(int rut) {
        try (Connection conn = getConnection()) {
            String query = "SELECT COUNT(*) AS count FROM lnmaqui.supervisor WHERE rut_superv = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, rut);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt("count");
                        return count == 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
        return false;
    }

    public void agregarSupervisor(String obra, String empresa, String rut, String nombre, Component parentComponent) {
        try (Connection connection = getConnection()) {

            int rutN = Integer.parseInt(rut);
            int idObra = obtenerIdObraPorNombre(obra);

            if (idObra == -1) {
                JOptionPane.showMessageDialog(parentComponent, "Obra no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!supervisorValido(rutN)) {
                JOptionPane.showMessageDialog(parentComponent, "Esta supervisor ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertSuperv = "INSERT INTO lnmaqui.supervisor(rut_superv,nombre_su) VALUES(?,?)";

            try (PreparedStatement preparedStatementMaquina = connection.prepareStatement(insertSuperv, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatementMaquina.setInt(1, rutN);
                preparedStatementMaquina.setString(2, nombre);

                int affectedRows = preparedStatementMaquina.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = preparedStatementMaquina.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int idSuperv = generatedKeys.getInt(1);

                        String moficarObra = "INSERT INTO lnmaqui.obrasup VALUES(?,?)";
                        try (PreparedStatement preparedStatementObraMaquina = connection.prepareStatement(moficarObra)) {
                            preparedStatementObraMaquina.setInt(1, idSuperv);
                            preparedStatementObraMaquina.setInt(2, idObra);
                            preparedStatementObraMaquina.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(parentComponent, "Supervisor agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al agregar el supervisor.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void eliminarSupervisor(String rutSup) {
        try (Connection connection = getConnection()) {
            if (!rutSup.isEmpty()) {
                String query = "DELETE FROM lnmaqui.supervisor WHERE rut_superv = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    int rutSupp = Integer.parseInt(rutSup);
                    preparedStatement.setInt(1, rutSupp);
                    preparedStatement.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int obtenerIdsup(int rut) throws SQLException {
        String query = "SELECT id_superv FROM lnmaqui.supervisor WHERE rut_superv = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, rut);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id_superv");
                } else {
                    return -1;
                }
            }
        }
    }



    public void modificarSupervisor(String obra, String obraAnt, String nombreSup, int rutSupAnt, int rutSup, Component parentComponent) {
        try (Connection connection = getConnection()) {
            int obraI = obtenerIdObraPorNombre(obra);
            int SupI = obtenerIdsup(rutSupAnt);
            
            if (!supervisorValido(rutSup) && rutSupAnt != rutSup) {
                JOptionPane.showMessageDialog(parentComponent, "Run ingresado ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!obra.equals(obraAnt)) {
                try (PreparedStatement ps = connection.prepareStatement("UPDATE lnmaqui.obrasup SET id_obra = ? WHERE id_superv = ?")) {
                    ps.setInt(1, obraI);
                    ps.setInt(2, SupI);
                    ps.executeUpdate();
                }
            }

            String update = "UPDATE lnmaqui.supervisor SET rut_superv = ?,nombre_su = ? WHERE id_superv= ?";
            try (PreparedStatement ps = connection.prepareStatement(update)) {
                ps.setInt(1, rutSup);
                ps.setString(2, nombreSup);
                ps.setInt(3, SupI);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(parentComponent, "Supervisor modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(parentComponent, "Error al modificar el supervisor en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    
}
