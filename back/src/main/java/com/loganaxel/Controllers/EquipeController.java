package com.loganaxel.Controllers;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Service.EquipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
