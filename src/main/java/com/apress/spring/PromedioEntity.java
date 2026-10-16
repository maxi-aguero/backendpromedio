package com.apress.spring;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromedioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double preciomediares;
    private Double kilosmediares;

    private Date fechadecreacion;

    @Transient
    private final SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");


    public PromedioEntity(Double preciomediares, Double kilosmediares, String date) {
        this.preciomediares=preciomediares;
        this.kilosmediares=kilosmediares;
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

    public Double getKilosmediares() {
        return kilosmediares;
    }
    public void setKilosmediares(Double kilosmediares) {
        this.kilosmediares = kilosmediares;
    }

    public Date getCreated() {
        return fechadecreacion;
    }
    public void setCreated(Date fechadecreacion) { this.fechadecreacion = fechadecreacion;
    }


}
