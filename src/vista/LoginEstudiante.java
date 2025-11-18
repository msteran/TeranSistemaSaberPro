package vista;

import controlador.EstudianteDAO;
import modelo.Estudiante;
import javax.swing.*;
import java.awt.*;

public class LoginEstudiante extends JFrame {
    private JTextField txtCodigo;
    private EstudianteDAO estudianteDAO;
    
    public LoginEstudiante() {
        estudianteDAO = new EstudianteDAO();
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Identificación de Estudiante");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel superior
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 153, 51));
        JLabel lblTitulo = new JLabel("ACCESO ESTUDIANTE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);
        
        // Panel central
        JPanel panelCentral = new JPanel(new GridBagLayout());
        panelCentral.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblCodigo = new JLabel("Código de Estudiante:");
        lblCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelCentral.add(lblCodigo, gbc);
        
        txtCodigo = new JTextField(20);
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        panelCentral.add(txtCodigo, gbc);
        
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));
        btnIngresar.setBackground(new Color(0, 153, 51));
        btnIngresar.setForeground(Color.BLACK);
        btnIngresar.setFocusPainted(false);
        btnIngresar.addActionListener(e -> verificarEstudiante());
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panelCentral.add(btnIngresar, gbc);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        btnVolver.setBackground(new Color(128, 128, 128));
        btnVolver.setForeground(Color.BLACK);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> volver());
        gbc.gridx = 1;
        panelCentral.add(btnVolver, gbc);
        
        add(panelCentral, BorderLayout.CENTER);
        
        // Permitir Enter para ingresar
        txtCodigo.addActionListener(e -> verificarEstudiante());
    }
    
    private void verificarEstudiante() {
        String codigo = txtCodigo.getText().trim();
        
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese su código de estudiante");
            return;
        }
        
        Estudiante estudiante = estudianteDAO.buscarPorCodigo(codigo);
        
        if (estudiante != null) {
            MenuEstudiante menu = new MenuEstudiante(estudiante);
            menu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, 
                "Código de estudiante no encontrado. Verifique e intente nuevamente.", 
                "Error de Identificación", 
                JOptionPane.ERROR_MESSAGE);
            txtCodigo.setText("");
            txtCodigo.requestFocus();
        }
    }
    
    private void volver() {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        this.dispose();
    }
}