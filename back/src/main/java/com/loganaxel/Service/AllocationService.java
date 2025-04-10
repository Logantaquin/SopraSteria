package com.loganaxel.Service;

import com.loganaxel.Model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AllocationService {

    public List<Jour> assignerEquipesAuxJours(List<Equipe> equipes, List<Salle> salles, List<Date> jours) {
        List<Jour> planning = new ArrayList<>();
        Map<Integer, Semaine> semaines = new HashMap<>();
        int numeroSemaine = -1;

        for (Date jour : jours) {
            salles.forEach(Salle::reinitialiser);

            Jour jourPlanifie = new Jour(jour);
            Calendar cal = Calendar.getInstance();
            cal.setTime(jour);
            int semaineAnnee = cal.get(Calendar.WEEK_OF_YEAR);

            if (semaineAnnee != numeroSemaine) {
                numeroSemaine = semaineAnnee;
                semaines.put(numeroSemaine, new Semaine(numeroSemaine));
            }

            Semaine semaineCourante = semaines.get(numeroSemaine);

            for (Equipe equipe : equipes) {
                if (!semaineCourante.equipeAtteintLimite(equipe.getId(), equipe.getNombreJourPrésentiel())) {
                    List<String> sallesAffecteesIds = allouerSalles(equipe, salles, jourPlanifie);

                    if (!sallesAffecteesIds.isEmpty()) {
                        jourPlanifie.ajouterEquipe(equipe.getId(), sallesAffecteesIds);
                        semaineCourante.incrementerJoursTravailles(equipe.getId());
                    }
                }
            }

            planning.add(jourPlanifie);
            semaineCourante.ajouterJour(jourPlanifie.getId()); // Ajout du jour à la semaine
        }

        return planning;
    }

    private List<String> allouerSalles(Equipe equipe, List<Salle> salles, Jour jour) {
        List<String> affectationsTempIds = new ArrayList<>();
        int membresRestants = equipe.getLesMembres().size();
        int totalPlacesDisponibles = salles.stream()
                .filter(salle -> salle.isEstDisponible() && jour.salleDisponible(salle.getId(), 1, salle.getCapacite()))
                .mapToInt(salle -> salle.getCapacite() - jour.getPlacesUtilisees(salle.getId()))
                .sum();

        // ✅ Si on n'a pas assez de place pour toute l'équipe, on annule
        if (totalPlacesDisponibles < membresRestants) {
            return Collections.emptyList();
        }

        for (Salle salle : salles) {
            if (membresRestants <= 0) break;

            if (salle.isEstDisponible() && jour.salleDisponible(salle.getId(), 1, salle.getCapacite())) {
                int placesRestantes = salle.getCapacite() - jour.getPlacesUtilisees(salle.getId());
                int placesAffectees = Math.min(placesRestantes, membresRestants);

                if (placesAffectees > 0) {
                    affectationsTempIds.add(salle.getId());
                    jour.ajouterPlaceUtilisee(salle.getId(), placesAffectees);
                    membresRestants -= placesAffectees;

                    if (jour.getPlacesUtilisees(salle.getId()) >= salle.getCapacite()) {
                        salle.setEstDisponible(false);
                    }
                }
            }
        }

        return affectationsTempIds;
    }
}
