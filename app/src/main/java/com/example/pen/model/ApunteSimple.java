package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
@Entity
public class ApunteSimple {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String fecha;
    private String nombre;
    private String tipo;
    private String content;

    public ApunteSimple() {
    }

    public ApunteSimple(long id, String fecha, String nombre, String tipo, String content) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.tipo = tipo;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
