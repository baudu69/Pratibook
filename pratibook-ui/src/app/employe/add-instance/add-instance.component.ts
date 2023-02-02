import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {AddInstanceService} from "./add-instance.service";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatTableDataSource, MatTableModule} from "@angular/material/table";
import {MatSort, MatSortModule} from "@angular/material/sort";
import {MatPaginator, MatPaginatorModule} from "@angular/material/paginator";
import {Oeuvre} from "../../shared/model/oeuvre";
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatDialog, MatDialogModule} from "@angular/material/dialog";
import {FormAddInstanceComponent} from "./form-add-instance/form-add-instance.component";

@Component({
  selector: 'app-add-instance',
  standalone: true,
  templateUrl: './add-instance.component.html',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    MatDialogModule
  ],
  styleUrls: ['./add-instance.component.css']
})
export class AddInstanceComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['titre', 'ajout'];
  oeuvres: MatTableDataSource<Oeuvre>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private addInstanceService: AddInstanceService, private dialog: MatDialog) {
    this.oeuvres = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.loadOeuvres();
  }


  loadOeuvres() {
    this.addInstanceService.getAllOeuvres().subscribe((response) => {
      this.oeuvres.data = response
      this.oeuvres._updateChangeSubscription()
    });
  }

  ngAfterViewInit(): void {
    this.oeuvres.paginator = this.paginator;
    this.oeuvres.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.oeuvres.filter = filterValue.trim().toLowerCase();

    if (this.oeuvres.paginator) {
      this.oeuvres.paginator.firstPage();
    }
  }

  openAjout(oeuvre: Oeuvre) {
    this.dialog.open(FormAddInstanceComponent, {data: oeuvre}).afterClosed().subscribe(() => {
      this.loadOeuvres();
    });
  }
}
