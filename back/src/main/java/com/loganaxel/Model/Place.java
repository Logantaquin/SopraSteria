package com.loganaxel.Model;

import com.loganaxel.Model.statePattern.*;

public class Place {
    
    private State occupee;
    private State libre;
    private State etat;

    public Place(){
        occupee = new OccupeState(this);
        libre = new LibreState(this);
    }
    
    public void setEtat(State nouvelEtat) {
        etat = nouvelEtat;
    }

    public State getLibre() {
        return libre;
    }

    public State getOccupee() {
       return occupee; 
    }
}
