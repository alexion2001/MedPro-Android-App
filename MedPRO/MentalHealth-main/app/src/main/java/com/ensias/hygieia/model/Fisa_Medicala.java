package com.ensias.hygieia.model;


import java.util.Date;

public class Fisa_Medicala {
    private String diagnostic;
    private String description;
    private String tratament;
    private String type;
    private Date dateCreated;
    private String doctor;

    public Fisa_Medicala(){

    }

    public Fisa_Medicala(String diagnostic, String description, String tratament, String type, String doctor) {
        this.diagnostic = diagnostic;
        this.description = description;
        this.tratament = tratament;
        this.type = type;
        this.doctor = doctor;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTratament() {
        return tratament;
    }

    public void setTratament(String tratament) {
        this.tratament = tratament;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Date getDateCreated() { return dateCreated; }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}

