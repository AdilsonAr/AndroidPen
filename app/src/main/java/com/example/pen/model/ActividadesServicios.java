package com.example.pen.model;

import java.util.ArrayList;
import java.util.List;

public class ActividadesServicios {
    public static List<ActividadesAgenda> activida = new ArrayList<>();
    public static void agregarActividad(ActividadesAgenda actividad){
        activida.add(actividad);
    }

    public static void eliminar(ActividadesAgenda actividadesAgenda){
        activida.remove(actividadesAgenda);
    }

    public static void actualizar(ActividadesAgenda actividades){
        activida.set(activida.indexOf(actividades),actividades);
    }
}
