import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  private apiUrl = 'http://localhost:8080/api/equipes';

  constructor(private http: HttpClient) {}

  getEquipes(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  addMemberToEquipe(idEquipe: string, idMembre: string) {
    const body = { idEquipe, idMembre };
      return this.http.post(`${this.apiUrl}/addMember`, body);
    }

  ajouterEquipe(equipe: { nomEquipe: string, nbJoursPresentiel: number }): Observable<any> {
      return this.http.post(`${this.apiUrl}/ajouter`, equipe);
    }
}
