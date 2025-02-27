import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';  // Pour le routage dans l'application
import { AppComponent } from './app.component';
import { FullCalendarModule } from '@fullcalendar/angular';  // Pour le calendrier FullCalendar

// Définir les routes (si nécessaire pour votre application)
const routes: Routes = [
  { path: '', component: AppComponent },  // Route par défaut
  // Ajoutez d'autres routes ici si nécessaire
];

@NgModule({
  declarations: [
    AppComponent,  // Déclaration de votre composant principal
  ],
  imports: [
    BrowserModule,       // Nécessaire pour une application Angular
    RouterModule.forRoot(routes),  // Déclaration du routage avec les routes définies plus haut
    FullCalendarModule,  // Ajout du module FullCalendar pour pouvoir utiliser la balise <full-calendar>
  ],
  providers: [],
  bootstrap: [AppComponent],  // Déclaration du composant de démarrage
})
export class AppModule { }
