package com.example.damian.reservations;

/**
 * Created by android on 02/11/2017.
 */

public class Favoritos {
    private String id;
    private String id_usuario;
    private String id_establecimiento;
    private float calificacion;

    public Favoritos(String id, String id_usuario, String id_establecimiento, float calificacion) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_establecimiento = id_establecimiento;
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
        return id_establecimiento;
    }

    public void setId_cancha(String id_cancha) {
        this.id_establecimiento = id_cancha;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }
}
