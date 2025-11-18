package vista;

import controlador.EstudianteDAO;
import controlador.ResultadoExamenDAO;
import modelo.Estudiante;
import modelo.ResultadoExamen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MenuCoordinacion extends JFrame {
    private JTabbedPane tabbedPane;
    private EstudianteDAO estudianteDAO;
    private ResultadoExamenDAO resultadoDAO;
    
    // Componentes CRUD Estudiante
    private JTextField txtCodigo, txtNombre, txtPrograma, txtSemestre;
    private JTable tablaEstudiantes;
    private DefaultTableModel modeloTablaEstudiantes;
    
    // Componentes Informes
    private JComboBox<String> cmbTipoInforme;
    private JTextArea txtAreaInforme;
    
    public MenuCoordinacion() {
        estudianteDAO = new EstudianteDAO();
        resultadoDAO = new ResultadoExamenDAO();
        initComponents();
        cargarEstudiantes();
    }
    
    private void initComponents() {
        setTitle("Panel de Coordinación - Saber Pro UTS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel superior
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 102, 204));
        JLabel lblTitulo = new JLabel("PANEL DE COORDINACIÓN");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Tab 1: CRUD Estudiantes
        JPanel panelCrud = crearPanelCRUD();
        tabbedPane.addTab("  Gestión de Estudiantes  ", panelCrud);
        
        // Tab 2: Informes
        JPanel panelInformes = crearPanelInformes();
        tabbedPane.addTab("  Informes  ", panelInformes);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel inferior con botón volver
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        JButton btnVolver = new JButton("Volver al Menú Principal");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setPreferredSize(new Dimension(250, 40));
        btnVolver.setBackground(new Color(128, 128, 128));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> volverMenuPrincipal());
        panelInferior.add(btnVolver);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private JPanel crearPanelCRUD() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        // Panel de formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
            "Datos del Estudiante",
            0, 0, new Font("Arial", Font.BOLD, 14)));
        panelForm.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Código
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 13));
        panelForm.add(lblCodigo, gbc);
        
        gbc.gridx = 1;
        txtCodigo = new JTextField(20);
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 13));
        panelForm.add(txtCodigo, gbc);
        
        // Nombre
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
        panelForm.add(lblNombre, gbc);
        
        gbc.gridx = 1;
        txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 13));
        panelForm.add(txtNombre, gbc);
        
        // Programa
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lblPrograma = new JLabel("Programa:");
        lblPrograma.setFont(new Font("Arial", Font.BOLD, 13));
        panelForm.add(lblPrograma, gbc);
        
        gbc.gridx = 1;
        txtPrograma = new JTextField(20);
        txtPrograma.setFont(new Font("Arial", Font.PLAIN, 13));
        panelForm.add(txtPrograma, gbc);
        
        // Semestre
        gbc.gridx = 0; gbc.gridy = 3;
        JLabel lblSemestre = new JLabel("Semestre:");
        lblSemestre.setFont(new Font("Arial", Font.BOLD, 13));
        panelForm.add(lblSemestre, gbc);
        
        gbc.gridx = 1;
        txtSemestre = new JTextField(20);
        txtSemestre.setFont(new Font("Arial", Font.PLAIN, 13));
        panelForm.add(txtSemestre, gbc);
        
        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(Color.WHITE);
        
        JButton btnAgregar = crearBoton("Agregar", new Color(0, 153, 51));
        JButton btnActualizar = crearBoton("Actualizar", new Color(0, 102, 204));
        JButton btnEliminar = crearBoton("Eliminar", new Color(204, 0, 0));
        JButton btnLimpiar = crearBoton("Limpiar", new Color(128, 128, 128));
        
        btnAgregar.addActionListener(e -> agregarEstudiante());
        btnActualizar.addActionListener(e -> actualizarEstudiante());
        btnEliminar.addActionListener(e -> eliminarEstudiante());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        
        JPanel panelSuperior = new JPanel(new BorderLayout(10, 10));
        panelSuperior.setBackground(Color.WHITE);
        panelSuperior.add(panelForm, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);
        
        // Tabla
        String[] columnas = {"Código", "Nombre", "Programa", "Semestre"};
        modeloTablaEstudiantes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaEstudiantes = new JTable(modeloTablaEstudiantes);
        tablaEstudiantes.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaEstudiantes.setRowHeight(25);
        tablaEstudiantes.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaEstudiantes.getTableHeader().setBackground(new Color(0, 102, 204));
        tablaEstudiantes.getTableHeader().setForeground(Color.WHITE);
        
        tablaEstudiantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaEstudiantes.getSelectedRow() != -1) {
                cargarDatosSeleccionados();
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tablaEstudiantes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Estudiantes"));
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelInformes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        
        // Panel superior
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelSuperior.setBackground(Color.WHITE);
        
        JLabel lblTipo = new JLabel("Tipo de Informe:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 13));
        panelSuperior.add(lblTipo);
        
        cmbTipoInforme = new JComboBox<>(new String[]{
            "Informe Básico - Todos los Estudiantes",
            "Informe Detallado - Por Estudiante",
            "Estudiantes que deben Reprogramar"
        });
        cmbTipoInforme.setFont(new Font("Arial", Font.PLAIN, 13));
        cmbTipoInforme.setPreferredSize(new Dimension(300, 30));
        panelSuperior.add(cmbTipoInforme);
        
        JButton btnGenerar = new JButton("Generar Informe");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGenerar.setPreferredSize(new Dimension(150, 35));
        btnGenerar.setBackground(new Color(0, 102, 204));
        btnGenerar.setForeground(Color.BLACK);
        btnGenerar.setFocusPainted(false);
        btnGenerar.addActionListener(e -> generarInforme());
        panelSuperior.add(btnGenerar);
        
        panel.add(panelSuperior, BorderLayout.NORTH);
        
        // Área de texto para el informe
        txtAreaInforme = new JTextArea();
        txtAreaInforme.setEditable(false);
        txtAreaInforme.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaInforme.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(txtAreaInforme);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultado del Informe"));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 13));
        boton.setPreferredSize(new Dimension(120, 35));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        return boton;
    }
    
    private void agregarEstudiante() {
        if (validarCampos()) {
            Estudiante est = new Estudiante();
            est.setCodigo(txtCodigo.getText().trim());
            est.setNombres(txtNombre.getText().trim());
            est.setPrograma(txtPrograma.getText().trim());
            est.setSemestre(Integer.parseInt(txtSemestre.getText().trim()));
            
            if (estudianteDAO.crear(est)) {
                JOptionPane.showMessageDialog(this, 
                    "Estudiante agregado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al agregar estudiante", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void actualizarEstudiante() {
        if (tablaEstudiantes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un estudiante de la tabla", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (validarCampos()) {
            Estudiante est = new Estudiante();
            est.setCodigo(txtCodigo.getText().trim());
            est.setNombres(txtNombre.getText().trim());
            est.setPrograma(txtPrograma.getText().trim());
            est.setSemestre(Integer.parseInt(txtSemestre.getText().trim()));
            
            if (estudianteDAO.actualizar(est)) {
                JOptionPane.showMessageDialog(this, 
                    "Estudiante actualizado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar estudiante", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
   private void eliminarEstudiante() {
    if (tablaEstudiantes.getSelectedRow() == -1) {
        JOptionPane.showMessageDialog(this, 
            "Seleccione un estudiante de la tabla", 
            "Advertencia", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int confirmacion = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de eliminar este estudiante?\nEsta acción no se puede deshacer.", 
        "Confirmar Eliminación", 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        String codigo = txtCodigo.getText().trim();
        
        // Primero buscar el estudiante para obtener su ID
        Estudiante est = estudianteDAO.buscarPorCodigo(codigo);
        
        if (est != null) {
            // Eliminar usando el ID del estudiante
            if (estudianteDAO.eliminar(est.getId())) {
                JOptionPane.showMessageDialog(this, 
                    "Estudiante eliminado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                cargarEstudiantes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Error al eliminar estudiante", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                "No se encontró el estudiante", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    private void cargarEstudiantes() {
        modeloTablaEstudiantes.setRowCount(0);
        List<Estudiante> estudiantes = estudianteDAO.listarTodos();
        for (Estudiante est : estudiantes) {
            modeloTablaEstudiantes.addRow(new Object[]{
                est.getCodigo(),
                est.getNombres(),
                est.getPrograma(),
                est.getSemestre()
            });
        }
    }
    
    private void cargarDatosSeleccionados() {
        int fila = tablaEstudiantes.getSelectedRow();
        txtCodigo.setText(tablaEstudiantes.getValueAt(fila, 0).toString());
        txtNombre.setText(tablaEstudiantes.getValueAt(fila, 1).toString());
        txtPrograma.setText(tablaEstudiantes.getValueAt(fila, 2).toString());
        txtSemestre.setText(tablaEstudiantes.getValueAt(fila, 3).toString());
        txtCodigo.setEditable(false);
    }
    
    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrograma.setText("");
        txtSemestre.setText("");
        txtCodigo.setEditable(true);
        tablaEstudiantes.clearSelection();
    }
    
    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() || 
            txtNombre.getText().trim().isEmpty() ||
            txtPrograma.getText().trim().isEmpty() ||
            txtSemestre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios", 
                "Validación", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        try {
            int semestre = Integer.parseInt(txtSemestre.getText().trim());
            if (semestre <= 0) {
                JOptionPane.showMessageDialog(this, 
                    "El semestre debe ser un número positivo", 
                    "Validación", 
                    JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El semestre debe ser un número válido", 
                "Validación", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private void generarInforme() {
        int tipo = cmbTipoInforme.getSelectedIndex();
        StringBuilder informe = new StringBuilder();
        
        switch (tipo) {
            case 0: // Informe Básico
                informe.append("═══════════════════════════════════════════════════════════════\n");
                informe.append("      INFORME BÁSICO - TODOS LOS ESTUDIANTES\n");
                informe.append("═══════════════════════════════════════════════════════════════\n\n");
                
                List<Estudiante> estudiantes = estudianteDAO.listarTodos();
                if (estudiantes.isEmpty()) {
                    informe.append("No hay estudiantes registrados en el sistema.\n");
                } else {
                    for (Estudiante est : estudiantes) {
                        ResultadoExamen resultado = resultadoDAO.obtenerPorCodigoEstudiante(est.getCodigo());
                        informe.append(String.format("Código:    %s\n", est.getCodigo()));
                        informe.append(String.format("Nombre:    %s\n", est.getNombres()));
                        informe.append(String.format("Programa:  %s\n", est.getPrograma()));
                        informe.append(String.format("Semestre:  %d\n", est.getSemestre()));
                        
                        if (resultado != null) {
                            informe.append(String.format("Puntaje:   %.2f / 300\n", resultado.getPuntajeTotal()));
                            informe.append(String.format("Estado:    %s\n", resultado.getEstado()));
                        } else {
                            informe.append("Estado:    Sin resultados registrados\n");
                        }
                        informe.append("───────────────────────────────────────────────────────────────\n");
                    }
                }
                break;
                
            case 1: // Informe Detallado
                String codigo = JOptionPane.showInputDialog(this, 
                    "Ingrese el código del estudiante:",
                    "Código de Estudiante",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (codigo != null && !codigo.trim().isEmpty()) {
                    Estudiante est = estudianteDAO.buscarPorCodigo(codigo.trim());
                    if (est != null) {
                        ResultadoExamen resultado = resultadoDAO.obtenerPorCodigoEstudiante(codigo.trim());
                        
                        informe.append("═══════════════════════════════════════════════════════════════\n");
                        informe.append("                 INFORME DETALLADO\n");
                        informe.append("═══════════════════════════════════════════════════════════════\n\n");
                        
                        informe.append("DATOS DEL ESTUDIANTE:\n");
                        informe.append(String.format("Código:    %s\n", est.getCodigo()));
                        informe.append(String.format("Nombre:    %s\n", est.getNombres()));
                        informe.append(String.format("Programa:  %s\n", est.getPrograma()));
                        informe.append(String.format("Semestre:  %d\n\n", est.getSemestre()));
                        
                        if (resultado != null) {
                            double puntaje = resultado.getPuntajeTotal();
                            informe.append("RESULTADOS SABER PRO:\n");
                            informe.append(String.format("Puntaje Total: %.2f / 300 puntos\n", puntaje));
                            informe.append(String.format("Estado:        %s\n\n", resultado.getEstado()));
                            
                            informe.append("INCENTIVOS UTS (Acuerdo 01-009):\n");
                            if (puntaje >= 180 && puntaje <= 210) {
                                informe.append("✓ Exoneración del informe final de trabajo de grado\n");
                                informe.append("✓ Nota asignada: 4.5\n");
                            } else if (puntaje >= 211 && puntaje <= 240) {
                                informe.append("✓ Exoneración del informe final de trabajo de grado\n");
                                informe.append("✓ Nota asignada: 4.7\n");
                                informe.append("✓ Beca del 50% en derechos de grado\n");
                            } else if (puntaje > 241) {
                                informe.append("✓ Exoneración del informe final de trabajo de grado\n");
                                informe.append("✓ Nota asignada: 5.0\n");
                                informe.append("✓ Beca del 100% en derechos de grado\n");
                            } else if (puntaje < 80) {
                                informe.append("⚠ El estudiante debe reprogramar el examen\n");
                            } else {
                                informe.append("- No aplica a incentivos especiales\n");
                            }
                        } else {
                            informe.append("RESULTADOS: Sin resultados registrados\n");
                        }
                    } else {
                        informe.append("═══════════════════════════════════════════════════════════════\n");
                        informe.append("ERROR: Estudiante no encontrado\n");
                        informe.append("═══════════════════════════════════════════════════════════════\n");
                    }
                }
                break;
                
            case 2: // Deben Reprogramar
                informe.append("═══════════════════════════════════════════════════════════════\n");
                informe.append("     ESTUDIANTES QUE DEBEN REPROGRAMAR EXAMEN\n");
                informe.append("          (Puntaje inferior a 80 puntos)\n");
                informe.append("═══════════════════════════════════════════════════════════════\n\n");
                
                List<Estudiante> todos = estudianteDAO.listarTodos();
                int count = 0;
                
                for (Estudiante est : todos) {
                    ResultadoExamen resultado = resultadoDAO.obtenerPorCodigoEstudiante(est.getCodigo());
                    if (resultado != null && resultado.getPuntajeTotal() < 80) {
                        count++;
                        informe.append(String.format("%d. Código: %s\n", count, est.getCodigo()));
                        informe.append(String.format("   Nombre: %s\n", est.getNombres()));
                        informe.append(String.format("   Programa: %s\n", est.getPrograma()));
                        informe.append(String.format("   Puntaje: %.2f\n", resultado.getPuntajeTotal()));
                        informe.append("   ───────────────────────────────────────────────────────────\n");
                    }
                }
                
                if (count == 0) {
                    informe.append("✓ No hay estudiantes que deban reprogramar el examen\n");
                } else {
                    informe.append(String.format("\nTotal de estudiantes: %d\n", count));
                }
                break;
        }
        
        txtAreaInforme.setText(informe.toString());
        txtAreaInforme.setCaretPosition(0);
    }
    
    private void volverMenuPrincipal() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Desea volver al menú principal?",
            "Confirmar",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        }
    }
}