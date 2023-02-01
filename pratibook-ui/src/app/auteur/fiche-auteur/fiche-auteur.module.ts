import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {FicheAuteurComponent} from "./fiche-auteur.component";


@NgModule({
  declarations: [
    FicheAuteurComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild([
      {path: '', redirectTo: '/', pathMatch: 'full'},
      {path: ':id', component: FicheAuteurComponent},
    ]),
  ]
})
export class FicheAuteurModule {
}
