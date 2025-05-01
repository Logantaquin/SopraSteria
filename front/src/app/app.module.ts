import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';  // Importer RouterModule et Routes
import { AppComponent } from './app.component';
import { FullCalendarModule } from '@fullcalendar/angular';  // Pour le calendrier FullCalendar
import { HttpClientModule } from '@angular/common/http';  // Pour l'injection d'HttpClient
import { CommonModule } from '@angular/common'; // Pour utiliser CommonModule dans un module
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router'; // Importer RouterModule ici

// Définir les routes de l'application
const routes: Routes = [
  { path: '', component: AppComponent },  // Route par défaut
  // Ajoute d'autres routes si nécessaire
];

@NgModule({
  declarations: [
    AppComponent,  // Déclaration de ton composant principal
  ],
  imports: [
    BrowserModule,  // Nécessaire pour toute application Angular
    CommonModule,   // Pour utiliser des directives comme ngIf, ngFor
    HttpClientModule, // Nécessaire pour l'injection d'HttpClient
    FullCalendarModule, // Pour FullCalendar
    AppRoutingModule,
    RouterModule,
    RouterModule.forRoot(routes), // Pour activer le routage dans ton application
  ],
  providers: [],
  bootstrap: [AppComponent],  // Composant de démarrage
})
export class AppModule {}
