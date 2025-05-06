import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { UserService } from '../services/user.service';
import { EquipeService } from '../services/equipe.service';

@Component({
  selector: 'app-add-user',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  equipes: any[] = [];
  selectedEquipeId: string = '';

  newUser = {
    adresseMail: '',
    motDePasse: '',
    admin: false
  };

  constructor(
    private userService: UserService,
    private equipeService: EquipeService
  ) {}

  ngOnInit(): void {
    this.equipeService.getEquipes().subscribe({
      next: (data: any[]) => this.equipes = data,
      error: (err: any) => console.error('Erreur chargement équipes :', err)
    });
  }

  // Ajout de l'utilisateur et ajout à l'équipe après
  addUser(): void {
    console.log('Utilisateur à ajouter :', this.newUser);

    // 1. Ajouter l'utilisateur
    this.userService.addUser(this.newUser).subscribe({
      next: (res: any) => {
        console.log('Utilisateur ajouté :', res);

        // 2. Une fois l'utilisateur ajouté, récupérer son ID et l'ajouter à l'équipe
        this.equipeService.addMemberToEquipe(this.selectedEquipeId, res.id).subscribe({
          next: (equipeRes) => {
            console.log('Membre ajouté à l\'équipe :', equipeRes);
            alert('Utilisateur ajouté et membre ajouté à l\'équipe avec succès.');
          },
          error: (equipeErr) => {
            console.error('Erreur ajout membre à l\'équipe :', equipeErr);
            alert('Erreur lors de l\'ajout du membre à l\'équipe.');
          }
        });
      },
      error: (err: any) => {
        console.error('Erreur ajout utilisateur :', err);
        alert('Erreur lors de l\'ajout de l\'utilisateur.');
      }
    });
  }
}
