import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    const loginData = {
      email: this.email,
      password: this.password
    };

    this.authService.login(loginData).subscribe(
      (response: { userId: string, equipeId: string, isAdmin: boolean }) => {
        console.log('Connexion réussie', response);

        // Stockage local
        localStorage.setItem('userId', response.userId);
        localStorage.setItem('equipeId', response.equipeId);
        localStorage.setItem('isAdmin', String(response.isAdmin));

        // Redirection selon le rôle
        if (response.isAdmin) {
          this.router.navigate(['/planning-admin']);
        } else {
          this.router.navigate(['/planning']);
        }
      },
      (error) => {
        console.error('Erreur de connexion', error);
        // TODO: Ajouter une gestion d'affichage d'erreur si besoin
      }
    );
  }
}
