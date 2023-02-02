import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Oeuvre} from "../../shared/model/oeuvre";

@Injectable({
  providedIn: 'root'
})
export class AddInstanceService {

  constructor(private http: HttpClient) {
  }

  getAllOeuvres(): Observable<Oeuvre[]> {
    return this.http.get<Oeuvre[]>(`/api/oeuvre`);
  }

  addInstance(idOeuvre: number, codeBarre: string): Observable<void> {
    return this.http.post<void>(`/api/stocks/instance-oeuvre`, {oeuvreId: idOeuvre, codeBarre: codeBarre});
  }
}
