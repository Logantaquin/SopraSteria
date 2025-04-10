package com.loganaxel;

import com.loganaxel.Model.*;
import com.loganaxel.Repository.*;
import com.loganaxel.Service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ApplicationTests {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private JourRepository jourRepository;

    @Autowired
    private AllocationService allocationService;

    @Test
    public void testUtilisateurRepository() {
        // Création d'un utilisateur
        Utilisateur utilisateur = new Utilisateur("test@example.com", "password123");
        utilisateur.setEstDisponible(true);
        utilisateurRepository.save(utilisateur);

        // Vérification de l'utilisateur enregistré
        Utilisateur fetchedUtilisateur = utilisateurRepository.findByAdresseMail("test@example.com");
        assertThat(fetchedUtilisateur).isNotNull();
        assertThat(fetchedUtilisateur.getAdresseMail()).isEqualTo("test@example.com");

        // Suppression et vérification
        utilisateurRepository.delete(fetchedUtilisateur);
        assertThat(utilisateurRepository.findByAdresseMail("test@example.com")).isNull();
    }

    @Test
    public void testEquipeRepository() {
        // Création d'une équipe
        Equipe equipe = new Equipe("Equipe Test", 2, new ArrayList<>());
        equipeRepository.save(equipe);

        // Vérification de l'équipe enregistrée
        Equipe fetchedEquipe = equipeRepository.findByNomEquipe("Equipe Test");
        assertThat(fetchedEquipe).isNotNull();
        assertThat(fetchedEquipe.getNomEquipe()).isEqualTo("Equipe Test");

        // Suppression et vérification
        equipeRepository.delete(fetchedEquipe);
        assertThat(equipeRepository.findByNomEquipe("Equipe Test")).isNull();
    }

    @Test
    public void testSalleRepository() {
        // Création d'une salle
        Salle salle = new Salle("Salle Test", 50);
        salleRepository.save(salle);

        // Vérification de la salle enregistrée
        Salle fetchedSalle = salleRepository.findByNomSalle("Salle Test");
        assertThat(fetchedSalle).isNotNull();
        assertThat(fetchedSalle.getNomSalle()).isEqualTo("Salle Test");

        // Suppression et vérification
        salleRepository.delete(fetchedSalle);
        assertThat(salleRepository.findByNomSalle("Salle Test")).isNull();
    }

    @Test
    public void testAllocationService() {
        // Création de salles et équipes
        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle("Salle A", 60));
        salles.add(new Salle("Salle B", 60));

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", 3, new ArrayList<>()));
        equipes.add(new Equipe("Equipe 2", 2, new ArrayList<>()));

        // Dates
        List<Date> jours = new ArrayList<>();
        jours.add(new Date());

        // Test de l'assignation
        List<Jour> planning = allocationService.assignerEquipesAuxJours(equipes, salles, jours);
        assertThat(planning).isNotEmpty();
    }
}
