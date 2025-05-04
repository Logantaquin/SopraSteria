package com.loganaxel.Service;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Repository.EquipeRepository;
import com.loganaxel.Repository.UtilisateurRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;
    private final EquipeRepository equipeRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, EquipeRepository equipeRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.equipeRepository = equipeRepository;
    }

    // Récupérer un utilisateur par ID
    public Utilisateur getUtilisateurById(String id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Ajouter ou mettre à jour un utilisateur
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Supprimer un utilisateur par ID
    public void deleteUtilisateur(String id) {
        utilisateurRepository.deleteById(id);
    }

    public Equipe getEquipeByUtilisateurId(String utilisateurId) {
        // Recherche dans l'équipe ayant cet utilisateur
        return equipeRepository.findByLesMembresContains(utilisateurId); // Exemple avec un champ "membresId" dans l'équipe
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        try {
            return utilisateurRepository.findByAdresseMail(email);  // Vérifie que cette méthode est bien définie dans ton repository
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de l'utilisateur", e);
        }
    }


}
