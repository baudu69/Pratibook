import {Component} from '@angular/core';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatRadioModule} from "@angular/material/radio";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {BarcodeScannerComponent} from "../../barcode-scanner/barcode-scanner.component";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {RenduService} from "./rendu.service";
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";

@Component({
  selector: 'app-rendu',
  standalone: true,
  templateUrl: './rendu.component.html',
  imports: [
    ReactiveFormsModule,
    MatRadioModule,
    MatFormFieldModule,
    MatIconModule,
    MatAutocompleteModule,
    MatInputModule,
    MatButtonModule,
    MatDialogModule,
    MatSnackBarModule
  ],
  styleUrls: ['./rendu.component.css']
})
export class RenduComponent {
  formGroup: FormGroup = new FormGroup({
    'etat': new FormControl<number>(0),
    'codeBarre': new FormControl<string>('', [Validators.required]),
  })

  constructor(private dialog: MatDialog, private renduService: RenduService, private snackbar: MatSnackBar) {
  }

  openQR() {
    this.dialog.open(BarcodeScannerComponent).afterClosed().subscribe((res) => {
      if (res) {
        this.formGroup.controls['codeBarre'].setValue(res);
        if (this.formGroup.valid) {
          this.validate()
        }
      }
    })
  }


  validate() {
    const codeBarre: string = this.formGroup.controls['codeBarre'].value;
    const etat: number = this.formGroup.controls['etat'].value;
    this.renduService.rendu(codeBarre, etat).subscribe({
      next: () => {
        this.snackbar.open('Location rendue', 'OK', {duration: 2000});
        this.formGroup.controls['codeBarre'].setValue('');
      },
      error: (err) => {
        this.snackbar.open(err.err.message, 'OK', {duration: 2000});
      }
    })
  }

}
