package com.example.damian.reservations;

/**
 * Created by android on 02/11/2017.
 */

public class Favoritos {
    private String id;
    private String id_usuario;
    private String id_cancha;
    private int calificacion;

    public Favoritos(String id, String id_usuario, String id_cancha, int calificacion) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_cancha = id_cancha;
        this.calificacion = calificacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_cancha() {
        return id_cancha;
    }

    public void setId_cancha(String id_cancha) {
        this.id_cancha = id_cancha;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
