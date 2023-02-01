import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DemandeReservation} from "./DemandeReservation";

@Injectable({
  providedIn: 'root'
})
export class ListeReservationService {

  constructor(private http: HttpClient) {
  }

  getListeReservation(): Observable<DemandeReservation[]> {
    return this.http.get<DemandeReservation[]>('api/reservation');
  }

  validerReservation(codeBarre: string, idUser: number): Observable<void> {
    return this.http.post<void>('api/reservation', {codeBarre, idUser})
  }
}
