import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {AuteurService} from "../../shared/service/auteur.service";
import {IAuteur} from "../../shared/model/iauteur";
import {NgForOf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {OeuvreService} from "../../shared/service/oeuvre.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";

@Component({
  selector: 'app-oeuvre-form',
  standalone: true,
  templateUrl: './oeuvre-form.component.html',
  imports: [
    MatInputModule,
    ReactiveFormsModule,
    MatSelectModule,
    NgForOf,
    MatButtonModule,
    MatSnackBarModule
  ],
  styleUrls: ['./oeuvre-form.component.css']
})
export class OeuvreFormComponent implements OnInit {

  auteurs!: IAuteur[];

  formOeuvre: FormGroup = new FormGroup<any>({
    titre: new FormControl<string>('', [Validators.required]),
    anneeSortie: new FormControl<number | null>(null, [Validators.required]),
    isbn: new FormControl<string>('', [Validators.maxLength(13)]),
    auteurs: new FormControl<number[]>([]),
  });

  constructor(
    private oeuvreService: OeuvreService,
    private auteurService: AuteurService,
    private snackbar: MatSnackBar,
  ) {}

  ngOnInit(): void {
    this.auteurService.getAuteurs().subscribe({
      next: (auteurs: IAuteur[]) => {
        this.auteurs = auteurs;
      }
    });
  }

  createOeuvre(): void {
    this.oeuvreService.addOeuvre(
      this.formOeuvre.controls['titre'].value,
      this.formOeuvre.controls['anneeSortie'].value,
      this.formOeuvre.controls['isbn'].value,
      this.formOeuvre.controls['auteurs'].value
    ).subscribe({
      next: () => {
        this.snackbar.open('Oeuvre ajoutée', 'OK', {duration: 5000});
      },
      error: (error: HttpErrorResponse) => {
        this.snackbar.open('Erreur', 'OK', {duration: 5000});
      },
    });
  }

}
