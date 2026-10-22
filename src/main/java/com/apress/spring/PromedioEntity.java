package com.apress.spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class PromedioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double preciomediares;
    private Double novillokg; //kg de la mediares
    private Double totalkg; //kg de la mediares utilizables


    private Date fechadecreacion;

    //costo real : preciomediares / totalkg

    @Transient
    private final SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");


    public PromedioEntity(Double preciomediares, Double novillokg, String date) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        try {
            this.fechadecreacion = miformato.parse(date);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public PromedioEntity() {
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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