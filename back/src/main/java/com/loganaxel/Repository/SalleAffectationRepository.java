package com.loganaxel.Repository;

import com.loganaxel.Model.Salle;
import com.loganaxel.Model.SalleAffectation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

public interface SalleAffectationRepository extends MongoRepository<SalleAffectation, String> {
    // Exemple : Trouver une salle par son nom
    SalleAffectation findBySalleId(String Id);
}
