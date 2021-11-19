package com.diegomorales.evaluacionu2;

import java.sql.Time;
import java.util.Date;

public class Sensores {
    private int id;
    private String nom_sensor;
    private String valor_sensor;
    private String fecha_sensor;
    private String hora_sensor;
    private String observacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_sensor() {
        return nom_sensor;
    }

    public void setNom_sensor(String nom_sensor) {
        this.nom_sensor = nom_sensor;
    }

    public String getValor_sensor() {
        return valor_sensor;
    }

    public void setValor_sensor(String valor_sensor) {
        this.valor_sensor = valor_sensor;
    }

    public String getFecha_sensor() {
        return fecha_sensor;
    }

    public void setFecha_sensor(String fecha_sensor) {
        this.fecha_sensor = fecha_sensor;
    }

    public String getHora_sensor() {
        return hora_sensor;
    }

    public void setHora_sensor(String hora_sensor) {
        this.hora_sensor = hora_sensor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
