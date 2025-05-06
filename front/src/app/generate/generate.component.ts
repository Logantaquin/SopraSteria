import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EquipeService } from '../services/equipe.service';
import { PlanningService } from '../services/planning.service';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './generate.component.html',
  styleUrls: ['./generate.component.css']
})
export class GenerateComponent implements OnInit {

  dateDebut: string = '';
  dateFin: string = '';
  equipes: any[] = [];
  selectedEquipes: string[] = [];

  constructor(
    private equipeService: EquipeService,
    private planningService: PlanningService
  ) {}

  ngOnInit(): void {
    console.log('UserComponent initialized');
    this.equipeService.getEquipes().subscribe({
      next: (data) => {
        console.log('Réponse API:', data);
        this.equipes = data;
      },
      error: (err) => console.error('Erreur chargement équipes :', err)
    });
  }

  onCheckboxChange(event: any): void {
    const id = event.target.value;
    if (event.target.checked) {
      this.selectedEquipes.push(id);
    } else {
      this.selectedEquipes = this.selectedEquipes.filter(equipeId => equipeId !== id);
    }
  }

  convertDateToFrFormat(date: string): string {
    const [year, month, day] = date.split('-');
    return `${day}/${month}/${year}`;
  }

  generatePlanning(): void {
    const dateDebutFr = this.convertDateToFrFormat(this.dateDebut);
    const dateFinFr = this.convertDateToFrFormat(this.dateFin);

    const requestBody = {
      dateDebut: dateDebutFr,
      dateFin: dateFinFr,
      equipeIds: this.selectedEquipes
    };

    this.planningService.generatePlanning(requestBody).subscribe({
      next: (res) => {
        console.log('Planning généré :', res);
        // Tu peux stocker ou afficher le planning ici
      },
      error: (err) => {
        console.error('Erreur lors de la génération du planning :', err);
      }
    });
  }
}
