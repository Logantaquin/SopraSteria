package com.loganaxel.Model;

import java.util.List;

public class Equipe {
    private List<Utilisateur> lesMembres;
    private String nomEquipe;
    private int nombreJourPrésentiel;

    public Equipe(String nomEquipe, int nombreJourPrésentiel, List<Utilisateur> lesMembres) {
        this.nomEquipe = nomEquipe;
        this.nombreJourPrésentiel = nombreJourPrésentiel;
        this.lesMembres = lesMembres;
    }

    public List<Utilisateur> getLesMembres() {
        return lesMembres;
    }

    public void setLesMembres(List<Utilisateur> lesMembres) {
        this.lesMembres = lesMembres;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public int getNombreJourPrésentiel() {
        return nombreJourPrésentiel;
    }

    public void setNombreJourPrésentiel(int nombreJourPrésentiel) {
        this.nombreJourPrésentiel = nombreJourPrésentiel;
    }
}
