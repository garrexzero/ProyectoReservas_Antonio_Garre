package com.garre.appreservasjavafx;

public class Recurso {
    private int idRecurso;
    private String nombre;
    private int capacidad;

    public Recurso() {}

    public Recurso(int idRecurso, String nombre, int capacidad) {
        this.idRecurso = idRecurso;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getIdRecurso() { return idRecurso; }
    public void setIdRecurso(int idRecurso) { this.idRecurso = idRecurso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
}