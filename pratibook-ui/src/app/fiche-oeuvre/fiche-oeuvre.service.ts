import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Oeuvre} from "../liste-oeuvre/Oeuvre";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FicheOeuvreService {

  constructor(private http: HttpClient) {
  }

  loadOeuvre(idOeuvre: number): Observable<Oeuvre> {
    return this.http.get<Oeuvre>(`api/oeuvre/${idOeuvre}`);
  }

  reserverOeuvre(idOeuvre: number): Observable<void> {
    return this.http.get<void>(`api/reservation/demande/${idOeuvre}`);
  }
}
