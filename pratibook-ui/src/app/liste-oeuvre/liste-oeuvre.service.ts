import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Oeuvre} from "./Oeuvre";

@Injectable({
  providedIn: 'root'
})
export class ListeOeuvreService {

  constructor(private http: HttpClient) { }

  getAllOeuvres(): Observable<HttpResponse<Oeuvre[]>> {
    return this.http.get<Oeuvre[]>(`/api/oeuvre`, {observe: 'response'});
  }
}
