package com.loganaxel.Service;

import com.loganaxel.Model.Salle;
import com.loganaxel.Repository.SalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalleService {
    private final SalleRepository SalleRepository;

    public SalleService(SalleRepository SalleRepository) {
        this.SalleRepository = SalleRepository;
    }

    // Récupérer un Salle par ID
    public Salle getSalleById(String id) {
        return SalleRepository.findById(id).orElse(null);
    }

    // Récupérer tous les Salles
    public List<Salle> getAllSalles() {
        return SalleRepository.findAll();
    }

    // Ajouter ou mettre à jour un Salle
    public Salle saveSalle(Salle Salle) {
        return SalleRepository.save(Salle);
    }

    // Supprimer un Salle par ID
    public void deleteSalle(String id) {
        SalleRepository.deleteById(id);
    }
}
