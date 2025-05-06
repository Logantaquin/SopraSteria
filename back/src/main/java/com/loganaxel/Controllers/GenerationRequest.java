package com.loganaxel.Controllers;

import java.util.List;

public class GenerationRequest {
    private String dateDebut;
    private String dateFin;
    private List<String> equipeIds;

    // Getters et setters

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public List<String> getEquipeIds() {
        return equipeIds;
    }

    public void setEquipeIds(List<String> equipeIds) {
        this.equipeIds = equipeIds;
    }
}
