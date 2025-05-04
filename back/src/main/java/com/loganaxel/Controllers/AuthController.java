package com.loganaxel.Controllers;

import com.loganaxel.Controllers.LoginRequest;
import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Utilisateur user = utilisateurService.getUtilisateurByEmail(loginRequest.getEmail());

            if (user != null && user.getMotDePasse().equals(loginRequest.getPassword())) {
                // Trouver l'équipe de l'utilisateur
                Equipe equipe = utilisateurService.getEquipeByUtilisateurId(user.getId());

                if (equipe != null) {
                    // Renvoi de l'utilisateur avec l'ID de l'équipe
                    return ResponseEntity.ok(new LoginResponse(user.getId(), equipe.getId(),user.getAdmin().toString()));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur sans équipe associée");
                }

            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
            }

        } catch (Exception e) {
            e.printStackTrace(); // log dans la console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur serveur : " + e.getMessage());
        }
    }
}

