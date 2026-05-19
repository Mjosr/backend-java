package com.example.demo.model;
public class Baja {
    private String id; // Cambiado de Long a String
    private Long activoId;
    private String motivo;
    private String fecha;

    // Constructores, Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getActivoId() { return activoId; }
    public void setActivoId(Long activoId) { this.activoId = activoId; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
}