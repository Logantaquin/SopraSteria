package com.loganaxel.Repository;

import com.loganaxel.Model.SalleAffectation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SalleAffectationRepository extends MongoRepository<SalleAffectation, String> {
    // Ajouter des méthodes si nécessaire
}
