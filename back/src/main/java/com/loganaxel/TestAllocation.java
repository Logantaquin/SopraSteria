package com.loganaxel;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Jour;
import com.loganaxel.Model.Salle;
import com.loganaxel.Model.Utilisateur;
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

        // Création des équipes
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe("Equipe 1", 3, creerUtilisateurs(70))); // 70 membres, travaille 3 jours/semaine
        equipes.add(new Equipe("Equipe 2", 2, creerUtilisateurs(80))); // 80 membres, travaille 2 jours/semaine
        equipes.add(new Equipe("Equipe 3", 3, creerUtilisateurs(80))); // 80 membres, travaille 3 jours/semaine

        // Génération des jours de la semaine (lundi au vendredi)
        List<Date> semaine = genererDates("06/02/2025", 5);

        // Lancement de l'algorithme
        List<Jour> planning = service.assignerEquipesAuxJours(equipes, salles, semaine);

        // Affichage du planning
        afficherPlanning(planning);
    }

    private static void afficherPlanning(List<Jour> planning) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for (Jour jour : planning) {
            System.out.println("📅 Date : " + dateFormat.format(jour.getDate()));

            for (Equipe equipe : jour.getEquipes()) {
                System.out.print("  🏢 " + equipe.getNomEquipe() + " (Membres : " + equipe.getLesMembres().size() + ") ");
                System.out.println("→ Assigné à ce jour");
            }

            System.out.println();
        }
    }

    private static List<Utilisateur> creerUtilisateurs(int nombre) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        for (int i = 1; i <= nombre; i++) {
            utilisateurs.add(new Utilisateur("user" + i + "@mail.com", "password"));
        }
        return utilisateurs;
    }

    private static List<Date> genererDates(String startDate, int nbJours) {
        List<Date> dates = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(startDate);

            for (int i = 0; i < nbJours; i++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, i);
                dates.add(cal.getTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dates;
    }
}