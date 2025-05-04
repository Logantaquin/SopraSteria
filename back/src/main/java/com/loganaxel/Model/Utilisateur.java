package com.loganaxel.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "utilisateurs") // Lien avec la collection MongoDB "utilisateurs"
public class Utilisateur {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("adresse_mail") // Permet de stocker ce champ sous un nom différent
    private String adresseMail;

    @Field("mot_de_passe")
    private String motDePasse;


    @Field("est_admin")
    private Boolean admin;

    // Constructeur sans argument requis par MongoDB
    public Utilisateur() {
    }

    // Constructeur pour initialiser l'utilisateur avec email et mot de passe
    public Utilisateur(String adresseMail, String motDePasse, Boolean ad) {
        this.adresseMail = adresseMail;
        this.motDePasse = motDePasse;
        this.admin = ad;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
