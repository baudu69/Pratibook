import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RenduService {

  constructor(private http: HttpClient) {
  }

  rendu(codeBarre: string, etat: number): Observable<void> {
    return this.http.post<void>('/api/location/rendre', {codeBarre: codeBarre, etat: etat});
  }
}
