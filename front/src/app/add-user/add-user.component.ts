import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-add-user',
  imports: [CommonModule,RouterModule, FormsModule],
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent {
  email: string = '';
  password: string = '';
  confirmPassword: string = '';
  equipe: string = '';

  constructor(private http: HttpClient) {}

  ajouterUtilisateur() {
    if (this.password !== this.confirmPassword) {
      alert('Les mots de passe ne correspondent pas !');
      return;
    }

    const utilisateur = {
      email: this.email,
      password: this.password,
      equipe: this.equipe
    };

    this.http.post('http://localhost:8080/api/add', utilisateur).subscribe({
      next: () => alert('Utilisateur ajouté avec succès !'),
      error: (err) => alert('Erreur : ' + err.message)
    });
  }

}
