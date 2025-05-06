package com.loganaxel.Service;

import com.loganaxel.Model.Equipe;
import com.loganaxel.Repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService {
    private final EquipeRepository EquipeRepository;

    public EquipeService(EquipeRepository EquipeRepository) {
        this.EquipeRepository = EquipeRepository;
    }

    // Récupérer un Equipe par ID
    public Equipe getEquipeById(String id) {
        Optional<Equipe> equipe = EquipeRepository.findById(id);
        if (equipe.isPresent()) {
            return equipe.get();  // L'équipe trouvée est renvoyée
        } else {
            throw new RuntimeException("Équipe non trouvée avec l'ID " + id);
        }
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

    public Equipe addMemberToEquipe(String idEquipe, String idMembre) {
        Optional<Equipe> equipeOptional = EquipeRepository.findById(idEquipe);
        if (equipeOptional.isPresent()) {
            Equipe equipe = equipeOptional.get();

            // Utilisation de la méthode addMembre pour ajouter un membre
            equipe.addMembre(idMembre);

            // Enregistrer l'équipe mise à jour
            return EquipeRepository.save(equipe);
        } else {
            throw new RuntimeException("Équipe non trouvée"); // Gérer l'exception selon besoin
        }
    }
}
