package com.loganaxel.Service;

import com.loganaxel.Model.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
    public Utilisateur getUtilisateur() {
        return new Utilisateur("test@example.com", "password123", true);
    }
}