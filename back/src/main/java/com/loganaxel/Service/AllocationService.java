package com.loganaxel.Service;

import com.loganaxel.Model.*;
import com.loganaxel.Repository.JourRepository;
import com.loganaxel.Repository.SalleAffectationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@ComponentScan(basePackages = "com.loganaxel")
public class AllocationService {

    private final JourRepository jourRepository;
    private final SalleAffectationRepository salleAffectationRepository;

    @Autowired
    public AllocationService(JourRepository jourRepository, SalleAffectationRepository salleAffectationRepository) {
        this.jourRepository = jourRepository;
        this.salleAffectationRepository = salleAffectationRepository;
    }

    public List<Jour> assignerEquipesAuxJours(List<Equipe> equipes, List<Salle> salles, List<Date> jours) {
        List<Jour> planning = new ArrayList<>();
        Map<Integer, Semaine> semaines = new HashMap<>();
        int numeroSemaine = -1;

        for (Date jour : jours) {
            salles.forEach(Salle::reinitialiser); // Réinitialiser la disponibilité des salles avant chaque jour

            // Créer un objet Jour pour chaque jour de l'itération
            Jour jourPlanifie = new Jour(jour);
            Calendar cal = Calendar.getInstance();
            cal.setTime(jour);
            int semaineAnnee = cal.get(Calendar.WEEK_OF_YEAR);

            // Si c'est une nouvelle semaine, l'ajouter dans la map des semaines
            if (semaineAnnee != numeroSemaine) {
                numeroSemaine = semaineAnnee;
                semaines.put(numeroSemaine, new Semaine(numeroSemaine));
            }

            Semaine semaineCourante = semaines.get(numeroSemaine);

            // Affectation des équipes aux salles
            for (Equipe equipe : equipes) {
                if (!semaineCourante.equipeAtteintLimite(equipe.getId(), equipe.getNombreJourPrésentiel())) {
                    List<SalleAffectation> sallesAffectees = allouerSalles(equipe, salles, jourPlanifie);

                    if (!sallesAffectees.isEmpty()) {
                        jourPlanifie.ajouterEquipe(equipe.getId(), sallesAffectees);
                        semaineCourante.incrementerJoursTravailles(equipe.getId());
                    }
                }
            }

            planning.add(jourPlanifie);
            jourRepository.save(jourPlanifie); // Sauvegarder le jour affecté dans la base de données
        }

        return planning;
    }

    private List<SalleAffectation> allouerSalles(Equipe equipe, List<Salle> salles, Jour jour) {
        List<SalleAffectation> affectationsTemp = new ArrayList<>();
        int membresRestants = equipe.getLesMembres().size();
        int placesDisponibles = 0;

        // Calculer le nombre total de places disponibles dans toutes les salles
        for (Salle salle : salles) {
            if (salle.isEstDisponible() && jour.salleDisponible(salle.getId(), 1, salle.getCapacite())) {
                placesDisponibles += salle.getCapacite() - jour.getPlacesUtilisees(salle.getId());
            }
        }

        // Si les places disponibles sont insuffisantes pour toute l'équipe, retourner une liste vide
        if (placesDisponibles < membresRestants) {
            return Collections.emptyList();
        }

        // Sinon, procéder à l'allocation des salles
        for (Salle salle : salles) {
            if (membresRestants <= 0) break;

            if (salle.isEstDisponible() && jour.salleDisponible(salle.getId(), 1, salle.getCapacite())) {
                int placesRestantes = salle.getCapacite() - jour.getPlacesUtilisees(salle.getId());
                int placesAffectees = Math.min(placesRestantes, membresRestants);

                if (placesAffectees > 0) {
                    // Affectation de la salle
                    SalleAffectation salleAf = new SalleAffectation(salle.getId(), placesAffectees);
                    SalleAffectation salleSaved = salleAffectationRepository.save(salleAf);
                    affectationsTemp.add(salleSaved);
                    membresRestants -= placesAffectees;

                    // Si la salle est complètement remplie, elle n'est plus disponible
                    if (jour.getPlacesUtilisees(salle.getId()) >= salle.getCapacite()) {
                        salle.setEstDisponible(false);
                    }
                }
            }
        }

        return affectationsTemp;
    }
}
