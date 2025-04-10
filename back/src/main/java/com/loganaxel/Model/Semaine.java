package com.loganaxel.Model;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "semaines") // Représente la collection MongoDB "semaines"
public class Semaine {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("numero_semaine") // Numéro de la semaine dans l'année
    private int numeroSemaine;

    @Field("jours") // Liste d'IDs des jours
    private List<String> jours; // Référence aux IDs des documents "Jour"

    @Field("jours_travailles_par_equipe") // Jours travaillés par équipe via IDs
    private Map<String, Integer> joursTravaillesParEquipe; // Map : ID d'équipe -> Nombre de jours travaillés

    // Constructeur sans argument requis par MongoDB
    public Semaine() {
    }

    // Constructeur pour initialiser une semaine
    public Semaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
        this.jours = new ArrayList<>();
        this.joursTravaillesParEquipe = new HashMap<>();
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumeroSemaine() {
        return numeroSemaine;
    }

    public void setNumeroSemaine(int numeroSemaine) {
        this.numeroSemaine = numeroSemaine;
    }

    public List<String> getJours() {
        return jours;
    }

    public void setJours(List<String> jours) {
        this.jours = jours;
    }

    public Map<String, Integer> getJoursTravaillesParEquipe() {
        return joursTravaillesParEquipe;
    }

    public void setJoursTravaillesParEquipe(Map<String, Integer> joursTravaillesParEquipe) {
        this.joursTravaillesParEquipe = joursTravaillesParEquipe;
    }

    public int getJoursTravailles(String equipeId) {
        return joursTravaillesParEquipe.getOrDefault(equipeId, 0);
    }

    public void incrementerJoursTravailles(String equipeId) {
        joursTravaillesParEquipe.put(equipeId, getJoursTravailles(equipeId) + 1);
    }

    // Vérifie si une équipe a atteint son nombre de jours maximum
    public boolean equipeAtteintLimite(String equipeId, int nombreJourPrésentiel) {
        return getJoursTravailles(equipeId) >= nombreJourPrésentiel;
    }

    // Ajout d'un jour (référence par ID)
    public void ajouterJour(String jourId) {
        this.jours.add(jourId);
    }
}
