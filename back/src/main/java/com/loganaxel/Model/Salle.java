package com.loganaxel.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "salles") // Déclare que cette classe correspond à la collection MongoDB "salles"
public class Salle {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("nom_salle") // Définit le champ comme "nom_salle" dans MongoDB
    private String nomSalle;

    @Field("capacite") // Champ avec le même nom
    private int capacite;

    @Field("est_disponible") // Définit le champ comme "est_disponible" dans MongoDB
    private boolean estDisponible;

    // Constructeur par défaut requis pour MongoDB
    public Salle() {
    }

    // Constructeur pour initialiser les données de la salle
    public Salle(String nomSalle, int capacite) {
        this.nomSalle = nomSalle;
        this.capacite = capacite;
        this.estDisponible = true; // Disponible par défaut
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
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
