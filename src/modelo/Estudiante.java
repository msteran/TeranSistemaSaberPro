package modelo;

import java.util.Date;

public class Estudiante {
    private int id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String programa;
    private int semestre;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    
    // Constructor vacío
    public Estudiante() {
    }
    
    // Constructor con parámetros
    public Estudiante(String codigo, String nombres, String apellidos, 
                     String programa, int semestre, String email, String telefono) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.programa = programa;
        this.semestre = semestre;
        this.email = email;
        this.telefono = telefono;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getNombres() {
        return nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    
    public String getApellidos() {
        return apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public String getPrograma() {
        return programa;
    }
    
    public void setPrograma(String programa) {
        this.programa = programa;
    }
    
    public int getSemestre() {
        return semestre;
    }
    
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    // Método adicional para obtener nombre completo
    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
    
    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", programa='" + programa + '\'' +
                ", semestre=" + semestre +
                ", email='" + email + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}