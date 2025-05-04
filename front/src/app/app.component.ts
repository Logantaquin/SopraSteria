import { RouterModule } from '@angular/router';  // ✅ Importer RouterModule
import { CommonModule } from '@angular/common';  // ✅ Ajout de CommonModule
import { RouterOutlet } from '@angular/router';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import { Component, OnInit } from '@angular/core';
import { PlanningService } from './services/planning.service';
import { HttpClientModule } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterModule,FullCalendarModule,CommonModule,HttpClientModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'my-app';
  selectedDate: string | null = null;  // Pour garder la trace de la date sélectionnée

  calendarOptions = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth', // Vue par défaut : mois
    events: [ // Liste des événements
      { title: 'Équipe A', date: '2025-01-31' },
      { title: 'Équipe B', date: '2025-02-05' },
      { title: 'Réunion', date: '2025-02-10' }
    ],
    dateClick: this.handleDateClick.bind(this)  // Gestion du clic sur un jour
  };

  // Méthode appelée lors du clic sur une date
  handleDateClick(event: any) {
    const clickedDate = event.dateStr;  // Récupère la date cliquée
    this.selectedDate = clickedDate;  // Enregistre la date sélectionnée
    this.openSVGInNewTab();  // Ouvre l'image SVG dans un nouvel onglet
  }

  // Ouvre un SVG du plan de bureau dans un nouvel onglet
  openSVGInNewTab() {
    console.log('Afficher le plan pour le jour sélectionné :', this.selectedDate);

    // Chemin vers ton SVG dans les assets
    const svgUrl = 'assets/Plan_5Bureaux.svg';

    // Ouvre un nouvel onglet avec l'image SVG
    const newWindow = window.open(svgUrl, '_blank');
    if (newWindow) {
      newWindow.focus();  // Assure que le nouvel onglet est mis en avant
    }
  }

 planning: any[] = []; // Stocke le planning reçu du backend

  constructor(private planningService: PlanningService) { }

  ngOnInit() {
    this.fetchPlanning();
  }

  fetchPlanning() {
    this.planningService.getPlanning().subscribe(
      (data) => {
        this.planning = data;
        console.log('Planning récupéré:', data);
      },
      (error) => {
        console.error('Erreur lors de la récupération du planning', error);
      }
    );
  }
}
