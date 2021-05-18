package com.example.pen.model;

import java.util.Date;

public class ApunteFace {
    private long id;
    private Date fecha;
    private String nombre;
    private String tipo;

    public ApunteFace() {
    }

    public ApunteFace(long id, Date fecha, String nombre, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
