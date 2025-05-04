package com.loganaxel.Repository;

import com.loganaxel.Model.Equipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EquipeRepository extends MongoRepository<Equipe, String> {
    // Méthode personnalisée pour chercher une équipe par son nom
    Equipe findByNomEquipe(String nomEquipe);

    Equipe findByLesMembresContains(String utilisateurId);

}
