package com.loganaxel.Controllers;

import com.loganaxel.Model.Jour;
import com.loganaxel.Model.Salle;
import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.SalleAffectation;
import com.loganaxel.Repository.EquipeRepository;
import com.loganaxel.Repository.JourRepository;
import com.loganaxel.Repository.SalleAffectationRepository;
import com.loganaxel.Repository.SalleRepository;
import com.loganaxel.Service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/planning")
public class PlanningController {

    private final AllocationService allocationService;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private SalleRepository salleRepository;

    @Autowired
    private JourRepository jourRepository;

    @Autowired
    private SalleAffectationRepository salleAffectationRepository;

    public PlanningController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @GetMapping
    public List<Jour> getAllPlanning() {
        // Retourner tous les jours dans la base de données
        return jourRepository.findAll();
    }

    @GetMapping("/generer")
    public List<Jour> genererPlanning() {
        List<Equipe> equipes = equipeRepository.findAll();

        List<Salle> salles = salleRepository.findAll();

        // Génération des jours ouvrés (utilisation des dates simulées)
        List<Date> jours = genererDates("06/01/2025", "24/01/2025");

        // Lancement de l'algorithme d'affectation

        return allocationService.assignerEquipesAuxJours(equipes, salles, jours);
    }

    @PostMapping("/generer")
    public List<Jour> genererPlanning(@RequestBody GenerationRequest request) {
        // Récupérer les équipes sélectionnées
        List<Equipe> equipes = equipeRepository.findAllById(request.getEquipeIds());

        // Charger toutes les salles
        List<Salle> salles = salleRepository.findAll();

        // Générer les dates à partir des paramètres
        List<Date> jours = genererDates(request.getDateDebut(), request.getDateFin());

        // Appeler l'algorithme d'affectation
        return allocationService.assignerEquipesAuxJours(equipes, salles, jours);
    }

    // Méthode pour simuler la création des salles
    private List<Salle> creerSalles() {
        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle("Salle A", 60));
        salles.add(new Salle("Salle B", 60));
        salles.add(new Salle("Salle C", 40));
        return salles;
    }

    // Méthode pour simuler la création des équipes
    private List<Equipe> creerEquipes() {
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", 3, creerUtilisateurs(70))); // 70 membres, 3 jours/semaine
        equipes.add(new Equipe("Equipe 2", 2, creerUtilisateurs(85))); // 85 membres, 2 jours/semaine
        equipes.add(new Equipe("Equipe 3", 3, creerUtilisateurs(80))); // 80 membres, 3 jours/semaine
        return equipes;
    }

    // Méthode pour simuler la création des utilisateurs
    private List<String> creerUtilisateurs(int nombre) {
        List<String> utilisateurs = new ArrayList<>();
        for (int i = 1; i <= nombre; i++) {
            // Simulation de la création d'utilisateur avec ID
            utilisateurs.add("user" + i + "@mail.com"); // Remplace par une logique MongoDB si nécessaire
        }
        return utilisateurs;
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
                    SalleAffectation salleAf = new SalleAffectation(salle.getId(),placesAffectees);
                    SalleAffectation salleSaved = salleAffectationRepository.save(salleAf);
                    affectationsTempIds.add(salleSaved.getId());
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
