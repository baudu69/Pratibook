import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogModule} from "@angular/material/dialog";
import {Oeuvre} from "../../../shared/model/oeuvre";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {FormControl, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatIconModule} from "@angular/material/icon";
import {BarcodeScannerComponent} from "../../../barcode-scanner/barcode-scanner.component";
import {AddInstanceService} from "../add-instance.service";

@Component({
  selector: 'app-form-add-instance',
  standalone: true,
  templateUrl: './form-add-instance.component.html',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatIconModule,
    FormsModule,
    MatDialogModule
  ],
  styleUrls: ['./form-add-instance.component.css']
})
export class FormAddInstanceComponent {

  codeBarre: FormControl<string | null> = new FormControl<string>('', [Validators.required])
  message: string = "";

  constructor(@Inject(MAT_DIALOG_DATA) public oeuvre: Oeuvre, private dialog: MatDialog, private addInstanceService: AddInstanceService) {
  }

  openQR() {
    this.dialog.open(BarcodeScannerComponent).afterClosed().subscribe((result: string) => {
      this.codeBarre.setValue(result);
      this.valider();
    });
  }

  valider() {
    if (this.codeBarre.invalid || this.codeBarre.value == null) {
      this.message = "Veuillez entrer un code barre valide";
      return;
    }
    this.addInstanceService.addInstance(this.oeuvre.idOeuvre, this.codeBarre.value).subscribe(() => {
      this.message = `${this.codeBarre.value} a bien été ajouté à l'oeuvre ${this.oeuvre.titre}`;
      this.codeBarre.setValue("");
    })
  }
}
