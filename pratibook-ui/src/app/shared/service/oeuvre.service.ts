import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {IOeuvre} from "../model/oeuvre";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class OeuvreService {

  constructor(private http: HttpClient) {}

  public addOeuvre(titre: string, anneeSortie: number, isbn: string, auteurs: number[]): Observable<void> {
    return this.http.post<void>('/api/stocks/oeuvre', {
      titre: titre,
      anneeSortie: anneeSortie,
      isbn: isbn === '' ? null : isbn,
      auteurs: auteurs,
      genres: [],
    });
  }

}
