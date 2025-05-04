package com.loganaxel.Controllers;

import com.loganaxel.Model.Salle;
import com.loganaxel.Service.SalleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
