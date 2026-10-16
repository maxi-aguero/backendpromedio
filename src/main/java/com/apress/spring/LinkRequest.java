package com.apress.spring;

public class LinkRequest {
    private String url;
    private String descripcion;
    // getters y setters
    public LinkRequest(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
