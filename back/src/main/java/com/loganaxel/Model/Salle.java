package com.loganaxel.Model;

<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

public class Salle {
    String nom;
    private Map<String, Place> places;


    public Salle(String n){
        this.nom = n;
        places = new HashMap<>();
    }

    public void addPlace(String id){
        places.put(id, new Place());
    }


    public void occuperPlace(String id){
        if(places.containsKey(id)){
            places.get(id).getOccupee();
        }else{
            System.out.println("La place avec l'ID" + id + " n'existe pas.");
        }
    }

    public void libererPlace(String id){
        if (places.containsKey(id)){
            places.get(id).getLibre();
        }else{
            System.out.println("La place avec l'ID" + id + " n'existe pas.");
        }
=======
public class Salle {
    private String nomSalle;
    private int Capacite;
    private boolean estDisponible;

    public Salle(String nomSalle, int capacite) {
        this.nomSalle = nomSalle;
        Capacite = capacite;
        this.estDisponible = true;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public int getCapacite() {
        return Capacite;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
>>>>>>> a2e3323a1f4e82b6bbe4171ce86c377278698f87
    }
}
