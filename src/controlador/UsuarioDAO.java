package controlador;

import modelo.Usuario;
import util.ConexionDB;
import java.sql.*;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    
    // Método para autenticar usuario
    public Usuario autenticar(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setRol(rs.getString("rol"));
                
                Integer estId = (Integer) rs.getObject("estudiante_id");
                usuario.setEstudianteId(estId);
                
                return usuario;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al autenticar: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    // Método para crear usuario estudiante
    public boolean crearUsuarioEstudiante(String username, String password, int estudianteId) {
        String sql = "INSERT INTO usuarios (username, password, rol, estudiante_id) VALUES (?, ?, 'ESTUDIANTE', ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setInt(3, estudianteId);
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al crear usuario: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para verificar si existe un usuario
    public boolean existeUsuario(String username) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            System.err.println("Error al verificar usuario: " + e.getMessage());
        }
        
        return false;
    }
}