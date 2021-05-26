package com.example.pen.model;

import java.util.Objects;

public class ActividadesAgenda {
    private String id;
    private String fecha;
    private String actividad;
    private String descripcion;
    private String horainicio;
    private String horafin;
    private String imagen;

    public ActividadesAgenda() {
    }

    public ActividadesAgenda(String fecha, String actividad, String descripcion, String horainicio, String horafin, String imagen) {
        this.fecha = fecha;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
       return id.equals(((ActividadesAgenda) o).id);
    }

}

