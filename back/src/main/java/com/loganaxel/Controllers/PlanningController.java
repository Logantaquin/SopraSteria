package com.loganaxel.Controllers;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Jour;
import com.loganaxel.Model.Salle;
import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/planning")
public class PlanningController {

    private final AllocationService allocationService;


    public PlanningController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }



    @GetMapping("/generer")
    public List<Jour> genererPlanning() {
        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle("Salle A", 60));
        salles.add(new Salle("Salle B", 60));
        salles.add(new Salle("Salle C", 40));

        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", 3, creerUtilisateurs(70))); // 70 membres, 3 jours/semaine
        equipes.add(new Equipe("Equipe 2", 2, creerUtilisateurs(85))); // 85 membres, 2 jours/semaine
        equipes.add(new Equipe("Equipe 3", 3, creerUtilisateurs(80))); // 80 membres, 3 jours/semaine

        List<Date> jours = genererDates("06/01/2025", "24/01/2025"); // Exclut les week-ends et jours fériés


        return allocationService.assignerEquipesAuxJours(equipes, salles, jours);
    }

    private static List<String> creerUtilisateurs(int nombre) {
        List<String> utilisateurs = new ArrayList<>();
        for (int i = 1; i <= nombre; i++) {
            utilisateurs.add(new Utilisateur("user" + i + "@mail.com", "password", false).getId());
        }
        return utilisateurs;
    }

    private static List<Date> genererDates(String startDate, String endDate) {
        List<Date> dates = new ArrayList<>();
        Set<String> joursFeries = new HashSet<>(Arrays.asList("01/01/2025", "17/04/2025", "01/05/2025", "08/05/2025", "14/07/2025", "15/08/2025", "01/11/2025", "11/11/2025", "25/12/2025"));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(startDate));
            Date fin = sdf.parse(endDate);

            while (!cal.getTime().after(fin)) {
                int jourSemaine = cal.get(Calendar.DAY_OF_WEEK);
                String dateString = sdf.format(cal.getTime());

                // Exclure les week-ends (samedi = 7, dimanche = 1) et jours fériés
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
}