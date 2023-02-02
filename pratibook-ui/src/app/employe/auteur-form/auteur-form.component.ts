import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatButtonModule} from "@angular/material/button";
import {AuteurService} from "../../shared/service/auteur.service";
import {Auteur} from "../../shared/model/iauteur";
import {HttpErrorResponse} from "@angular/common/http";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";

@Component({
  selector: 'app-auteur-form',
  standalone: true,
  templateUrl: './auteur-form.component.html',
  imports: [
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  providers: [
    MatDatepickerModule,
  ],
  styleUrls: ['./auteur-form.component.css']
})
export class AuteurFormComponent implements OnInit {

  formAuteur: FormGroup = new FormGroup<any>({
    nom: new FormControl<string>('', [Validators.required]),
    prenom: new FormControl<string>(''),
    dateNaissance: new FormControl<Date | null>(null),
    dateDeces: new FormControl<Date | null>(null),
  });

  constructor(
    private auteurService: AuteurService,
    private snackbar: MatSnackBar,
  ) {}

  ngOnInit(): void {}

  createAuteur(): void {
    this.auteurService.addAuteur(
      this.formAuteur.controls['nom'].value,
      this.formAuteur.controls['prenom'].value,
      this.formAuteur.controls['dateNaissance'].value,
      this.formAuteur.controls['dateDeces'].value
    ).subscribe({
      next: () => {
        this.snackbar.open('Auteur ajouté', 'OK', {duration: 5000});
      },
      error: (err: HttpErrorResponse) => {
        this.snackbar.open('Erreur', 'OK', {duration: 5000});
      },
    });
  }

}
