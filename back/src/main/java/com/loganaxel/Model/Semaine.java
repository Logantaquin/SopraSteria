package com.loganaxel.Model;

import java.util.*;

public class Semaine {
    private int numeroSemaine;
    private List<Jour> jours;
    private Map<Equipe, Integer> joursTravaillesParEquipe;

    public Semaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
        this.jours = new ArrayList<>();
        this.joursTravaillesParEquipe = new HashMap<>();
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public List<Jour> getJours() {
        return jours;
    }

    public int getJoursTravailles(Equipe equipe) {
        return joursTravaillesParEquipe.getOrDefault(equipe, 0);
    }

    public void incrementerJoursTravailles(Equipe equipe) {
        joursTravaillesParEquipe.put(equipe, getJoursTravailles(equipe) + 1);
    }

    // Nouvelle méthode pour vérifier si une équipe a atteint son nombre de jours de travail maximum
    public boolean equipeAtteintLimite(Equipe equipe) {
        return getJoursTravailles(equipe) >= equipe.getNombreJourPrésentiel();
    }
}