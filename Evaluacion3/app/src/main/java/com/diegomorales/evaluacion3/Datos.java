package com.diegomorales.evaluacion3;

public class Datos {
    private String idSensor;
    private String nombreSensor;
    private String tipoSensor;
    private String valorSensor;
    private String ubicacionSensor;
    private String fechaRegistro;
    private String observacionSensor;

    public Datos() {
    }

    public Datos(String idSensor, String nombreSensor, String tipoSensor, String valorSensor, String ubicacionSensor, String fechaRegistro, String observacionSensor) {
        this.idSensor = idSensor;
        this.nombreSensor = nombreSensor;
        this.tipoSensor = tipoSensor;
        this.valorSensor = valorSensor;
        this.ubicacionSensor = ubicacionSensor;
        this.fechaRegistro = fechaRegistro;
        this.observacionSensor = observacionSensor;
    }

    public String getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(String idSensor) {
        this.idSensor = idSensor;
    }

    public String getNombreSensor() {
        return nombreSensor;
    }

    public void setNombreSensor(String nombreSensor) {
        this.nombreSensor = nombreSensor;
    }

    public String getTipoSensor() {
        return tipoSensor;
    }

    public void setTipoSensor(String tipoSensor) {
        this.tipoSensor = tipoSensor;
    }

    public String getValorSensor() {
        return valorSensor;
    }

    public void setValorSensor(String valorSensor) {
        this.valorSensor = valorSensor;
    }

    public String getUbicacionSensor() {
        return ubicacionSensor;
    }

    public void setUbicacionSensor(String ubicacionSensor) {
        this.ubicacionSensor = ubicacionSensor;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacionSensor() {
        return observacionSensor;
    }

    public void setObservacionSensor(String observacionSensor) {
        this.observacionSensor = observacionSensor;
    }
}
