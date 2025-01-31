package com.loganaxel.Service;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Jour;
import com.loganaxel.Model.Salle;

import java.util.*;

public class AllocationService {
    public List<Jour> assignerEquipesAuxJours(List<Equipe> equipes, List<Salle> salles, List<Date> dates) {
        List<Jour> jours = new ArrayList<>();
        Map<Equipe, List<Date>> calendrierEquipe = new HashMap<>();

        // Étape 1 : Répartition des jours pour chaque équipe (sans hasard)
        Map<Date, Integer> placesRestantes = new HashMap<>();
        for (Date date : dates) {
            placesRestantes.put(date, salles.stream().mapToInt(Salle::getCapacite).sum()); // 160 places totales
        }

        for (Equipe equipe : equipes) {
            List<Date> joursAffectes = new ArrayList<>();
            for (Date date : dates) {
                if (joursAffectes.size() < equipe.getNombreJourPrésentiel() && placesRestantes.get(date) >= equipe.getLesMembres().size()) {
                    joursAffectes.add(date);
                    placesRestantes.put(date, placesRestantes.get(date) - equipe.getLesMembres().size());
                }
            }
            calendrierEquipe.put(equipe, joursAffectes);
        }

        // Étape 2 : Création des jours et assignation des équipes
        for (Date date : dates) {
            Jour jour = new Jour(date);
            List<Equipe> equipesAffectees = new ArrayList<>();

            for (Equipe equipe : equipes) {
                if (calendrierEquipe.get(equipe).contains(date)) {
                    equipesAffectees.add(equipe);
                }
            }

            jour.setEquipes(equipesAffectees);
            jours.add(jour);
        }

        return jours;
    }

}