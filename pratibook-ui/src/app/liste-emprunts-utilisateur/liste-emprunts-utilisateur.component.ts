import {Component, OnInit} from '@angular/core';
import {IEmprunt} from "../shared/model/emprunt";
import {EmpruntService} from "../shared/service/emprunt.service";
import {MatTableModule} from "@angular/material/table";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-liste-emprunts-utilisateur',
  standalone: true,
  imports: [
    MatTableModule,
    DatePipe
  ],
  templateUrl: './liste-emprunts-utilisateur.component.html',
  styleUrls: ['./liste-emprunts-utilisateur.component.css']
})
export class ListeEmpruntsUtilisateurComponent implements OnInit {

  displayedColumns: string[] = [
    'codeBarre', 'dateReservation', 'dateLocation', 'dateRenduTheorique',
    'dateRenduReel', 'etatPhysiqueLocation', 'etatPhysiqueRendu'
  ];
  dataSource: IEmprunt[] = [];

  constructor(
    private serviceEmprunt: EmpruntService,
  ) {}

  ngOnInit(): void {
    this.serviceEmprunt.getEmpruntsOfUtilisateur().subscribe({
      next: (emprunts: IEmprunt[]) => {
        this.dataSource = emprunts;
      },
    });
  }

  etatToString(etat: number): string {
    switch (etat) {
      case 0:
        return 'Neuf';
      case 1:
        return 'Bon';
      case 2:
        return 'Abimé';
      case 3:
        return 'Nécessite des réparations';
      default:
        return '-';
    }
  }

}
