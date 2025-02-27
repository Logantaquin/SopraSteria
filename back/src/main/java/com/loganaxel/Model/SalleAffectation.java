package com.loganaxel.Model;

public class SalleAffectation {
    private Salle salle;
    private int placesUtilisees;

    public SalleAffectation(Salle salle, int placesUtilisees) {
        this.salle = salle;
        this.placesUtilisees = placesUtilisees;
    }

    public Salle getSalle() {
        return salle;
    }

    public int getPlacesUtilisees() {
        return placesUtilisees;
    }
}
