package com.loganaxel.Controllers;

import com.loganaxel.Model.Utilisateur;
import com.loganaxel.Repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/add")
@CrossOrigin(origins = "http://localhost:4200") // Permet l'accès depuis Angular
public class AddController {

    private final UtilisateurRepository utilisateurRepository;

    public AddController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @PostMapping
    public ResponseEntity<String> ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.findByAdresseMail(utilisateur.getAdresseMail()) != null) {
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé !");
        }

        // Enregistrer l'utilisateur
        utilisateurRepository.save(utilisateur);
        return ResponseEntity.ok("Utilisateur ajouté avec succès !");
    }
}