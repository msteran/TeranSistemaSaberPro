package vista;

import controlador.ResultadoExamenDAO;
import modelo.Estudiante;
import modelo.ResultadoExamen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;

public class MenuEstudiante extends JFrame {
    private Estudiante estudiante;
    private ResultadoExamenDAO resultadoDAO;
    private JTabbedPane tabbedPane;
    
    // Componentes
    private JLabel lblInfoEstudiante;
    private JTextArea txtAreaResultado;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;
    private JDateChooser dateChooser;
    
    public MenuEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.resultadoDAO = new ResultadoExamenDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Portal del Estudiante - Saber Pro UTS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel superior con info del estudiante
        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(new Color(0, 153, 51));
        panelInfo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        JLabel lblTitulo = new JLabel("PORTAL DEL ESTUDIANTE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelInfo.add(lblTitulo, BorderLayout.NORTH);
        
        lblInfoEstudiante = new JLabel();
        lblInfoEstudiante.setFont(new Font("Arial", Font.BOLD, 14));
        lblInfoEstudiante.setForeground(Color.WHITE);
        lblInfoEstudiante.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfoEstudiante.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        actualizarInfoEstudiante();
        panelInfo.add(lblInfoEstudiante, BorderLayout.CENTER);
        
        add(panelInfo, BorderLayout.NORTH);
        
        // Pestañas
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));
        tabbedPane.setForeground(Color.BLACK);
        
        
        // Tab 1: Mi Resultado
        JPanel panelMiResultado = crearPanelMiResultado();
        tabbedPane.addTab("  Mi Resultado  ", panelMiResultado);
        
        // Tab 2: Todos los Resultados
        JPanel panelTodosResultados = crearPanelTodosResultados();
        tabbedPane.addTab("  Todos los Resultados  ", panelTodosResultados);
        
        // Tab 3: Reprogramar Examen
        JPanel panelReprogramar = crearPanelReprogramar();
        tabbedPane.addTab("  Reprogramar Examen  ", panelReprogramar);
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel inferior con botón cerrar sesión
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelInferior.setBackground(new Color(240, 240, 240));
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrarSesion.setPreferredSize(new Dimension(200, 40));
        btnCerrarSesion.setBackground(new Color(204, 0, 0));
        btnCerrarSesion.setForeground(Color.BLACK);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        panelInferior.add(btnCerrarSesion);
        
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void actualizarInfoEstudiante() {
        String info = String.format("Bienvenido: %s | Código: %s | Programa: %s | Semestre: %d", 
           estudiante.getNombreCompleto(), 
            estudiante.getCodigo(),
            estudiante.getPrograma(),
            estudiante.getSemestre());
        lblInfoEstudiante.setText(info);
    }
    
    private JPanel crearPanelMiResultado() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(Color.WHITE);
        
        JButton btnConsultar = new JButton("Consultar Mi Resultado");
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConsultar.setPreferredSize(new Dimension(220, 45));
        btnConsultar.setBackground(new Color(0, 102, 204));
        btnConsultar.setForeground(Color.BLACK);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addActionListener(e -> consultarMiResultado());
        panelBoton.add(btnConsultar);
        
        panel.add(panelBoton, BorderLayout.NORTH);
        
        txtAreaResultado = new JTextArea();
        txtAreaResultado.setEditable(false);
        txtAreaResultado.setFont(new Font("Monospaced", Font.PLAIN, 13));
        txtAreaResultado.setMargin(new Insets(15, 15, 15, 15));
        JScrollPane scrollPane = new JScrollPane(txtAreaResultado);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
            "Resultado del Examen",
            0, 0, new Font("Arial", Font.BOLD, 13)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelTodosResultados() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);
        
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(Color.WHITE);
        
        JButton btnCargar = new JButton("Cargar Todos los Resultados");
        btnCargar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCargar.setPreferredSize(new Dimension(240, 45));
        btnCargar.setBackground(new Color(0, 102, 204));
        btnCargar.setForeground(Color.BLACK);
        btnCargar.setFocusPainted(false);
        btnCargar.addActionListener(e -> cargarTodosResultados());
        panelBoton.add(btnCargar);
        
        panel.add(panelBoton, BorderLayout.NORTH);
        
        String[] columnas = {"Código", "Nombre", "Programa", "Puntaje", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaResultados.setRowHeight(28);
        tablaResultados.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tablaResultados.getTableHeader().setBackground(new Color(0, 102, 204));
        tablaResultados.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(tablaResultados);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Resultados de Todos los Estudiantes"));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelReprogramar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel lblTitulo = new JLabel("Reprogramar Examen Saber Pro");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(204, 0, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(lblTitulo, gbc);
        
        // Información
        JLabel lblInfo = new JLabel("<html><div style='text-align: center; width: 500px;'>" +
            "Si obtuviste un puntaje inferior a 80 puntos, debes reprogramar el examen.<br><br>" +
            "<b>Selecciona una nueva fecha a continuación:</b></div></html>");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panel.add(lblInfo, gbc);
        
        // Etiqueta fecha
        JLabel lblFecha = new JLabel("Nueva Fecha del Examen:");
        lblFecha.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(lblFecha, gbc);
        
        // Selector de fecha
        dateChooser = new JDateChooser();
        dateChooser.setMinSelectableDate(new Date());
        dateChooser.setFont(new Font("Arial", Font.PLAIN, 13));
        dateChooser.setPreferredSize(new Dimension(250, 35));
        dateChooser.setDateFormatString("dd/MM/yyyy");
        gbc.gridx = 1;
        panel.add(dateChooser, gbc);
        
        // Botón confirmar
        JButton btnReprogramar = new JButton("Confirmar Reprogramación");
        btnReprogramar.setFont(new Font("Arial", Font.BOLD, 14));
        btnReprogramar.setPreferredSize(new Dimension(280, 45));
        btnReprogramar.setBackground(new Color(255, 140, 0));
        btnReprogramar.setForeground(Color.BLACK);
        btnReprogramar.setFocusPainted(false);
        btnReprogramar.addActionListener(e -> reprogramarExamen());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnReprogramar, gbc);
        
        // Panel de información adicional
        JPanel panelInfoAdicional = new JPanel(new BorderLayout());
        panelInfoAdicional.setBackground(new Color(255, 255, 220));
        panelInfoAdicional.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(255, 140, 0), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)));
        
        JTextArea txtInfo = new JTextArea(
            "⚠ IMPORTANTE:\n\n" +
            "• Solo puedes reprogramar si tu puntaje fue inferior a 80 puntos.\n\n" +
            "• La nueva fecha debe ser posterior a la fecha actual.\n\n" +
            "• Recibirás un correo de confirmación con los detalles.\n\n" +
            "• Recuerda prepararte adecuadamente para tu próximo examen.\n\n" +
            "• La preparación es clave para obtener un mejor resultado."
        );
        txtInfo.setEditable(false);
        txtInfo.setBackground(new Color(255, 255, 220));
        txtInfo.setFont(new Font("Arial", Font.PLAIN, 13));
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        panelInfoAdicional.add(txtInfo, BorderLayout.CENTER);
        
        gbc.gridy = 4;
        gbc.insets = new Insets(25, 15, 15, 15);
        panel.add(panelInfoAdicional, gbc);
        
        return panel;
    }
    
    private void consultarMiResultado() {
        ResultadoExamen resultado = resultadoDAO.obtenerPorCodigoEstudiante(estudiante.getCodigo());
        
        StringBuilder texto = new StringBuilder();
        texto.append("═══════════════════════════════════════════════════════════════\n");
        texto.append("          RESULTADO EXAMEN SABER PRO - UTS\n");
        texto.append("═══════════════════════════════════════════════════════════════\n\n");
        
        texto.append("INFORMACIÓN DEL ESTUDIANTE:\n\n");
        texto.append(String.format("Código:    %s\n", estudiante.getCodigo()));
        texto.append(String.format("Nombre:    %s\n",estudiante.getNombreCompleto()));
        texto.append(String.format("Programa:  %s\n", estudiante.getPrograma()));
        texto.append(String.format("Semestre:  %d\n\n", estudiante.getSemestre()));
        
        if (resultado != null) {
            texto.append("───────────────────────────────────────────────────────────────\n");
            texto.append("                    RESULTADOS\n");
            texto.append("───────────────────────────────────────────────────────────────\n\n");
            
            double puntaje = resultado.getPuntajeTotal();
            texto.append(String.format("Puntaje Total:  %.2f / 300 puntos\n", puntaje));
            texto.append(String.format("Estado:         %s\n\n", resultado.getEstado()));
            
            // Evaluación según puntaje
            if (puntaje < 80) {
                texto.append("═══════════════════════════════════════════════════════════════\n");
                texto.append("⚠ ATENCIÓN: Puntaje inferior a 80 puntos\n");
                texto.append("═══════════════════════════════════════════════════════════════\n\n");
                texto.append("➤ DEBES REPROGRAMAR EL EXAMEN\n\n");
                texto.append("Acción requerida:\n");
                texto.append("• Ve a la pestaña 'Reprogramar Examen'\n");
                texto.append("• Selecciona una nueva fecha\n");
                texto.append("• Prepárate mejor para tu próximo intento\n\n");
            } else if (puntaje >= 80 && puntaje < 180) {
                texto.append("═══════════════════════════════════════════════════════════════\n");
                texto.append("✓ HAS APROBADO EL EXAMEN\n");
                texto.append("═══════════════════════════════════════════════════════════════\n\n");
                texto.append("Felicitaciones por aprobar el examen Saber Pro.\n\n");
            } else if (puntaje >= 180 && puntaje <= 210) {
                texto.append("═══════════════════════════════════════════════════════════════\n");
                texto.append("★ ¡FELICITACIONES! PUNTAJE SOBRESALIENTE\n");
                texto.append("═══════════════════════════════════════════════════════════════\n\n");
                texto.append("INCENTIVOS UTS (Acuerdo 01-009 del 22 de abril de 2024):\n\n");
                texto.append("✓ Exoneración del informe final de trabajo de grado\n");
                texto.append("✓ Exoneración de Seminario de grado IV\n");
                texto.append("✓ Nota asignada: 4.5\n\n");
                texto.append("Requisitos:\n");
                texto.append("• Haber presentado y aprobado la propuesta de trabajo de grado\n");
                texto.append("• Vigencia: 1 año desde publicación de resultados ICFES\n\n");
            } else if (puntaje >= 211 && puntaje <= 240) {
                texto.append("═══════════════════════════════════════════════════════════════\n");
                texto.append("★★ ¡EXCELENTE! PUNTAJE MUY SOBRESALIENTE\n");
                texto.append("═══════════════════════════════════════════════════════════════\n\n");
                texto.append("INCENTIVOS UTS (Acuerdo 01-009 del 22 de abril de 2024):\n\n");
                texto.append("✓ Exoneración del informe final de trabajo de grado\n");
                texto.append("✓ Exoneración de Seminario de grado IV\n");
                texto.append("✓ Nota asignada: 4.7\n");
                texto.append("✓ Beca del 50% en derechos de grado\n\n");
                texto.append("Requisitos:\n");
                texto.append("• Haber presentado y aprobado la propuesta de trabajo de grado\n");
                texto.append("• Vigencia: 1 año desde publicación de resultados ICFES\n\n");
            } else if (puntaje > 241) {
                texto.append("═══════════════════════════════════════════════════════════════\n");
                texto.append("★★★ ¡EXCEPCIONAL! PUNTAJE EXTRAORDINARIO\n");
                texto.append("═══════════════════════════════════════════════════════════════\n\n");
                texto.append("INCENTIVOS UTS (Acuerdo 01-009 del 22 de abril de 2024):\n\n");
                texto.append("✓ Exoneración del informe final de trabajo de grado\n");
                texto.append("✓ Exoneración de Seminario de grado IV\n");
                texto.append("✓ Nota asignada: 5.0\n");
                texto.append("✓ Beca del 100% en derechos de grado\n\n");
                texto.append("Requisitos:\n");
                texto.append("• Haber presentado y aprobado la propuesta de trabajo de grado\n");
                texto.append("• Vigencia: 1 año desde publicación de resultados ICFES\n\n");
            }
            
            texto.append("───────────────────────────────────────────────────────────────\n");
            texto.append("NOTA: Los incentivos no son acumulables y son intransferibles.\n");
            
        } else {
            texto.append("───────────────────────────────────────────────────────────────\n");
            texto.append("⚠ NO TIENES RESULTADOS REGISTRADOS\n");
            texto.append("───────────────────────────────────────────────────────────────\n\n");
            texto.append("No se encontraron resultados asociados a tu código.\n");
            texto.append("Por favor contacta a coordinación para más información.\n");
        }
        
        texto.append("═══════════════════════════════════════════════════════════════\n");
        
        txtAreaResultado.setText(texto.toString());
        txtAreaResultado.setCaretPosition(0);
    }
    
    private void cargarTodosResultados() {
        modeloTabla.setRowCount(0);
        List<ResultadoExamen> resultados = resultadoDAO.listarTodos();
        
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "No hay resultados registrados en el sistema", 
                "Sin Resultados", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (ResultadoExamen resultado : resultados) {
            modeloTabla.addRow(new Object[]{
                resultado.getCodigoEstudiante(),
                resultado.getNombreEstudiante(),
                resultado.getProgramaEstudiante(),
                String.format("%.2f", resultado.getPuntajeTotal()),
                resultado.getEstado()
            });
        }
        
        JOptionPane.showMessageDialog(this, 
            "Se cargaron " + resultados.size() + " resultados exitosamente", 
            "Carga Exitosa", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void reprogramarExamen() {
        ResultadoExamen resultado = resultadoDAO.obtenerPorCodigoEstudiante(estudiante.getCodigo());
        
        if (resultado == null) {
            JOptionPane.showMessageDialog(this, 
                "No tienes resultados registrados en el sistema.\n" +
                "Por favor contacta a coordinación.", 
                "Sin Resultados", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (resultado.getPuntajeTotal() >= 80) {
            JOptionPane.showMessageDialog(this, 
                String.format("Tu puntaje es de %.2f puntos.\n\n" +
                "No necesitas reprogramar el examen ya que has aprobado.", 
                resultado.getPuntajeTotal()), 
                "No Requiere Reprogramación", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Date fechaSeleccionada = dateChooser.getDate();
        
        if (fechaSeleccionada == null) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona una fecha para reprogramar el examen", 
                "Fecha Requerida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (fechaSeleccionada.before(new Date())) {
            JOptionPane.showMessageDialog(this, 
                "La fecha seleccionada debe ser posterior a la fecha actual", 
                "Fecha Inválida", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(fechaSeleccionada);
        
        int confirmacion = JOptionPane.showConfirmDialog(this,
            String.format("¿Confirmas que deseas reprogramar el examen?\n\n" +
                "Fecha seleccionada: %s\n\n" +
                "Recibirás un correo de confirmación.", fechaFormateada),
            "Confirmar Reprogramación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            resultado.setFechaReprogramacion(fechaSeleccionada);
            
            if (resultadoDAO.actualizarFechaReprogramacion(resultado)) {
                JOptionPane.showMessageDialog(this,
                    String.format("¡Reprogramación exitosa!\n\n" +
                        "Tu examen ha sido reprogramado para el %s\n\n" +
                        "Detalles importantes:\n" +
                        "• Recibirás un correo de confirmación\n" +
                        "• Prepárate adecuadamente\n" +
                        "• Llega 30 minutos antes", fechaFormateada),
                    "Reprogramación Exitosa",
                    JOptionPane.INFORMATION_MESSAGE);
                dateChooser.setDate(null);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Error al reprogramar el examen.\n" +
                    "Por favor intenta nuevamente o contacta a coordinación.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cerrarSesion() {
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Deseas cerrar sesión y volver al menú principal?",
            "Cerrar Sesión",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
        }
    }
}