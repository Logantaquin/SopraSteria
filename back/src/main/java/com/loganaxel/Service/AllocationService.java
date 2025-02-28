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
                if (!semaineCourante.equipeAtteintLimite(equipe)) {
                    List<SalleAffectation> sallesAffectees = allouerSalles(equipe, salles, jourPlanifie);
                    if (!sallesAffectees.isEmpty()) {
                        jourPlanifie.ajouterEquipe(equipe, sallesAffectees);
                        semaineCourante.incrementerJoursTravailles(equipe);
                    }
                }
            }
            planning.add(jourPlanifie);
        }
        return planning;
    }

    private List<SalleAffectation> allouerSalles(Equipe equipe, List<Salle> salles, Jour jour) {
        List<SalleAffectation> affectationsTemp = new ArrayList<>();
        int membresRestants = equipe.getLesMembres().size();
        int totalPlacesDisponibles = salles.stream()
                .filter(salle -> salle.isEstDisponible() && jour.salleDisponible(salle, 1))
                .mapToInt(salle -> salle.getCapacite() - jour.getPlacesUtilisees(salle))
                .sum();

        // ✅ Si on n'a pas assez de place pour toute l'équipe, on annule
        if (totalPlacesDisponibles < membresRestants) {
            // System.out.println("⚠️ Impossible d'affecter l'équipe " + equipe.getNomEquipe() + " (" + membresRestants + " membres) : pas assez de place !");
            return Collections.emptyList();
        }

        for (Salle salle : salles) {
            if (membresRestants <= 0) break;

            if (salle.isEstDisponible() && jour.salleDisponible(salle, 1)) {
                int placesRestantes = salle.getCapacite() - jour.getPlacesUtilisees(salle);
                int placesAffectees = Math.min(placesRestantes, membresRestants);

                if (placesAffectees > 0) {
                    affectationsTemp.add(new SalleAffectation(salle, placesAffectees));
                    membresRestants -= placesAffectees;

                    if (jour.getPlacesUtilisees(salle) >= salle.getCapacite()) {
                        salle.setEstDisponible(false);
                    }
                }
            }
        }

        return affectationsTemp;
    }
}
