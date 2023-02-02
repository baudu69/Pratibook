import {HttpClient} from "@angular/common/http";
import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {AuteurWithOeuvres, IAuteur} from "../model/iauteur";

@Injectable({
  providedIn: 'root'
})
export class AuteurService {

  constructor(private http: HttpClient) { }

  public getAuteurs(): Observable<IAuteur[]> {
    return this.http.get<IAuteur[]>('/api/auteur');
  }

  public getAuteurWithOeuvres(auteurId: number): Observable<AuteurWithOeuvres> {
    return this.http.get<AuteurWithOeuvres>('/api/auteur/' + auteurId);
  }

}
