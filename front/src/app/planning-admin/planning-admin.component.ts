import { Component, OnInit } from '@angular/core';
import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { PlanningService } from '../services/planning.service';

@Component({
  selector: 'app-planning-admin',
  standalone: true,
  imports: [FullCalendarModule, CommonModule, RouterModule],
  templateUrl: './planning-admin.component.html',
  styleUrl: './planning-admin.component.css'
})
export class PlanningAdminComponent implements OnInit {
  calendarOptions: any = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    events: [],
    eventMouseEnter: this.onEventMouseEnter.bind(this),
    eventMouseLeave: this.onEventMouseLeave.bind(this),
  };

  equipesMap: Map<string, string> = new Map(); // ID -> Nom équipe
  sallesMap: Map<string, string> = new Map(); // ID -> Nom salle
  tooltipEl: HTMLElement | null = null;

  constructor(private planningService: PlanningService) {}

  ngOnInit(): void {
    this.planningService.getEquipes().subscribe(equipes => {
      equipes.forEach(e => this.equipesMap.set(e.id, e.nomEquipe));

      this.planningService.getSalles().subscribe(salles => {
        salles.forEach(s => this.sallesMap.set(s.id, s.nomSalle));

        this.planningService.getPlanning().subscribe(planning => {
          const events = this.transformPlanningToEvents(planning);
          this.calendarOptions.events = events;
        });
      });
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
    tooltip.style.padding = '10px';
    tooltip.style.boxShadow = '0 0 5px rgba(0,0,0,0.2)';
    tooltip.style.maxWidth = '250px';
    tooltip.style.whiteSpace = 'normal';
    tooltip.style.fontSize = '12px';
    tooltip.style.lineHeight = '1.4';
    tooltip.style.borderRadius = '5px';
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
