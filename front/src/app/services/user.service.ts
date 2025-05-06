import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/utilisateur';

  constructor(private http: HttpClient) {}

  addUser(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, user);
  }

  // user.service.ts
  getUserById(id: string): Observable<any> {
      return this.http.post<{ adresseMail: string }>(`${this.baseUrl}/getById`, { id });
    }



}
