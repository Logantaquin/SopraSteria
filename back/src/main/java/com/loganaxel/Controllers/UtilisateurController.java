package com.loganaxel.Controllers;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/getById")
    public ResponseEntity<?> getUtilisateurById(@RequestBody Map<String, String> request) {
        String id = request.get("id"); // Extraire l'ID du corps de la requête

        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            if (utilisateur != null) {
                return ResponseEntity.ok(utilisateur);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Utilisateur avec l'ID " + id + " non trouvé");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur interne : " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public Utilisateur addUser(@RequestBody Utilisateur user) {
        // On pourrait faire des validations ici si nécessaire
        return utilisateurService.saveUtilisateur(user);
    }
}
