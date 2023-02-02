import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IEmprunt} from "../model/emprunt";

@Injectable({
  providedIn: 'root'
})
export class EmpruntService {

  constructor(private http: HttpClient) { }

  public getEmpruntsOfUtilisateur(): Observable<IEmprunt[]> {
    return this.http.get<IEmprunt[]>('/api/utilisateur/emprunts');
  }

}
