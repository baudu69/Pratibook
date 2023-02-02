import {HttpClient, HttpResponse} from "@angular/common/http";
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

  public addAuteur(nom: string, prenom: string, dateNaissance: Date | null, dateDeces: Date | null): Observable<void> {
    return this.http.post<void>('/api/stocks/auteur', {
      nomAuteur: nom,
      prenomAuteur: prenom === '' ? null : prenom,
      dateNaissanceAuteur: dateNaissance,
      dateDecesAuteur: dateDeces,
    });
  }

}
