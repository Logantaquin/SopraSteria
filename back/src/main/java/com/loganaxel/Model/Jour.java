package com.loganaxel.Model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "jours") // Représente la collection MongoDB "jours"
public class Jour {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("date") // Champ pour la date
    private Date date;

    @Field("equipes") // Stocke une liste des IDs des équipes
    private List<String> equipes; // IDs des équipes au lieu des objets `Equipe`

    @Field("affectations") // Stocke les affectations sous une structure simplifiée
    private Map<String, List<SalleAffectation>> affectations; // Map : ID d'équipe -> Liste des IDs de SalleAffectation

    @Field("places_utilisees") // Suivi des places utilisées
    private Map<String, Integer> placesUtiliseesParSalle; // Map : ID de Salle -> Places utilisées

    // Constructeur par défaut requis par MongoDB
    public Jour() {
    }

    // Constructeur pour initialiser la classe
    public Jour(Date date) {
        this.date = date;
        this.equipes = new ArrayList<>();
        this.affectations = new HashMap<>();
        this.placesUtiliseesParSalle = new HashMap<>();
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<String> equipes) {
        this.equipes = equipes;
    }

    public Map<String, List<SalleAffectation>> getAffectations() {
        return affectations;
    }

    public void setAffectations(Map<String, List<SalleAffectation>> affectations) {
        this.affectations = affectations;
    }

    public Map<String, Integer> getPlacesUtiliseesParSalle() {
        return placesUtiliseesParSalle;
    }

    public void setPlacesUtiliseesParSalle(Map<String, Integer> placesUtiliseesParSalle) {
        this.placesUtiliseesParSalle = placesUtiliseesParSalle;
    }

    // Méthode pour ajouter une équipe et ses affectations
    public void ajouterEquipe(String equipeId, List<SalleAffectation> sallesAffectees) {
        equipes.add(equipeId);
        affectations.put(equipeId, sallesAffectees);

        for (SalleAffectation affectation : sallesAffectees) {
            ajouterPlaceUtilisee(affectation.getSalleId(), affectation.getPlacesUtilisees());
        }

    }

    public void ajouterPlaceUtilisee(String id, int placesAffectees) {
        placesUtiliseesParSalle.put(id, getPlacesUtilisees(id) + placesAffectees);
    }

    public boolean salleDisponible(String salleId, int placesRequises, int capaciteSalle) {
        int placesDejaPrises = getPlacesUtilisees(salleId);
        return (placesDejaPrises + placesRequises) <= capaciteSalle;
    }

    public int getPlacesUtilisees(String salleId) {
        return placesUtiliseesParSalle.getOrDefault(salleId, 0);
    }
}
