import { Component } from '@angular/core';
import { EquipeService } from '../services/equipe.service';  // Service pour gérer les équipes
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-equipe',
  standalone: true,
  templateUrl: './add-equipe.component.html',  // Mise à jour du nom du fichier HTML
  styleUrls: ['./add-equipe.component.css'],
  imports: [CommonModule, FormsModule, RouterModule],
})
export class AddEquipeComponent {
  nomEquipe: string = '';  // Nom de l'équipe
  nbJoursPresentiel: number | null = null;  // Nombre de jours de présentiel

  constructor(private equipeService: EquipeService, private router: Router) {}

  ajouterEquipe(): void {
    if (!this.nomEquipe || this.nbJoursPresentiel === null) {
      alert("Veuillez remplir tous les champs.");
      return;
    }

    const nouvelleEquipe = {
      nomEquipe: this.nomEquipe,
      nbJoursPresentiel: this.nbJoursPresentiel
    };

    // Appel au service pour ajouter l'équipe
    this.equipeService.ajouterEquipe(nouvelleEquipe).subscribe(
      () => {
        alert("Équipe ajoutée avec succès !");
        this.nomEquipe = '';  // Réinitialisation des champs
        this.nbJoursPresentiel = null;
      },
      error => {
        console.error("Erreur lors de l'ajout de l'équipe :", error);
        alert("Échec de l'ajout.");
      }
    );
  }
}
