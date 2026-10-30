package com.apress.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromedioEntity {
    
    private String id; // En Firestore los IDs de documentos suelen ser String

    private Double preciomediares;
    private Double novillokg; 
    private Double totalkg; 

    private Date created;

    private List<CorteEntity> cortes = new ArrayList<>();

    // Constructor con parámetros
    public PromedioEntity(Double preciomediares, Double novillokg, String date) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");
        try {
            this.created = miformato.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Constructor vacío obligatorio para que Firestore pueda mapear los datos automáticamente
    public PromedioEntity() {
    }

    public void addCorte(CorteEntity corte) {
        cortes.add(corte);
    }

    public void removeCorte(CorteEntity corte) {
        cortes.remove(corte);
    }

    // Getters y Setters (Cambiamos Long id por String id)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Double getPreciomediares() { return preciomediares; }
    public void setPreciomediares(Double preciomediares) { this.preciomediares = preciomediares; }

    public Double getNovillokg() { return novillokg; }
    public void setNovillokg(Double novillokg) { this.novillokg = novillokg; }

    public Double getTotalkg() { return totalkg; }
    public void setTotalkg(Double totalkg) { this.totalkg = totalkg; }

    public Double getCostoreal() { 
        if (preciomediares == null || novillokg == null || totalkg == null || totalkg == 0.0) {
            return 0.0;
        }
        return (preciomediares * novillokg) / totalkg;
    }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }

    public Double getTotalcompra() { 
        Double costo = getCostoreal();
        if (costo == null || totalkg == null) {
            return 0.0;
        }
        return costo * totalkg;
    }

    public List<CorteEntity> getCortes() { return cortes; }
    public void setCortes(List<CorteEntity> cortes) { this.cortes = cortes; }
}
