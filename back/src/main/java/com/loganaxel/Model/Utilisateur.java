package com.loganaxel.Model;

public class Utilisateur {
    private String adresseMail;
    private String motDePasse;
    private Equipe equipe;

    public Utilisateur(String adresseMail, String motDePasse) {
        this.adresseMail = adresseMail;
        this.motDePasse = motDePasse;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}
