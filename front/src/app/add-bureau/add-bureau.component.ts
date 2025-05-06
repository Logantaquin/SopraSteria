import { Component } from '@angular/core';
import { SalleService } from '../services/salle.service';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-bureau',
  standalone: true,
  templateUrl: './add-bureau.component.html',
  styleUrls: ['./add-bureau.component.css'],
  imports: [CommonModule, FormsModule,RouterModule],
})
export class AddBureauComponent {
  nomSalle: string = '';
  capacite: number | null = null;

  constructor(private salleService: SalleService, private router: Router) {}

  ajouterSalle(): void {
    if (!this.nomSalle || this.capacite === null) {
      alert("Veuillez remplir tous les champs.");
      return;
    }

    const nouvelleSalle = {
      nomSalle: this.nomSalle,
      capacite: this.capacite
    };

    this.salleService.ajouterSalle(nouvelleSalle).subscribe(
      () => {
        alert("Salle ajoutée avec succès !");
        this.nomSalle = '';
        this.capacite = null;
      },
      error => {
        console.error("Erreur lors de l'ajout de la salle :", error);
        alert("Échec de l'ajout.");
      }
    );
  }
}
