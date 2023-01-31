import {Component, OnInit} from '@angular/core';
import {IAuteur} from "../../shared/model/iauteur";
import {AuteurService} from "../../shared/service/auteur.service";

@Component({
  selector: 'app-liste-auteurs',
  templateUrl: './liste-auteurs.component.html',
  styleUrls: ['./liste-auteurs.component.css']
})
export class ListeAuteursComponent implements OnInit {

  auteurs: IAuteur[] = [];
  readonly displayedColumns: string[] = [
    'nomAuteur',
    'boutonFiche',
    'boutonOeuvres',
  ];

  constructor(
      private auteurService: AuteurService,
  ) { }

  ngOnInit(): void {
    this.auteurService.getAuteurs().subscribe({
      next: (auteurs: IAuteur[]) => {
        this.auteurs = auteurs;
      }
    });
  }

}
