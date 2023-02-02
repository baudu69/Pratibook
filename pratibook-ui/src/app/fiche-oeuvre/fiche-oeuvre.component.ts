import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {Oeuvre} from "../liste-oeuvre/Oeuvre";
import {FicheOeuvreService} from "./fiche-oeuvre.service";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {AsyncPipe, NgForOf, NgIf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {UserService} from "../shared/service/user.service";


@Component({
  selector: 'app-fiche-oeuvre',
  standalone: true,
  templateUrl: './fiche-oeuvre.component.html',
  imports: [
    AsyncPipe,
    MatSnackBarModule,
    NgIf,
    NgForOf,
    MatButtonModule,
    RouterLink
  ],
  styleUrls: ['./fiche-oeuvre.component.css']
})
export class FicheOeuvreComponent implements OnInit {

  idOeuvre?: number
  oeuvre?: Oeuvre;

  constructor(
    private route: ActivatedRoute,
    private ficheOeuvreService: FicheOeuvreService,
    private snackbar: MatSnackBar,
    public userService: UserService
  ) {
  }


  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.idOeuvre = Number(params.get('idOeuvre'));
      this.loadOeuvre();
    });
  }

  loadOeuvre(): void {
    this.ficheOeuvreService.loadOeuvre(this.idOeuvre!!).subscribe({
      next: (oeuvre: Oeuvre) => {
        this.oeuvre = oeuvre;
      },
      error: (err) => {
        this.snackbar.open('Erreur lors du chargement de l\'oeuvre', 'OK', {duration: 5000});
      }
    });
  }

  reserver(): void {
    this.ficheOeuvreService.reserverOeuvre(this.idOeuvre!!).subscribe({
      next: () => {
        this.snackbar.open('Réservation effectuée', 'OK', {duration: 5000});
      },
      error: (err) => {
        this.snackbar.open('Erreur, cette oeuvre n\'est plus disponible ou a déjà été réservée', 'OK', {duration: 5000});
      }
    })
  }
}
