package com.example.damian.reservations;

import java.sql.Time;
import java.util.Date;

/**
 * Created by android on 02/11/2017.
 */

public class Reservas {
    private String id;
    private String id_cancha;
    private String id_usuario;
    private String fecha;
    private Time hora;
    private boolean estado;

    public Reservas(String id, String id_cancha, String fecha, Time hora, boolean estado,String id_usuario) {
        this.id = id;
        this.id_cancha = id_cancha;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.id_usuario=id_usuario;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_cancha() {
        return id_cancha;
    }

    public void setId_cancha(String id_cancha) {
        this.id_cancha = id_cancha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
