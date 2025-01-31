package com.loganaxel.Model;

public class Salle {
    private String nomSalle;
    private int Capacite;
    private boolean estDisponible;

    public Salle(String nomSalle, int capacite) {
        this.nomSalle = nomSalle;
        Capacite = capacite;
        this.estDisponible = true;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getCapacite() {
        return Capacite;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }
}
