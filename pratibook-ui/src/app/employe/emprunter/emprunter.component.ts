import {Component, OnInit} from '@angular/core';
import {MatRadioModule} from "@angular/material/radio";
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {AsyncPipe, NgForOf} from "@angular/common";
import {map, Observable, startWith, tap} from "rxjs";
import {EmprunterService} from "./emprunter.service";
import {MatButtonModule} from "@angular/material/button";

export class Utilisateur {
  id!: number;
  email!: string;
  nom!: string;
  prenom!: string;
  adresse!: string;
  codePostal!: string;
  ville!: string;

  toString(): string {
    return this.nom + ' ' + this.prenom;
  };
}

@Component({
  selector: 'app-emprunter',
  standalone: true,
  templateUrl: './emprunter.component.html',
  imports: [
    MatRadioModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatInputModule,
    AsyncPipe,
    NgForOf,
    MatButtonModule,
    FormsModule
  ],
  styleUrls: ['./emprunter.component.css']
})
export class EmprunterComponent implements OnInit {

  formGroup: FormGroup = new FormGroup({
    'etat': new FormControl<number>(0),
    'codeBarre': new FormControl<string>('', [Validators.required]),
    'idUser': new FormControl<string>("", [Validators.required])
  })
  utilisateurs: Utilisateur[] = []
  filteredOptions: Observable<Utilisateur[]> = this.formGroup.controls['idUser'].valueChanges.pipe(
    startWith(''),
    map(value => this._filter(value || '')),
    tap((res) => console.log(res))
  );

  constructor(private emprunterService: EmprunterService) {

  }

  ngOnInit(): void {
    this.emprunterService.loadUser().subscribe((res) => {
      this.utilisateurs = res;
    })
  }

  validate() {
    if (this.formGroup.controls['etat'].value === null
      || this.formGroup.controls['codeBarre'].value === null
      || this.formGroup.controls['idUser'].value === null) {
      return;
    }
    const etat: number = this.formGroup.controls['etat'].value;
    const codeBarre: string = this.formGroup.controls['codeBarre'].value;
    const idUser: number = this.formGroup.controls['idUser'].value;
  }

  private _filter(value: string): Utilisateur[] {
    const filterValue = value.toLowerCase();

    return this.utilisateurs.filter(user =>
      user.nom.toLowerCase().includes(filterValue)
      || user.prenom.toLowerCase().includes(filterValue)
    );
  }


}
