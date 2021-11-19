package com.diegomorales.sql1;

import androidx.annotation.NonNull;

public class Producto {
    private Integer idProducto;
    private String nomProducto;
    private Float valorNeto;

    public Producto(Integer idProducto, String nomProducto, Float valorNeto) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.valorNeto = valorNeto;
    }

    public Integer getIdProducto() { return idProducto; }
    public void setIdProducto(Integer idProducto) { this.idProducto = idProducto; }

    public String getNomProducto() { return nomProducto; }
    public void setNomProducto(String nomProducto) { this.nomProducto = nomProducto; }

    public Float getValorNeto() { return valorNeto; }
    public void setValorNeto(Float valorNeto) { this.valorNeto = valorNeto; }

    @NonNull
    @Override
    public String toString() { return String.format("%d. %s", idProducto, nomProducto); }
}
