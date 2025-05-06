package com.loganaxel.Controllers;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Service.EquipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {
    private final EquipeService EquipeService;

    public EquipeController(EquipeService EquipeService) {
        this.EquipeService = EquipeService;
    }

    // Endpoint pour obtenir tous les Equipes
    @GetMapping
    public List<Equipe> getAllEquipes() {
        return EquipeService.getAllEquipes();
    }

    // Endpoint pour obtenir un Equipe spécifique par ID
    @GetMapping("/{id}")
    public Equipe getEquipeById(@PathVariable String id) {
        return EquipeService.getEquipeById(id);
    }

    @PostMapping("/addMember")
    public Equipe addMemberToEquipe(@RequestBody AddMemberRequest request) {
        return EquipeService.addMemberToEquipe(request.getIdEquipe(), request.getIdMembre());
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterEquipe(@RequestBody Equipe equipe) {
        try {
            equipe.setLesMembres(new ArrayList<>());
            Equipe savedEquipe = EquipeService.saveEquipe(equipe);
            return ResponseEntity.ok(savedEquipe);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de l'équipe: " + e.getMessage());
        }
    }

}
