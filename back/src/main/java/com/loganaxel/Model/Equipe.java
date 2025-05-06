package com.loganaxel.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "equipes") // Spécifie que cette classe représente la collection MongoDB "equipes"
public class Equipe {
    @Id
    private String id; // ID unique généré automatiquement par MongoDB

    @Field("nom_equipe") // Personnalise le nom du champ dans la base de données
    private String nomEquipe;

    @Field("nombre_jour_presentiel")
    private int nombreJourPrésentiel;

    @Field("les_membres") // Stocke une liste des IDs des membres (par exemple)
    private List<String> lesMembres; // Liste des IDs des Utilisateurs
    

    public Equipe() {
        // Constructeur par défaut requis par Spring Data
    }

    public Equipe(String nomEquipe, int nombreJourPrésentiel) {
        this.nomEquipe = nomEquipe;
        this.nombreJourPrésentiel = nombreJourPrésentiel;
        this.lesMembres = new ArrayList<>();
    }

    
    // Constructeur pour initialiser une équipe
    public Equipe(String nomEquipe, int nombreJourPrésentiel, List<String> lesMembres) {
        this.nomEquipe = nomEquipe;
        this.nombreJourPrésentiel = nombreJourPrésentiel;
        this.lesMembres = lesMembres;
    }

    // Getters et setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getLesMembres() {
        return lesMembres;
    }

    public void addMembre(String membreId) {
        if (!lesMembres.contains(membreId)) {
            lesMembres.add(membreId);
        }
    }

    public void setLesMembres(List<String> lesMembres) {
        this.lesMembres = lesMembres;
    }
}
