package models;
public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String prioridad;
    private String estado;

    // Constructor vacío (necesario para Cursor)
    public Tarea() {
    }

    // Constructor completo
    public Tarea(int id, String titulo, String descripcion, String fecha, String prioridad, String estado) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return estado;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public boolean isCompletada() {
        return "completada".equalsIgnoreCase(estado);
    }


    @Override
    public String toString() {
        return titulo + " (" + prioridad + ") - " + estado;
    }
}
