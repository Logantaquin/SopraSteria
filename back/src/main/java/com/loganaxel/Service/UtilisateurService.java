package com.loganaxel.Service;

import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Repository.UtilisateurRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
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
}
