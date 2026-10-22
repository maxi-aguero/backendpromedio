package com.apress.spring;

import jakarta.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromedioRequest {

    private Double preciomediares;
    private Double novillokg; //kg de la mediares
    private Double totalkg; //kg de la mediares utilizables


    private Date fechadecreacion;
    @Transient
    private final SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");


    public PromedioRequest(){}
    public PromedioRequest(Double preciomediares, Double novillokg, String date) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        try {
            this.fechadecreacion = miformato.parse(date);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

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

    //costo real : preciomediares / totalkg
    public Double getCostoreal() { return (preciomediares/totalkg); }


    public Date getCreated() {
        return fechadecreacion;
    }
    public void setCreated(Date fechadecreacion) {
        this.fechadecreacion = fechadecreacion;
    }


}
