package com.loganaxel.Model;

public class Salle {
    private String nomSalle;
    private int capacite;
    private boolean estDisponible;

    public Salle(String nomSalle, int capacite) {
        this.nomSalle = nomSalle;
        this.capacite = capacite;
        this.estDisponible = true;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    // Méthode pour réinitialiser la disponibilité de la salle
    public void reinitialiser() {
        this.estDisponible = true;
    }
}
