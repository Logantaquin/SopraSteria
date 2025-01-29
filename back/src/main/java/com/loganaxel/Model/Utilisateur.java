package com.loganaxel.Model;

public class Utilisateur {
    private String adresseMail;
    private String motDePasse;

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
}
