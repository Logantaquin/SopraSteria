import { Component, OnInit } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PlanningService } from '../services/planning.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-planning',
  standalone: true,
  imports: [FullCalendarModule, CommonModule, RouterModule],
  templateUrl: './planning.component.html',
  styleUrls: ['./planning.component.css']
})
export class PlanningComponent implements OnInit {
  calendarOptions: any = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    events: [],
    eventMouseEnter: this.onEventMouseEnter.bind(this),
    eventMouseLeave: this.onEventMouseLeave.bind(this)
  };

  equipesMap: Map<string, string> = new Map();
  sallesMap: Map<string, string> = new Map();
  tooltipEl: HTMLElement | null = null;

  currentEquipeId: string = '';
  currentUserEmail: string = '';
  currentDateFr: string = '';
  salleDuJour: string[] = [];

  constructor(
    private planningService: PlanningService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    // Formatage de la date en français
    const today = new Date();
    this.currentDateFr = new Intl.DateTimeFormat('fr-FR', {
      weekday: 'long',
      day: 'numeric',
      month: 'long',
      year: 'numeric'
    }).format(today);

    const userId = localStorage.getItem('userId');
    if (userId) {
      this.userService.getUserById(userId).subscribe({
        next: (user) => {
          this.currentUserEmail = user?.adresseMail || 'Utilisateur inconnu';
        },
        error: (err) => {
          this.currentUserEmail = 'Utilisateur inconnu';
          console.error('Erreur lors de la récupération de l\'utilisateur:', err);
        }
      });
    } else {
      this.currentUserEmail = 'Utilisateur inconnu';
    }

    // Récupération de l'ID d'équipe
    this.currentEquipeId = localStorage.getItem('equipeId') || '';

    if (this.currentEquipeId) {
      this.loadPlanningData(today);
    } else {
      console.error("L'ID de l'équipe n'a pas été trouvé.");
    }
  }

  loadPlanningData(today: Date): void {
    this.planningService.getEquipes().subscribe(equipes => {
      equipes.forEach(e => this.equipesMap.set(e.id, e.nomEquipe));

      this.planningService.getSalles().subscribe(salles => {
        salles.forEach(s => this.sallesMap.set(s.id, s.nomSalle));

        this.planningService.getPlanning().subscribe(planning => {
          const filtered = this.filterPlanningForEquipe(planning, this.currentEquipeId);
          const events = this.transformPlanningToEvents(filtered);
          this.calendarOptions.events = events;

          // Salle du jour (aujourd'hui seulement)
          const todayStr = today.toISOString().split('T')[0];
          const todayPlanning = filtered.find(p => p.date === todayStr);
          if (todayPlanning) {
            this.salleDuJour = todayPlanning.affectations[this.currentEquipeId]
              .map((a: any) => this.sallesMap.get(a.salleId) || a.salleId);
          }
        });
      });
    });
  }

  filterPlanningForEquipe(planning: any[], equipeId: string): any[] {
    return planning
      .filter(jour => jour.affectations && jour.affectations[equipeId])
      .map(jour => {
        return {
          ...jour,
          affectations: {
            [equipeId]: jour.affectations[equipeId]
          }
        };
      });
  }

  transformPlanningToEvents(planning: any[]): any[] {
    const events: any[] = [];

    planning.forEach(jour => {
      const date = jour.date;
      const affectations = jour.affectations;

      Object.keys(affectations).forEach(equipeId => {
        const equipeNom = this.equipesMap.get(equipeId) || equipeId;
        const affectationsDetail = affectations[equipeId];

        const salleInfos = affectationsDetail.map((a: any) => {
          const nomSalle = this.sallesMap.get(a.salleId) || a.salleId;
          return `${nomSalle} (${a.placesUtilisees})`;
        });

        const title = `${equipeNom} - ${salleInfos.join(', ')}`;

        events.push({
          title,
          start: date,
          allDay: true,
          extendedProps: {
            salleInfos: salleInfos.join(', ')
          }
        });
      });
    });

    return events;
  }

  onEventMouseEnter(info: any): void {
    const tooltip = document.createElement('div');
    tooltip.className = 'custom-tooltip';
    tooltip.style.position = 'absolute';
    tooltip.style.zIndex = '10001';
    tooltip.style.background = '#fff';
    tooltip.style.border = '1px solid #ccc';
    tooltip.style.padding = '5px';
    tooltip.style.boxShadow = '0 0 5px rgba(0,0,0,0.2)';
    tooltip.innerText = info.event.extendedProps.salleInfos;

    document.body.appendChild(tooltip);
    this.tooltipEl = tooltip;

    const rect = info.el.getBoundingClientRect();
    tooltip.style.top = `${rect.top + window.scrollY - 40}px`;
    tooltip.style.left = `${rect.left + window.scrollX}px`;
  }

  onEventMouseLeave(): void {
    if (this.tooltipEl) {
      this.tooltipEl.remove();
      this.tooltipEl = null;
    }
  }
}
