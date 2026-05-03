package com.garre.appreservasjavafx;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int idReservaLocal;
    private int idRecurso;
    private int idUsuario;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double coste;
    private int numeroPlazas;
    private String motivo;
    private String observaciones;

    public Reserva() {}

    public Reserva(int idReservaLocal, int idRecurso, int idUsuario, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, double coste, int numeroPlazas, String motivo, String observaciones) {
        this.idReservaLocal = idReservaLocal;
        this.idRecurso = idRecurso;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.coste = coste;
        this.numeroPlazas = numeroPlazas;
        this.motivo = motivo;
        this.observaciones = observaciones;
    }

    public int getIdReservaLocal() { return idReservaLocal; }
    public void setIdReservaLocal(int idReservaLocal) { this.idReservaLocal = idReservaLocal; }

    public int getIdRecurso() { return idRecurso; }
    public void setIdRecurso(int idRecurso) { this.idRecurso = idRecurso; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public double getCoste() { return coste; }
    public void setCoste(double coste) { this.coste = coste; }

    public int getNumeroPlazas() { return numeroPlazas; }
    public void setNumeroPlazas(int numeroPlazas) { this.numeroPlazas = numeroPlazas; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}