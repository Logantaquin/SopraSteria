package com.loganaxel.Model.statePattern;

import com.loganaxel.Model.Place;

public class OccupeState implements State {
    private Place place;

    public OccupeState(Place place){
        this.place = place;
    }

    @Override
    public void occuper(){
        System.out.println("La place est déjà occupée");
    }

    @Override
    public void liberer(){
        this.place.setEtat(place.getLibre());
    }

 
}
