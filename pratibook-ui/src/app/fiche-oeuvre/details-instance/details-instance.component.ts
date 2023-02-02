import {Component, Input, OnInit} from '@angular/core';
import {DetailsInstanceService} from "./details-instance.service";
import {InstanceDetails} from "./InstanceDetails";
import {MatTableModule} from "@angular/material/table";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-details-instance',
  standalone: true,
  templateUrl: './details-instance.component.html',
  imports: [
    MatTableModule,
    DatePipe
  ],
  styleUrls: ['./details-instance.component.css']
})
export class DetailsInstanceComponent implements OnInit {

  displayedColumns: string[] = ['codeBarre', 'disponibilite', 'etat', 'nomReserveur', 'prenomReserveur', 'dateRenduTheorique'];

  @Input() idOeuvre!: number;
  instances: InstanceDetails[] = [];

  constructor(private detailInstanceService: DetailsInstanceService) {
  }

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.detailInstanceService.getDetailsInstance(this.idOeuvre).subscribe((response: InstanceDetails[]) => {
      this.instances = response;
    });
  }

}
