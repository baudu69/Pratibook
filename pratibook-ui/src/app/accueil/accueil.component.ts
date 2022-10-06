import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/service/user.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit {

  constructor(public userService: UserService) { }

  ngOnInit(): void {
  }

}
