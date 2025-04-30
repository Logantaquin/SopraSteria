package com.loganaxel;

import com.loganaxel.Model.*;
import com.loganaxel.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import java.util.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.loganaxel")
public class TestAllocation implements CommandLineRunner {

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private SalleAffectationRepository salleAffectationRepository;

    @Autowired
    private JourRepository jourRepository;

    @Autowired
    private SemaineRepository semaineRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestAllocation.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer des salles
        Salle salle1 = new Salle("Salle 1", 50);
        Salle salle2 = new Salle("Salle 2", 30);
        salleRepository.save(salle1);
        salleRepository.save(salle2);

        // Créer des équipes avec les nouveaux attributs
        Equipe equipe1 = new Equipe("Equipe 1", 5, Arrays.asList("utilisateur1", "utilisateur2", "utilisateur3"));
        Equipe equipe2 = new Equipe("Equipe 2", 6, Arrays.asList("utilisateur4", "utilisateur5"));
        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);

        // Créer une affectation pour chaque salle
        SalleAffectation affectation1 = new SalleAffectation(salle1.getId(), 0);
        SalleAffectation affectation2 = new SalleAffectation(salle2.getId(), 0);
        salleAffectationRepository.save(affectation1);
        salleAffectationRepository.save(affectation2);

        // Créer un jour pour l'affectation
        Jour jour = new Jour(new Date());
        jour.ajouterEquipe(equipe1.getId(), Arrays.asList(affectation1.getId()));
        jour.ajouterEquipe(equipe2.getId(), Arrays.asList(affectation2.getId()));
        jour.ajouterPlaceUtilisee(salle1.getId(), 20);
        jour.ajouterPlaceUtilisee(salle2.getId(), 10);
        jourRepository.save(jour);

        // Créer une semaine
        Semaine semaine = new Semaine(1);
        semaine.ajouterJour(jour.getId());
        semaineRepository.save(semaine);

        // Affichage des résultats dans le terminal
        afficherAllocation();
    }

    private void afficherAllocation() {
        System.out.println("Affichage des allocations :");

        // Récupérer les jours
        List<Jour> jours = jourRepository.findAll();

        for (Jour j : jours) {
            System.out.println("Date du jour : " + j.getDate());
            for (String equipeId : j.getEquipes()) {
                Equipe equipe = equipeRepository.findById(equipeId).orElse(null);
                if (equipe != null) {
                    System.out.println("Equipe : " + equipe.getNomEquipe());
                    List<String> sallesAffectees = j.getAffectations().get(equipeId);
                    for (String salleId : sallesAffectees) {
                        SalleAffectation affectation = salleAffectationRepository.findById(salleId).orElse(null);
                        if (affectation != null) {
                            Salle salle = salleRepository.findById(affectation.getSalleId()).orElse(null);
                            if (salle != null) {
                                System.out.println("Salle : " + salle.getNomSalle() + " (places utilisées : " + affectation.getPlacesUtilisees() + ")");
                            } else {
                                System.out.println("Salle : introuvable (ID = " + affectation.getId() + ")");
                            }
                        }
                    }
                }
            }
        }
    }
}