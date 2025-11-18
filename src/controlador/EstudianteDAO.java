package controlador;

import modelo.Estudiante;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class EstudianteDAO {
    
    // Método para crear un estudiante
    public boolean crear(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (codigo, nombres, apellidos, programa, semestre, email, telefono) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, estudiante.getCodigo());
            pst.setString(2, estudiante.getNombres());
            pst.setString(3, estudiante.getApellidos());
            pst.setString(4, estudiante.getPrograma());
            pst.setInt(5, estudiante.getSemestre());
            pst.setString(6, estudiante.getEmail());
            pst.setString(7, estudiante.getTelefono());
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error SQL en crear: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al crear estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
                // No cerrar conn si es singleton
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para leer todos los estudiantes
    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY apellidos, nombres";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.getConexion();
            
            // VALIDACIÓN CRÍTICA
            if (conn == null) {
                System.err.println("ERROR CRÍTICO: ConexionDB.getConexion() retornó null");
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos.\n" +
                    "Revisa la consola para más detalles.",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return lista; // Retorna lista vacía
            }
            
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setCodigo(rs.getString("codigo"));
                est.setNombres(rs.getString("nombres"));
                est.setApellidos(rs.getString("apellidos"));
                est.setPrograma(rs.getString("programa"));
                est.setSemestre(rs.getInt("semestre"));
                est.setEmail(rs.getString("email"));
                est.setTelefono(rs.getString("telefono"));
                est.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                lista.add(est);
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL en listarTodos: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al listar estudiantes: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                // No cerrar conn si es singleton
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }
    
    // Método para buscar por código
    public Estudiante buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM estudiantes WHERE codigo = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, codigo);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setCodigo(rs.getString("codigo"));
                est.setNombres(rs.getString("nombres"));
                est.setApellidos(rs.getString("apellidos"));
                est.setPrograma(rs.getString("programa"));
                est.setSemestre(rs.getInt("semestre"));
                est.setEmail(rs.getString("email"));
                est.setTelefono(rs.getString("telefono"));
                est.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                return est;
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL en buscarPorCodigo: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al buscar estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    // Método para buscar por ID
    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM estudiantes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setCodigo(rs.getString("codigo"));
                est.setNombres(rs.getString("nombres"));
                est.setApellidos(rs.getString("apellidos"));
                est.setPrograma(rs.getString("programa"));
                est.setSemestre(rs.getInt("semestre"));
                est.setEmail(rs.getString("email"));
                est.setTelefono(rs.getString("telefono"));
                est.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                return est;
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL en buscarPorId: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al buscar estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return null;
    }
    
    // Método para actualizar
    public boolean actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiantes SET codigo=?, nombres=?, apellidos=?, programa=?, semestre=?, email=?, telefono=? WHERE id=?";
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, estudiante.getCodigo());
            pst.setString(2, estudiante.getNombres());
            pst.setString(3, estudiante.getApellidos());
            pst.setString(4, estudiante.getPrograma());
            pst.setInt(5, estudiante.getSemestre());
            pst.setString(6, estudiante.getEmail());
            pst.setString(7, estudiante.getTelefono());
            pst.setInt(8, estudiante.getId());
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error SQL en actualizar: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para eliminar por ID
    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error SQL en eliminar: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para eliminar por código
    public boolean eliminarPorCodigo(String codigo) {
        String sql = "DELETE FROM estudiantes WHERE codigo = ?";
        Connection conn = null;
        PreparedStatement pst = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, codigo);
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error SQL en eliminarPorCodigo: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar estudiante: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Método para buscar estudiantes por nombre o código
    public List<Estudiante> buscar(String criterio) {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE codigo LIKE ? OR nombres LIKE ? OR apellidos LIKE ? ORDER BY apellidos, nombres";
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            conn = ConexionDB.getConexion();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, 
                    "No se pudo establecer conexión con la base de datos",
                    "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                return lista;
            }
            
            pst = conn.prepareStatement(sql);
            String busqueda = "%" + criterio + "%";
            pst.setString(1, busqueda);
            pst.setString(2, busqueda);
            pst.setString(3, busqueda);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setCodigo(rs.getString("codigo"));
                est.setNombres(rs.getString("nombres"));
                est.setApellidos(rs.getString("apellidos"));
                est.setPrograma(rs.getString("programa"));
                est.setSemestre(rs.getInt("semestre"));
                est.setEmail(rs.getString("email"));
                est.setTelefono(rs.getString("telefono"));
                est.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                lista.add(est);
            }
            
        } catch (SQLException e) {
            System.err.println("Error SQL en buscar: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al buscar: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return lista;
    }
}