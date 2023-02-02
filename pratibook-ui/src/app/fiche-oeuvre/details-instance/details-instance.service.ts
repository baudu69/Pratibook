import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {InstanceDetails} from "./InstanceDetails";

@Injectable({
  providedIn: 'root'
})
export class DetailsInstanceService {

  constructor(private http: HttpClient) {
  }

  getDetailsInstance(idOeuvre: number): Observable<InstanceDetails[]> {
    return this.http.get<InstanceDetails[]>(`/api/oeuvre/${idOeuvre}/instances`);
  }
}
