import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class SalleService {
  private apiUrl = 'http://localhost:8080/api/salles'; // adapte si besoin

  constructor(private http: HttpClient) {}

  ajouterSalle(salle: { nomSalle: string, capacite: number }): Observable<any> {
    return this.http.post(`${this.apiUrl}/ajouter`, salle);
  }
}
