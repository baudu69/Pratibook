import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Utilisateur} from "./emprunter.component";

@Injectable({
  providedIn: 'root'
})
export class EmprunterService {

  constructor(private http: HttpClient) {
  }

  loadUser(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>('/api/utilisateur')
  }
}

