package modelo;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String rol; // "COORDINACION" o "ESTUDIANTE"
    private Integer estudianteId;
    
    public Usuario() {
    }
    
    public Usuario(int id, String username, String password, String rol, Integer estudianteId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.estudianteId = estudianteId;
    }
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    
    public Integer getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Integer estudianteId) { this.estudianteId = estudianteId; }
    
    public boolean esCoordinacion() {
        return "COORDINACION".equals(rol);
    }
    
    public boolean esEstudiante() {
        return "ESTUDIANTE".equals(rol);
    }
}