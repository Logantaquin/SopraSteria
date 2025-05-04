package com.loganaxel;

import com.loganaxel.Model.*;
import com.loganaxel.Repository.*;
import com.loganaxel.Service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@ComponentScan(basePackages = "com.loganaxel")
public class TestAllocation implements CommandLineRunner {

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private SalleAffectationRepository salleAffectationRepository;

    @Autowired
    private JourRepository jourRepository;

    @Autowired
    private SemaineRepository semaineRepository;

    @Autowired
    AllocationService service;

    public static void main(String[] args) {
        SpringApplication.run(TestAllocation.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Créer des salles
        Salle salle1 = new Salle("Salle 1", 60);
        Salle salle2 = new Salle("Salle 2", 60);
        Salle salle3 = new Salle("Salle 3", 40);
        salleRepository.save(salle1);
        salleRepository.save(salle2);
        salleRepository.save(salle3);

        // Créer des équipes avec les nouveaux attributs
        Equipe equipe1 = new Equipe("Equipe 1", 3, creerUtilisateurs(70));
        Equipe equipe2 = new Equipe("Equipe 2", 2, creerUtilisateurs(85));
        Equipe equipe3 = new Equipe("Equipe 3", 3, creerUtilisateurs(80));
        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);
        equipeRepository.save(equipe3);

        // génération de journée
        List<Date> jours = genererDates("06/01/2025", "24/01/2025");

        // effectue l'affectation
        List<Jour> planning = service.assignerEquipesAuxJours(equipeRepository.findAll(), salleRepository.findAll(),jours);

        // Affichage des résultats dans le terminal
        afficherPlanning();
    }

    // Méthode pour générer les dates entre deux périodes
    private List<Date> genererDates(String startDate, String endDate) {
        List<Date> dates = new ArrayList<>();
        Set<String> joursFeries = new HashSet<>(Arrays.asList(
                "01/01/2025", "17/04/2025", "01/05/2025", "08/05/2025",
                "14/07/2025", "15/08/2025", "01/11/2025", "11/11/2025", "25/12/2025"
        ));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(startDate));
            Date fin = sdf.parse(endDate);

            while (!cal.getTime().after(fin)) {
                int jourSemaine = cal.get(Calendar.DAY_OF_WEEK);
                String dateString = sdf.format(cal.getTime());

                // Exclure les week-ends et jours fériés
                if (jourSemaine != Calendar.SATURDAY && jourSemaine != Calendar.SUNDAY && !joursFeries.contains(dateString)) {
                    dates.add(cal.getTime());
                }

                cal.add(Calendar.DAY_OF_MONTH, 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dates;
    }

    private List<String> creerUtilisateurs(int nombre) {
        List<String> utilisateurs = new ArrayList<>();
        for (int i = 1; i <= nombre; i++) {
            Utilisateur user = new Utilisateur("user" + i + "@mail.com", "password",false);
            Utilisateur savedUser = utilisateurRepository.save(user);
            utilisateurs.add(savedUser.getId());
        }
        return utilisateurs;
    }

    private void afficherPlanning() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Récupérer les jours
        List<Jour> jours = jourRepository.findAll();

        for (Jour jour : jours) {
            System.out.println(dateFormat.format(jour.getDate()) + " :");

            for (Map.Entry<String, List<SalleAffectation>> entry : jour.getAffectations().entrySet()) {
                Equipe equipe = equipeRepository.findById(entry.getKey()).orElse(null);
                List<SalleAffectation> sallesAffectees = entry.getValue();

                // Affichage de l'équipe et des salles affectées
                System.out.print(equipe.getNomEquipe() + " : ");
                boolean first = true;

                for (SalleAffectation affectation : sallesAffectees) {
                    if (!first) {
                        System.out.print(" + ");
                    }
                    first = false;

                    Salle salle = salleRepository.findById(affectation.getSalleId()).orElse(null);
                    int placesUtilisees = affectation.getPlacesUtilisees();
                    int capaciteSalle = salle.getCapacite();

                    System.out.print(salle.getNomSalle() + " (" + placesUtilisees + "/" + capaciteSalle + ")");
                }
                System.out.println();
            }
            System.out.println();  // Ajouter une ligne vide pour séparer les jours
        }
    }
}