package com.example.demo.model;

public class Empleado {
    private String id; // Firebase usa String
    private String nombre;
    private String puesto;
    private String correo;

    // Constructor vacío requerido por Firebase para mapear los documentos
    public Empleado() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}