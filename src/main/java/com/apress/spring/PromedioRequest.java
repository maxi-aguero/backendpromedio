package com.apress.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PromedioRequest {

    private Double preciomediares;
    private Double novillokg; // kg de la mediares
    private Double totalkg;   // kg de la mediares utilizables
    private Date created;
    
    // Lista para recibir los cortes que vienen desde React
    private List<CorteRequest> cortes;

    public PromedioRequest() {}

    public PromedioRequest(Double preciomediares, Double novillokg, Double totalkg, String date, List<CorteRequest> cortes) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        this.totalkg = totalkg;
        this.cortes = cortes;
        
        if (date != null && !date.isEmpty()) {
            SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");
            miformato.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                this.created = miformato.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException("Error al parsear la fecha: " + e.getMessage(), e);
            }
        }
    }

    // Getters y Setters
    public Double getPreciomediares() {
        return preciomediares;
    }

    public void setPreciomediares(Double preciomediares) {
        this.preciomediares = preciomediares;
    }

    public Double getNovillokg() {
        return novillokg;
    }

    public void setNovillokg(Double novillokg) {
        this.novillokg = novillokg;
    }

    public Double getTotalkg() {
        return totalkg;
    }

    public void setTotalkg(Double totalkg) {
        this.totalkg = totalkg;
    }

    public Double getCostoreal() {  
        return (preciomediares * novillokg) / totalkg;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getTotalcompra() {
        Double costo = getCostoreal();
        return costo * totalkg;
    }

    public List<CorteRequest> getCortes() {
        return cortes;
    }

    public void setCortes(List<CorteRequest> cortes) {
        this.cortes = cortes;
    }
}
