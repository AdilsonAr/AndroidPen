package com.example.pen.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
@Entity
public class ApunteKeyValue implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String fecha;
    private String nombre;
    private String tipo;

    public ApunteKeyValue() {
    }

    public ApunteKeyValue(long id, String fecha, String nombre, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.nombre = nombre;
        this.tipo = tipo;
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
}
