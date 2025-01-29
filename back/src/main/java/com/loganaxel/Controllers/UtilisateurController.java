package com.loganaxel.Controllers;

import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Service.UtilisateurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public Utilisateur getUtilisateur() {
        return utilisateurService.getUtilisateur();
    }
}