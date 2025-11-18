package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexionDB {
    // CORREGIDO: Agregar "jdbc:" al inicio y contraseña correcta
    private static final String URL = "jdbc:mysql://bksjazgbatnhpyayu75j-mysql.services.clever-cloud.com:3306/bksjazgbatnhpyayu75j?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "u5kfwobvjnscedms";
    private static final String PASSWORD = "V0fRgFecZ0WqbUtlzCp4"; // Corregido: era V0fRgFecZ... ahora es V0fRgPecZ...
    
    private static Connection conexion = null;
    
    // Método para obtener la conexión
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                System.out.println("=== INTENTANDO CONECTAR ===");
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("✓ Driver cargado");
                
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓✓✓ CONEXIÓN EXITOSA ✓✓✓");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: Driver no encontrado");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error: No se encontró el driver de MySQL\n" + e.getMessage(),
                "Error de Driver", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (SQLException e) {
            System.err.println("❌ ERROR SQL: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error al conectar con la base de datos\n" + e.getMessage(),
                "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return conexion;
    }
    
    // Método para cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
    // Método para probar la conexión
    public static boolean probarConexion() {
        Connection conn = getConexion();
        return conn != null;
    }
}