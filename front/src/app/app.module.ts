import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'; // RouterModule pour gérer les routes
import { AppComponent } from './app.component';
import { FullCalendarModule } from '@fullcalendar/angular';  // Pour FullCalendar
import { HttpClientModule } from '@angular/common/http';  // Pour l'injection d'HttpClient
import { CommonModule } from '@angular/common'; // Pour utiliser CommonModule dans un module
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';  // Import FormsModule pour utiliser ngModel
import { HomeComponent } from './home/home.component';

// Définir les routes de l'application
const routes = [
  { path: '', component: AppComponent },  // Route par défaut
    // Ajoute d'autres routes si nécessaire
];

@NgModule({
  declarations: [
    AppComponent,  // Déclaration de ton composant principal
    HomeComponent
  ],
  imports: [
    BrowserModule,  // Nécessaire pour toute application Angular
    CommonModule,   // Pour utiliser des directives comme ngIf, ngFor
    HttpClientModule, // Nécessaire pour l'injection d'HttpClient
    FullCalendarModule, // Pour FullCalendar
    AppRoutingModule,  // Si tu as un module pour le routing
    RouterModule.forRoot(routes),  // Routes principales de ton application
    FormsModule,  // Nécessaire pour ngModel
  ],
  providers: [],
  bootstrap: [AppComponent],  // Composant de démarrage
})
export class AppModule {}
