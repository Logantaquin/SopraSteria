package com.loganaxel.Model.statePattern;

import com.loganaxel.Model.Place;

public class LibreState implements State{
    private Place place;

    public LibreState(Place place){
        this.place = place;
    }

    @Override
    public void occuper(){
        place.setEtat(place.getOccupee());
    }

    @Override
    public void liberer(){
        System.out.println("La place est déjà libre");
    }

    

}
