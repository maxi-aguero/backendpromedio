package com.apress.spring;

import jakarta.persistence.Transient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PromedioResponse {
    private Integer status;
    private String message;
    private String error;

    private Long id;
    private Double preciomediares;
    private Double novillokg; //kg de la mediares
    private Double totalkg; //kg de la mediares utilizables


    private Date created;
    @Transient
    private final SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");

    public PromedioResponse() {
    }

    public PromedioResponse(Double preciomediares, Double novillokg, String date) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        try {
            this.created = miformato.parse(date);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    // Getters y setters
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
    if (preciomediares == null || novillokg == null || totalkg == null || totalkg == 0.0) return 0.0;
    return (preciomediares * novillokg) / totalkg; 
    }
    
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }


}
