package org.example.modelo;

public class Incidencia {
    private String descripcion;
    private String lugar;
    private Usuario empleado;
    private String codigo;
    private String prioridad;

    public Incidencia() {
    }

    public Incidencia(String descripcion, String lugar, Usuario empleado, String codigo, String prioridad) {
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.empleado = empleado;
        this.codigo = codigo;
        this.prioridad = prioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Usuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Usuario empleado) {
        this.empleado = empleado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

}
