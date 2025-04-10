package com.loganaxel.Repository;

import com.loganaxel.Model.Salle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalleRepository extends MongoRepository<Salle, String> {
    // Exemple : Trouver une salle par son nom
    Salle findByNomSalle(String nomSalle);
}
