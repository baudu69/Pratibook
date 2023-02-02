import {Component, OnInit} from '@angular/core';
import {registerLocaleData} from "@angular/common";
import localeFr from '@angular/common/locales/fr'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Pratibook';

  ngOnInit(): void {
    registerLocaleData(localeFr, 'fr-FR');
  }

}
