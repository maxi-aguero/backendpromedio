package com.apress.spring;

import jakarta.persistence.*;

@Entity
@Table(name = "cortes_promedio")
public class CorteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto; // Ej: "Asado", "Vacío", "Entraña"
    private Double kg;       // Cantidad de kilos de este corte
    private Double precioKg; // Precio por kilo de este corte

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promedio_id")
    private PromedioEntity promedio;

    public CorteEntity() {
    }

    public CorteEntity(String producto, Double kg, Double precioKg) {
        this.producto = producto;
        this.kg = kg;
        this.precioKg = precioKg;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Double getPrecioKg() {
        return precioKg;
    }

    public void setPrecioKg(Double precioKg) {
        this.precioKg = precioKg;
    }

    public PromedioEntity getPromedio() {
        return promedio;
    }

    public void setPromedio(PromedioEntity promedio) {
        this.promedio = promedio;
    }
}