import { Component, OnInit } from '@angular/core';
import {ListeOeuvreService} from "./liste-oeuvre.service";
import {Subject} from "rxjs";
import {Oeuvre} from "./Oeuvre";
import {HttpResponse} from "@angular/common/http";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-liste-oeuvre',
  templateUrl: './liste-oeuvre.component.html',
  styleUrls: ['./liste-oeuvre.component.css']
})
export class ListeOeuvreComponent implements OnInit {
  oeuvres: Subject<Oeuvre[]> = new Subject<Oeuvre[]>()
  oeuvresCompletes: Oeuvre[] = [];
  filtreControl = new FormControl<string>('');

  constructor(private listeOeuvreService: ListeOeuvreService) { }

  ngOnInit(): void {
    this.loadOeuvres();
    this.filtreControl.valueChanges.subscribe(value => this.filtrer(value));
  }

  loadOeuvres(): void {
    this.listeOeuvreService.getAllOeuvres().subscribe({
      next: (response: HttpResponse<Oeuvre[]>) => {
        if (!response.ok || !response.body) {
          console.log("Error: no response");
          return;
        }
        this.oeuvres.next(response.body);
        this.oeuvresCompletes = response.body;
      },
      error: (error) => {
        console.log("Error: " + error);
      }
      });
  }

  filtrer(filtre: string | null): void {
    if (filtre === "" || filtre === null) {
      this.oeuvres.next(this.oeuvresCompletes);
      return;
    }
    this.oeuvres.next(this.oeuvresCompletes.filter(oeuvre => oeuvre.titre.toLowerCase().includes(filtre.toLowerCase())
    // || oeuvre.dateSortie.toDateString().includes(filtre)
    ));
  }
}
