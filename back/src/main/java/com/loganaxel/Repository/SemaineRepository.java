package com.loganaxel.Repository;

import com.loganaxel.Model.Semaine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SemaineRepository extends MongoRepository<Semaine, String> {
    // Exemple : Trouver une semaine par son numéro
    Semaine findByNumeroSemaine(int numeroSemaine);
}
