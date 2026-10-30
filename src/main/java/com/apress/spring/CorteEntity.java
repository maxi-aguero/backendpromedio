package com.apress.spring;

public class CorteEntity {

    private String id; // Opcional, por si Firestore u vos le asignan un ID

    private String producto; // Ej: "Asado", "Vacío", "Entraña"
    private Double kg;       // Cantidad de kilos de este corte
    private Double precioKg; // Precio por kilo de este corte

    // Constructor vacío (Obligatorio para Firestore)
    public CorteEntity() {
    }

    // Constructor con parámetros
    public CorteEntity(String producto, Double kg, Double precioKg) {
        this.producto = producto;
        this.kg = kg;
        this.precioKg = precioKg;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    // Nota: Ya no necesitamos getPromedio() / setPromedio() 
    // porque la lista de cortes vive adentro de PromedioEntity.
}
