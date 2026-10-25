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
@Table(name = "promedio")
public class PromedioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double preciomediares;
    private Double novillokg; // kg de la mediares
    private Double totalkg; // kg de la mediares utilizables

    private Date created;

    @Transient
    private final SimpleDateFormat miformato = new SimpleDateFormat("MM/dd/yyyy");
   
    // Relación de Maestro-Detalle (Un promedio tiene muchos cortes)
    @OneToMany(mappedBy = "promedio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CorteEntity> cortes = new ArrayList<>();
    
    public PromedioEntity(Double preciomediares, Double novillokg, String date) {
        this.preciomediares = preciomediares;
        this.novillokg = novillokg;
        try {
            this.created = miformato.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public PromedioEntity() {
    }
    
    // Métodos auxiliares para manejar los cortes cómodamente
    public void addCorte(CorteEntity corte) {
        cortes.add(corte);
        corte.setPromedio(this);
    }

    public void removeCorte(CorteEntity corte) {
        cortes.remove(corte);
        corte.setPromedio(null);
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
        return (getCostoreal()*totalkg);
    }
    
    public List<CorteEntity> getCortes() {
        return cortes;
    }

    public void setCortes(List<CorteEntity> cortes) {
        this.cortes = cortes;
    }

}
