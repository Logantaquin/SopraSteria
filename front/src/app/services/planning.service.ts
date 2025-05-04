import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlanningService {
  private apiUrl = 'http://localhost:8080/api/planning/generer'; // URL du backend

  constructor(private http: HttpClient) {}

  getPlanning(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getPlanningPourEquipe(equipeId: string): Observable<any[]> {
    return this.http.get<any[]>(`http://localhost:8080/api/planning/equipe/${equipeId}`);
  }

  getEquipes(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/equipes'); // adapte le chemin
  }

  getSalles(): Observable<any[]> {
    return this.http.get<any[]>('http://localhost:8080/api/salles'); // adapte l'URL si besoin
  }

}
