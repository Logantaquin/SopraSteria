package com.loganaxel.Controllers;

import com.loganaxel.Model.Salle;
import com.loganaxel.Service.SalleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {
    private final SalleService SalleService;

    public SalleController(SalleService SalleService) {
        this.SalleService = SalleService;
    }

    // Endpoint pour obtenir tous les Salles
    @GetMapping
    public List<Salle> getAllSalles() {
        return SalleService.getAllSalles();
    }

    // Endpoint pour obtenir un Salle spécifique par ID
    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable String id) {
        return SalleService.getSalleById(id);
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> ajouterSalle(@RequestBody Salle salle) {
        salle.setEstDisponible(true);
        try {
            Salle saved = SalleService.saveSalle(salle);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur : " + e.getMessage());
        }
    }
}
