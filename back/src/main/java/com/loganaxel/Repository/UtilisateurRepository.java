package com.loganaxel.Repository;

import com.loganaxel.Model.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {


    Utilisateur findByAdresseMail(String adresseMail); // Méthode pour trouver l'utilisateur par son email



    // Rechercher des utilisateurs avec un mot de passe spécifique (juste un exemple, à éviter en production pour des raisons de sécurité)
    List<Utilisateur> findByMotDePasse(String motDePasse);

    // Rechercher tous les utilisateurs triés par adresse e-mail (par ordre alphabétique)
    List<Utilisateur> findAllByOrderByAdresseMailAsc();

    List<Utilisateur> findByAdmin(boolean admin);
}
