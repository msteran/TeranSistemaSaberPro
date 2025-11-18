package modelo;

import java.util.Date;

public class ResultadoExamen {
    private int id;
    private int estudianteId;
    private String tipoPrueba;
    private double puntaje;
    private Date fechaPresentacion;
    private boolean debeRepetir;
    private Date fechaRepeticion;
    private String observaciones;
    
    // Campos adicionales para joins
    private String codigoEstudiante;
    private String nombreEstudiante;
    private String programaEstudiante;
    
    // Constructor vacío
    public ResultadoExamen() {
    }
    
    // Constructor con parámetros
    public ResultadoExamen(int estudianteId, String tipoPrueba, double puntaje, 
                          Date fechaPresentacion, boolean debeRepetir) {
        this.estudianteId = estudianteId;
        this.tipoPrueba = tipoPrueba;
        this.puntaje = puntaje;
        this.fechaPresentacion = fechaPresentacion;
        this.debeRepetir = debeRepetir;
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getEstudianteId() {
        return estudianteId;
    }
    
    public void setEstudianteId(int estudianteId) {
        this.estudianteId = estudianteId;
    }
    
    public String getTipoPrueba() {
        return tipoPrueba;
    }
    
    public void setTipoPrueba(String tipoPrueba) {
        this.tipoPrueba = tipoPrueba;
    }
    
    public double getPuntaje() {
        return puntaje;
    }
    
    public void setPuntaje(double puntaje) {
        this.puntaje = puntaje;
    }
    
    public Date getFechaPresentacion() {
        return fechaPresentacion;
    }
    
    public void setFechaPresentacion(Date fechaPresentacion) {
        this.fechaPresentacion = fechaPresentacion;
    }
    
    public boolean isDebeRepetir() {
        return debeRepetir;
    }
    
    public void setDebeRepetir(boolean debeRepetir) {
        this.debeRepetir = debeRepetir;
    }
    
    public Date getFechaRepeticion() {
        return fechaRepeticion;
    }
    
    public void setFechaRepeticion(Date fechaRepeticion) {
        this.fechaRepeticion = fechaRepeticion;
    }
    
    // Nuevo método para reprogramación
    public Date getFechaReprogramacion() {
        return fechaRepeticion;
    }
    
    public void setFechaReprogramacion(Date fechaReprogramacion) {
        this.fechaRepeticion = fechaReprogramacion;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }
    
    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }
    
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }
    
    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }
    
    public String getProgramaEstudiante() {
        return programaEstudiante;
    }
    
    public void setProgramaEstudiante(String programaEstudiante) {
        this.programaEstudiante = programaEstudiante;
    }
    
    // Método para calcular puntaje total (sobre 300)
    public double getPuntajeTotal() {
        return puntaje;
    }
    
    // Método para obtener estado según puntaje
    public String getEstado() {
        if (puntaje < 80) {
            return "DEBE REPROGRAMAR";
        } else if (puntaje >= 80 && puntaje < 180) {
            return "APROBADO";
        } else if (puntaje >= 180 && puntaje <= 210) {
            return "SOBRESALIENTE";
        } else if (puntaje >= 211 && puntaje <= 240) {
            return "MUY SOBRESALIENTE";
        } else {
            return "EXTRAORDINARIO";
        }
    }
    
    @Override
    public String toString() {
        return "ResultadoExamen{" +
                "id=" + id +
                ", estudianteId=" + estudianteId +
                ", tipoPrueba='" + tipoPrueba + '\'' +
                ", puntaje=" + puntaje +
                ", fechaPresentacion=" + fechaPresentacion +
                ", debeRepetir=" + debeRepetir +
                ", fechaRepeticion=" + fechaRepeticion +
                ", estado=" + getEstado() +
                '}';
    }
}