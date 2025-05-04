import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Injection globale du service
})
export class AddUserService {
  private apiUrl = 'http://localhost:8080/api/add'; // URL du backend

  constructor(private http: HttpClient) {}

  // Méthode pour envoyer un utilisateur au backend
  ajouterUtilisateur(utilisateur: any): Observable<any> {
    return this.http.post(this.apiUrl, utilisateur);
  }
}