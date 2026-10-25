package com.apress.spring;

public class CorteRequest {
    private String producto;
    private Double kg;
    private Double precioKg;

    public CorteRequest() {}

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Double getKg() { return kg; }
    public void setKg(Double kg) { this.kg = kg; }

    public Double getPrecioKg() { return precioKg; }
    public void setPrecioKg(Double precioKg) { this.precioKg = precioKg; }
}
