package com.loganaxel.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

    public class Jour {
        private Date date;
        private List<Equipe> equipes; // Plusieurs équipes assignées ce jour-là
        private List<Salle> salles; // Liste des salles utilisées

        public Jour(Date date) {
            this.date = date;
            this.equipes = new ArrayList<>();
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<Equipe> getEquipes() {
            return equipes;
        }

        public void setEquipes(List<Equipe> equipes) {
            this.equipes = equipes;
        }

        public List<Salle> getSalles() {
            return salles;
        }

        public void setSalles(List<Salle> salles) {
            this.salles = salles;
        }

        public void ajouterEquipe(Equipe equipe){
            this.equipes.add(equipe);
        }
    }
