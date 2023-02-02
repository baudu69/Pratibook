import {Component, Inject} from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {ListeReservationService} from "../liste-reservation.service";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";
import {MatIconModule} from "@angular/material/icon";
import {BarcodeScannerComponent} from "../../../barcode-scanner/barcode-scanner.component";

@Component({
  selector: 'app-valid-reservation',
  standalone: true,
  templateUrl: './valid-reservation.component.html',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatIconModule,
    MatDialogModule
  ],
  styleUrls: ['./valid-reservation.component.css']
})
export class ValidReservationComponent {
  codeBarre: FormControl<string | null> = new FormControl<string>('', [Validators.required])

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { idUser: number },
    private listeReservationService: ListeReservationService,
    private dialogRef: MatDialogRef<ValidReservationComponent>,
    private snackBar: MatSnackBar,
    private dialog: MatDialog
  ) {
  }

  valider() {
    if (this.codeBarre.value == null) {
      return;
    }
    const codeBarre: string = this.codeBarre.value;
    const idUser: number = this.data.idUser;

    this.listeReservationService.validerReservation(codeBarre, idUser).subscribe({
      next: () => {
        this.dialogRef.close()
      },
      error: (err) => {
        this.snackBar.open(err.error.message, 'Fermer', {duration: 5000});
      }
    });
  }

  openQR() {
    this.dialog.open(BarcodeScannerComponent).afterClosed().subscribe((codeBarre: string) => {
      this.codeBarre.setValue(codeBarre);
    })
  }

}
