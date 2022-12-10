import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ListeOeuvreComponent} from "./liste-oeuvre.component";
import { OeuvreComponent } from './oeuvre/oeuvre.component';
import {RouterModule} from "@angular/router";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    ListeOeuvreComponent,
    OeuvreComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', component: ListeOeuvreComponent}
    ]),
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ]
})
export class ListeOeuvreModule { }
