package com.loganaxel.Service;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeService {
    private final EquipeRepository EquipeRepository;

    public EquipeService(EquipeRepository EquipeRepository) {
        this.EquipeRepository = EquipeRepository;
    }

    // Récupérer un Equipe par ID
    public Equipe getEquipeById(String id) {
        return EquipeRepository.findById(id).orElse(null);
    }

    // Récupérer tous les Equipes
    public List<Equipe> getAllEquipes() {
        return EquipeRepository.findAll();
    }

    // Ajouter ou mettre à jour un Equipe
    public Equipe saveEquipe(Equipe Equipe) {
        return EquipeRepository.save(Equipe);
    }

    // Supprimer un Equipe par ID
    public void deleteEquipe(String id) {
        EquipeRepository.deleteById(id);
    }
}
