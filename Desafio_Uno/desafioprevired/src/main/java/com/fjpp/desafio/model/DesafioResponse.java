package com.fjpp.desafio.model;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class DesafioResponse {

    private Integer id;
    private LocalDate fechaCreacion;
    private LocalDate fechaFin;
    private List<LocalDate> fechas;
    private List<LocalDate> fechasFaltantes;

    public DesafioResponse() {
    }

    public DesafioResponse(Integer id, LocalDate fechaCreacion, LocalDate fechaFin, List<LocalDate> fechas, List<LocalDate> fechasFaltantes) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.fechaFin = fechaFin;
        this.fechas = fechas;
        this.fechasFaltantes = fechasFaltantes;
    }

    public DesafioResponse(GDDResponse gddResponse) {
        this.id = gddResponse.getId();
        this.fechaCreacion = gddResponse.getFechaCreacion();
        this.fechaFin = gddResponse.getFechaFin();
        this.fechas = gddResponse.getFechas();
        this.fechasFaltantes = new ArrayList<>();
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

    public List<LocalDate> getFechasFaltantes() {
        return fechasFaltantes;
    }

    public void setFechasFaltantes(List<LocalDate> fechasFaltantes) {
        this.fechasFaltantes = fechasFaltantes;
    }
}
