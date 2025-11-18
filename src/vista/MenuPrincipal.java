package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    
    public MenuPrincipal() {
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema Exámenes Saber Pro - UTS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel superior con título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 51, 102));
        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN SABER PRO");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central con botones
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Botón Coordinación
        JButton btnCoordinacion = new JButton("COORDINACIÓN");
        btnCoordinacion.setFont(new Font("Arial", Font.BOLD, 16));
        btnCoordinacion.setPreferredSize(new Dimension(300, 60));
        btnCoordinacion.setBackground(new Color(1, 1, 1));
        btnCoordinacion.setForeground(Color.BLACK);
        btnCoordinacion.setFocusPainted(false);
        btnCoordinacion.addActionListener(e -> abrirMenuCoordinacion());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentral.add(btnCoordinacion, gbc);
        
        // Botón Estudiante
        JButton btnEstudiante = new JButton("ESTUDIANTE");
        btnEstudiante.setFont(new Font("Arial", Font.BOLD, 16));
        btnEstudiante.setPreferredSize(new Dimension(300, 60));
        btnEstudiante.setBackground(new Color(1, 1, 1));
        btnEstudiante.setForeground(Color.BLACK);
        btnEstudiante.setFocusPainted(false);
        btnEstudiante.addActionListener(e -> abrirMenuEstudiante());
        gbc.gridy = 1;
        panelCentral.add(btnEstudiante, gbc);
        
        // Botón Salir
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setPreferredSize(new Dimension(300, 60));
        btnSalir.setBackground(new Color(204, 0, 0));
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFocusPainted(false);
        btnSalir.addActionListener(e -> System.exit(0));
        gbc.gridy = 2;
        panelCentral.add(btnSalir, gbc);
        
        add(panelCentral, BorderLayout.CENTER);
        
        // Panel inferior
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(240, 240, 240));
        JLabel lblInfo = new JLabel("Unidades Tecnológicas de Santander - 2025");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 12));
        panelInferior.add(lblInfo);
        add(panelInferior, BorderLayout.SOUTH);
    }
    
    private void abrirMenuCoordinacion() {
        MenuCoordinacion menu = new MenuCoordinacion();
        menu.setVisible(true);
        this.dispose();
    }
    
    private void abrirMenuEstudiante() {
        LoginEstudiante login = new LoginEstudiante();
        login.setVisible(true);
        this.dispose();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new MenuPrincipal().setVisible(true);
        });
    }
}