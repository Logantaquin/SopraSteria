import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
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
}
