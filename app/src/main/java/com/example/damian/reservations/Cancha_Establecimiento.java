package com.example.damian.reservations;

/**
 * Created by android on 08/11/2017.
 */

public class Cancha_Establecimiento {
    private String id_cacha;
    private String nombre_establecimiento;
    private String direccion;
    private String celular;
    private String tamano;

    public Cancha_Establecimiento(String id_cacha, String nombre_establecimiento, String direccion, String celular, String tamano) {
        this.id_cacha = id_cacha;
        this.nombre_establecimiento = nombre_establecimiento;
        this.direccion = direccion;
        this.celular = celular;
        this.tamano = tamano;
    }

    public String getId_cacha() {
        return id_cacha;
    }

    public void setId_cacha(String id_cacha) {
        this.id_cacha = id_cacha;
    }

    public String getNombre_establecimiento() {
        return nombre_establecimiento;
    }

    public void setNombre_establecimiento(String nombre_establecimiento) {
        this.nombre_establecimiento = nombre_establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }
}
