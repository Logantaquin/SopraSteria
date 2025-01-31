import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,FullCalendarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'my-app';
  calendarOptions = {
      plugins: [dayGridPlugin],
      initialView: 'dayGridMonth', // Vue par défaut : mois
      events: [ // Liste des annotations
        { title: 'Équipe A', date: '2025-01-31' },
        { title: 'Équipe B', date: '2025-02-05' },
        { title: 'Réunion', date: '2025-02-10' }
      ]
    };
}
