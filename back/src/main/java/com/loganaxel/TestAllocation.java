package com.loganaxel;

import com.loganaxel.Model.*;
import com.loganaxel.Service.AllocationService;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestAllocation {
    public static void main(String[] args) {
        AllocationService service = new AllocationService();

        // Création des salles
        List<Salle> salles = new ArrayList<>();
        salles.add(new Salle("Salle A", 60));
        salles.add(new Salle("Salle B", 60));
        salles.add(new Salle("Salle C", 40));

        System.out.println("Salles créées : " + salles.size());

        // Création des équipes
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", 3, creerUtilisateurs(70))); // 70 membres, 3 jours/semaine
        equipes.add(new Equipe("Equipe 2", 2, creerUtilisateurs(85))); // 85 membres, 2 jours/semaine
        equipes.add(new Equipe("Equipe 3", 3, creerUtilisateurs(80))); // 80 membres, 3 jours/semaine

        System.out.println("Équipes créées : " + equipes.size());

        // Génération des jours ouvrés entre deux dates
        List<Date> semaine = genererDates("06/01/2025", "24/01/2025"); // Exclut les week-ends et jours fériés

        System.out.println("Jours générés : " + semaine.size());

        // Lancement de l'algorithme
        List<Jour> planning = service.assignerEquipesAuxJours(equipes, salles, semaine);

        // Affichage du planning
        afficherPlanning(planning);
    }

    private static void afficherPlanning(List<Jour> planning) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Jour jour : planning) {
            System.out.println(dateFormat.format(jour.getDate()) + " :");

            for (Map.Entry<Equipe, List<SalleAffectation>> entry : jour.getAffectations().entrySet()) {
                Equipe equipe = entry.getKey();
                List<SalleAffectation> sallesAffectees = entry.getValue();

                // Affichage de l'équipe et des salles affectées
                System.out.print(equipe.getNomEquipe() + " : ");
                boolean first = true;

                for (SalleAffectation affectation : sallesAffectees) {
                    if (!first) {
                        System.out.print(" + ");
                    }
                    first = false;

                    Salle salle = affectation.getSalle();
                    int placesUtilisees = affectation.getPlacesUtilisees();
                    int capaciteSalle = salle.getCapacite();

                    System.out.print(salle.getNomSalle() + " (" + placesUtilisees + "/" + capaciteSalle + ")");
                }
                System.out.println();
            }
            System.out.println();  // Ajouter une ligne vide pour séparer les jours
        }
    }

    private static List<Utilisateur> creerUtilisateurs(int nombre) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        for (int i = 1; i <= nombre; i++) {
            utilisateurs.add(new Utilisateur("user" + i + "@mail.com", "password"));
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