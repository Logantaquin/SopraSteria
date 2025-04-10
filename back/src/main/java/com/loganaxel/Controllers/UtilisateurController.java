package com.loganaxel.Controllers;

import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Service.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Endpoint pour obtenir tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Endpoint pour obtenir un utilisateur spécifique par ID
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable String id) {
        return utilisateurService.getUtilisateurById(id);
    }
}
