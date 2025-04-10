package com.loganaxel.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "salle_affectations") // Déclare que cette classe représente une collection MongoDB
public class SalleAffectation {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("salle_id") // Référence à l'ID de la salle
    private String salleId;

    @Field("places_utilisees") // Champ pour les places utilisées
    private int placesUtilisees;

    // Constructeur sans arguments requis pour MongoDB
    public SalleAffectation() {
    }

    // Constructeur pour initialiser une SalleAffectation
    public SalleAffectation(String salleId, int placesUtilisees) {
        this.salleId = salleId;
        this.placesUtilisees = placesUtilisees;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalleId() {
        return salleId;
    }

    public void setSalleId(String salleId) {
        this.salleId = salleId;
    }

    public int getPlacesUtilisees() {
        return placesUtilisees;
    }

    public void setPlacesUtilisees(int placesUtilisees) {
        this.placesUtilisees = placesUtilisees;
    }
}
