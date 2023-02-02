import {Component} from '@angular/core';

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.css']
})
export class EmployeComponent {
  liens = [
    {label: 'Réservation', url: '/employe/reservation'},
    {label: 'Emprunter', url: '/employe/emprunter'},
    {label: 'Rendu', url: '/employe/rendu'},
    {label: 'Ajout instance', url: '/employe/instance/add'},
  ]
}
