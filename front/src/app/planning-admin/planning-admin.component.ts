import { Component } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Importer RouterModule ici

@Component({
  selector: 'app-planning-admin',
  imports: [FullCalendarModule,CommonModule,RouterModule],
  templateUrl: './planning-admin.component.html',
  styleUrl: './planning-admin.component.css'
})
export class PlanningAdminComponent {

     selectedDate: string | null = null;  // Pour garder la trace de la date sélectionnée

        calendarOptions = {
          plugins: [dayGridPlugin],
          initialView: 'dayGridMonth', // Vue par défaut : mois
          events: [ // Liste des événements
            { title: 'Équipe A', date: '2025-01-31' },
            { title: 'Équipe B', date: '2025-02-05' },
            { title: 'Réunion', date: '2025-02-10' }
          ],
           //dateClick: this.handleDateClick.bind(this)  // Gestion du clic sur un jour
        };
}
