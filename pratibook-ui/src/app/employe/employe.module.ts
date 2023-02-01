import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {employeRoute} from "./employe.route";


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forChild(employeRoute),
  ]
})
export class EmployeModule {
}
