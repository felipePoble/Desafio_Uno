package com.fjpp.desafio.model;

import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class GDDResponse implements Serializable {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    private List<LocalDate> fechas;

    public GDDResponse() {
    }

    public GDDResponse(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<LocalDate> getFechas() {
        return fechas;
    }

    public void setFechas(List<LocalDate> fechas) {
        this.fechas = fechas;
    }
}
