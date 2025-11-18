package controlador;

import modelo.ResultadoExamen;
import util.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ResultadoExamenDAO {
    
    // Método para crear un resultado
    public boolean crear(ResultadoExamen resultado) {
        String sql = "INSERT INTO resultados_examen (estudiante_id, tipo_prueba, puntaje, fecha_presentacion, debe_repetir, fecha_repeticion, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, resultado.getEstudianteId());
            pst.setString(2, resultado.getTipoPrueba());
            pst.setDouble(3, resultado.getPuntaje());
            pst.setDate(4, new java.sql.Date(resultado.getFechaPresentacion().getTime()));
            pst.setBoolean(5, resultado.isDebeRepetir());
            
            if (resultado.getFechaRepeticion() != null) {
                pst.setDate(6, new java.sql.Date(resultado.getFechaRepeticion().getTime()));
            } else {
                pst.setNull(6, Types.DATE);
            }
            
            pst.setString(7, resultado.getObservaciones());
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al registrar resultado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para obtener todos los resultados con información del estudiante
    public List<ResultadoExamen> listarTodos() {
        List<ResultadoExamen> lista = new ArrayList<>();
        String sql = "SELECT r.*, e.codigo, e.nombres, e.apellidos, e.programa " +
                     "FROM resultados_examen r " +
                     "INNER JOIN estudiantes e ON r.estudiante_id = e.id " +
                     "ORDER BY r.fecha_presentacion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                ResultadoExamen res = crearResultadoDesdeRS(rs);
                lista.add(res);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al listar resultados: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return lista;
    }
    
    // MÉTODO PARA OBTENER RESULTADO POR CÓDIGO DE ESTUDIANTE
    public ResultadoExamen obtenerPorCodigoEstudiante(String codigo) {
        String sql = "SELECT r.*, e.codigo, e.nombres, e.apellidos, e.programa " +
                     "FROM resultados_examen r " +
                     "INNER JOIN estudiantes e ON r.estudiante_id = e.id " +
                     "WHERE e.codigo = ? " +
                     "ORDER BY r.fecha_presentacion DESC LIMIT 1";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, codigo);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return crearResultadoDesdeRS(rs);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al buscar resultado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    // Método para obtener resultado por estudiante ID
    public ResultadoExamen obtenerPorEstudiante(int estudianteId) {
        String sql = "SELECT r.*, e.codigo, e.nombres, e.apellidos, e.programa " +
                     "FROM resultados_examen r " +
                     "INNER JOIN estudiantes e ON r.estudiante_id = e.id " +
                     "WHERE r.estudiante_id = ? " +
                     "ORDER BY r.fecha_presentacion DESC LIMIT 1";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, estudianteId);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return crearResultadoDesdeRS(rs);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al buscar resultado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return null;
    }
    
    // Método para obtener todos los resultados de un estudiante
    public List<ResultadoExamen> obtenerTodosPorEstudiante(int estudianteId) {
        List<ResultadoExamen> lista = new ArrayList<>();
        String sql = "SELECT r.*, e.codigo, e.nombres, e.apellidos, e.programa " +
                     "FROM resultados_examen r " +
                     "INNER JOIN estudiantes e ON r.estudiante_id = e.id " +
                     "WHERE r.estudiante_id = ? " +
                     "ORDER BY r.fecha_presentacion DESC";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, estudianteId);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                ResultadoExamen res = crearResultadoDesdeRS(rs);
                lista.add(res);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al buscar resultados: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return lista;
    }
    
    // Método para actualizar
    public boolean actualizar(ResultadoExamen resultado) {
        String sql = "UPDATE resultados_examen SET tipo_prueba=?, puntaje=?, fecha_presentacion=?, debe_repetir=?, fecha_repeticion=?, observaciones=? WHERE id=?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, resultado.getTipoPrueba());
            pst.setDouble(2, resultado.getPuntaje());
            pst.setDate(3, new java.sql.Date(resultado.getFechaPresentacion().getTime()));
            pst.setBoolean(4, resultado.isDebeRepetir());
            
            if (resultado.getFechaRepeticion() != null) {
                pst.setDate(5, new java.sql.Date(resultado.getFechaRepeticion().getTime()));
            } else {
                pst.setNull(5, Types.DATE);
            }
            
            pst.setString(6, resultado.getObservaciones());
            pst.setInt(7, resultado.getId());
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar resultado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // MÉTODO PARA ACTUALIZAR FECHA DE REPROGRAMACIÓN
    public boolean actualizarFechaReprogramacion(ResultadoExamen resultado) {
        String sql = "UPDATE resultados_examen SET fecha_repeticion = ?, debe_repetir = true WHERE estudiante_id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            if (resultado.getFechaReprogramacion() != null) {
                pst.setDate(1, new java.sql.Date(resultado.getFechaReprogramacion().getTime()));
            } else {
                pst.setNull(1, Types.DATE);
            }
            pst.setInt(2, resultado.getEstudianteId());
            
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar fecha de reprogramación: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // Método para eliminar
    public boolean eliminar(int id) {
        String sql = "DELETE FROM resultados_examen WHERE id = ?";
        
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, id);
            int filas = pst.executeUpdate();
            return filas > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al eliminar resultado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    // MÉTODO AUXILIAR PARA CREAR OBJETO DESDE RESULTSET
    private ResultadoExamen crearResultadoDesdeRS(ResultSet rs) throws SQLException {
        ResultadoExamen res = new ResultadoExamen();
        res.setId(rs.getInt("id"));
        res.setEstudianteId(rs.getInt("estudiante_id"));
        res.setTipoPrueba(rs.getString("tipo_prueba"));
        res.setPuntaje(rs.getDouble("puntaje"));
        res.setFechaPresentacion(rs.getDate("fecha_presentacion"));
        res.setDebeRepetir(rs.getBoolean("debe_repetir"));
        
        Date fechaRep = rs.getDate("fecha_repeticion");
        if (fechaRep != null) {
            res.setFechaRepeticion(fechaRep);
        }
        
        res.setObservaciones(rs.getString("observaciones"));
        res.setCodigoEstudiante(rs.getString("codigo"));
        res.setNombreEstudiante(rs.getString("nombres") + " " + rs.getString("apellidos"));
        res.setProgramaEstudiante(rs.getString("programa"));
        
        return res;
    }
    
    // Método para obtener estadísticas
    public int contarAprobados() {
        String sql = "SELECT COUNT(*) FROM resultados_examen WHERE puntaje >= 80";
        return ejecutarConteo(sql);
    }
    
    public int contarReprobados() {
        String sql = "SELECT COUNT(*) FROM resultados_examen WHERE puntaje < 80";
        return ejecutarConteo(sql);
    }
    
    public int contarSobresalientes() {
        String sql = "SELECT COUNT(*) FROM resultados_examen WHERE puntaje >= 180";
        return ejecutarConteo(sql);
    }
    
    private int ejecutarConteo(String sql) {
        try (Connection conn = ConexionDB.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.err.println("Error al contar: " + e.getMessage());
        }
        return 0;
    }
}