package com.loganaxel.Model;

import java.util.*;

public class Jour {
    private Date date;
    private List<Equipe> equipes;
    private Map<Equipe, List<SalleAffectation>> affectations;
    private Map<Salle, Integer> placesUtiliseesParSalle; // Suivi des places utilisées

    public Jour(Date date) {
        this.date = date;
        this.equipes = new ArrayList<>();
        this.affectations = new HashMap<>();
        this.placesUtiliseesParSalle = new HashMap<>();
    }

    public Date getDate() {
        return date;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public Map<Equipe, List<SalleAffectation>> getAffectations() {
        return affectations;
    }

    public void ajouterEquipe(Equipe equipe, List<SalleAffectation> sallesAffectees) {
        equipes.add(equipe);
        affectations.put(equipe, sallesAffectees);

        for (SalleAffectation affectation : sallesAffectees) {
            ajouterPlaceUtilisee(affectation.getSalle(), affectation.getPlacesUtilisees());
        }
    }

    public void ajouterPlaceUtilisee(Salle salle, int placesAffectees) {
        placesUtiliseesParSalle.put(salle, getPlacesUtilisees(salle) + placesAffectees);
    }

    public boolean salleDisponible(Salle salle, int placesRequises) {
        int placesDejaPrises = getPlacesUtilisees(salle);
        boolean disponible = (placesDejaPrises + placesRequises) <= salle.getCapacite();

        // ✅ FIX : Ajout de logs pour mieux voir ce qui se passe
       /* System.out.println("Salle " + salle.getNomSalle() + " - Déjà pris : " + placesDejaPrises +
                ", Demandé : " + placesRequises + ", Disponible : " + disponible);*/

        return disponible;
    }

    public int getPlacesUtilisees(Salle salle) {
        return placesUtiliseesParSalle.getOrDefault(salle, 0);
    }
}
