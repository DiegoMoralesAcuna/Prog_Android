package com.diegomorales.EvaluacionU2;

public class Sensores {
    private int id;
    private String direccion;
    private String sensortipo;
    private String valorsensor;
    private String fecha;
    private String hora;
    private String observacion;

    public String getSensortipo() {
        return sensortipo;
    }

    public void setSensortipo(String sensortipo) {
        this.sensortipo = sensortipo;
    }

    public String getValorsensor() {
        return valorsensor;
    }

    public void setValorsensor(String valorsensor) {
        this.valorsensor = valorsensor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
