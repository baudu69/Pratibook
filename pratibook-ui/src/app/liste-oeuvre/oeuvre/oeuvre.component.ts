import {Component, Input, OnInit} from '@angular/core';
import {Oeuvre} from "../Oeuvre";

@Component({
  selector: 'app-oeuvre',
  templateUrl: './oeuvre.component.html',
  styleUrls: ['./oeuvre.component.css']
})
export class OeuvreComponent implements OnInit {

  @Input()
  oeuvre!: Oeuvre

  constructor() { }

  ngOnInit(): void {
  }


}
