package com.apress.spring;

public class CorteResponse {
    private Long id;
    private String producto;
    private Double kg;
    private Double precioKg;

    public CorteResponse() {}

    public CorteResponse(Long id, String producto, Double kg, Double precioKg) {
        this.id = id;
        this.producto = producto;
        this.kg = kg;
        this.precioKg = precioKg;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public Double getKg() { return kg; }
    public void setKg(Double kg) { this.kg = kg; }

    public Double getPrecioKg() { return precioKg; }
    public void setPrecioKg(Double precioKg) { this.precioKg = precioKg; }
}
