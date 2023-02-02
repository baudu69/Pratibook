import {Component, OnInit} from '@angular/core';
import {AuteurService} from "../../shared/service/auteur.service";
import {ActivatedRoute, ParamMap} from "@angular/router";
import {AuteurWithOeuvres, IAuteur} from "../../shared/model/iauteur";

@Component({
  selector: 'app-fiche-auteur',
  templateUrl: './fiche-auteur.component.html',
  styleUrls: ['./fiche-auteur.component.css']
})
export class FicheAuteurComponent implements OnInit {

  auteurId?: number;
  auteur?: AuteurWithOeuvres;

  constructor(
    private auteurService: AuteurService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      this.auteurId = +params.get('id')!;
      this.loadAuteur();
    });
  }

  private loadAuteur(): void {
    if (!this.auteurId) throw "Invalid auteur ID";

    this.auteurService.getAuteurWithOeuvres(this.auteurId).subscribe({
      next: (auteur: AuteurWithOeuvres) => {
        this.auteur = auteur;
        console.log(JSON.stringify(this.auteur));
      },
    });
  }

}
