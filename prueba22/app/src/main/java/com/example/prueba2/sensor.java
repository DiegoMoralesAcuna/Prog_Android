package com.example.prueba2;

import androidx.annotation.NonNull;

public class sensor {
    private String namSensor;
    private String ValSensor;

    public sensor(String namSensor, String valSensor) {
        this.namSensor = namSensor;
        ValSensor = valSensor;
    }

    public sensor(String string, float aFloat) {

    }

    public String getNamSensor() {
        return namSensor;
    }

    public void setNamSensor(String namSensor) {
        this.namSensor = namSensor;
    }

    public String getValSensor() {
        return ValSensor;
    }

    public void setValSensor(String valSensor) {
        ValSensor = valSensor;
    }
    @NonNull
    @Override
    public String toString() { return String.format("%s %s", namSensor, ValSensor); }
}
