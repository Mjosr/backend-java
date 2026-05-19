package com.example.demo.model;

public class Asignacion {
    private String id;
    private String empleadoId; // Enlazado por el ID String de Firebase
    private String activoId;   // Enlazado por el ID String de Firebase
    private String fechaAsignacion;

    public Asignacion() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEmpleadoId() { return empleadoId; }
    public void setEmpleadoId(String empleadoId) { this.empleadoId = empleadoId; }
    public String getActivoId() { return activoId; }
    public void setActivoId(String activoId) { this.activoId = activoId; }
    public String getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(String fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}