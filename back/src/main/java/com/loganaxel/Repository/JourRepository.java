package com.loganaxel.Repository;

import com.loganaxel.Model.Jour;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface JourRepository extends MongoRepository<Jour, String> {
    // Exemple : Trouver des jours dans une plage de dates
    List<Jour> findByDateBetween(Date startDate, Date endDate);
}

