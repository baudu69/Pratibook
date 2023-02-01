import {Component, OnInit} from '@angular/core';
import Quagga, {QuaggaJSResultObject} from '@ericblade/quagga2'
import {MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";

@Component({
  standalone: true,
  selector: 'app-barcode-scanner',
  templateUrl: './barcode-scanner.component.html',
  imports: [
    MatDialogModule,
    MatButtonModule
  ],
  styleUrls: ['./barcode-scanner.component.css']
})
export class BarcodeScannerComponent implements OnInit {

  constructor(
    private dialogRef: MatDialogRef<BarcodeScannerComponent>,
  ) {
    dialogRef.disableClose = true;
  }

  ngOnInit(): void {
    Quagga.init({
        inputStream: {
          target: '#scanner',
          constraints: {
          },
        },
        decoder: {
          readers: ['ean_reader']
        },
      },
      (err) => {
        if (err) {
          console.error(`QuaggaJS could not be initialized, err: ${err}`);
        } else {
          Quagga.start();
          Quagga.onDetected((res: QuaggaJSResultObject) => {
            this.dialogRef.close(res.codeResult.code);
          })
        }
      }
    );
  }

  cancel() {
    this.dialogRef.close(null)
  }
}
