import {Component, OnInit} from '@angular/core';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {DemandeReservation} from "./DemandeReservation";
import {ListeReservationService} from "./liste-reservation.service";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {DatePipe} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {ValidReservationComponent} from "./valid-reservation/valid-reservation.component";

@Component({
  selector: 'app-liste-reservation',
  standalone: true,
  templateUrl: './liste-reservation.component.html',
  imports: [
    MatTableModule,
    MatSortModule,
    MatSnackBarModule,
    MatDialogModule,
    DatePipe,
    MatButtonModule,
    MatIconModule
  ],
  styleUrls: ['./liste-reservation.component.css']
})
export class ListeReservationComponent implements OnInit {
  displayedColumns: string[] = ['titre', 'dateDemande', 'idUser', 'nbOeuvres', 'valider'];
  dataSource: DemandeReservation[] = [];

  constructor(
    private listeReservationService: ListeReservationService,
    private snack: MatSnackBar,
    public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.listeReservationService.getListeReservation().subscribe({
      next: (data) => {
        this.dataSource = data;
      },
      error: (err) => {
        this.snack.open("Erreur lors de la récupération des réservations", "OK", {duration: 2000});
      }
    })
  }

  openValidation(idUser: number) {
    this.dialog.open(ValidReservationComponent, {
      data: {
        idUser: idUser
      }
    }).afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

}
