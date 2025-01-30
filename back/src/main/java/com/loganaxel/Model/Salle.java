package com.loganaxel.Model;

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
    }
}
