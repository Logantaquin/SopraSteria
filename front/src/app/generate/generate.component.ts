import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router'; // Importer RouterModule ici

@Component({
  selector: 'app-generate',
  imports: [CommonModule,RouterModule],
  templateUrl: './generate.component.html',
  styleUrl: './generate.component.css'
})
export class GenerateComponent {

}
