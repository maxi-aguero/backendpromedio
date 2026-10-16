package com.apress.spring;

import java.util.List;

public class CourseResponse {
    private Integer status;
    private String message;
    private String error;

    private Long id;
    private String title;
    private List<LinkResponse> links;

    // Constructor
    public CourseResponse() {
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

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<LinkResponse> getLinks() { return links; }
    public void setLinks(List<LinkResponse> links) { this.links = links; }
}
