import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from "@angular/common/http";
import {Observable, Subject} from "rxjs";
import {LoginResponse} from "../model/loginResponse";
import {SignUpRequest} from "../model/signUpRequest";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  public token?: string
  public isConnected: boolean = false
  public isAdmin: boolean = false
  public isEmploye: boolean = false
  public userInfo: Subject<LoginResponse> = new Subject<LoginResponse>();
  public connected: Subject<boolean> = new Subject<boolean>();
  public admin: Subject<boolean> = new Subject<boolean>();

  constructor(private http: HttpClient) {

    this.userInfo.subscribe((res) => {
      this.token = res.jwt
      this.connected.next(true)
      this.admin.next(res.roles.includes('ADMIN'))
      this.isAdmin = res.roles.includes('ADMIN')
      this.isEmploye = res.roles.includes('Employe')
      this.isConnected = true
      localStorage.setItem('userInfo', JSON.stringify(res))
      localStorage.setItem("exp", res.exp.toString())
    })
    const userInfo: string | null = localStorage.getItem('userInfo')
    if (userInfo !== null) {
      const dateStr: string = localStorage.getItem("exp") || ''
      const dateExp: Date = new Date(Date.parse(dateStr))
      if (dateExp > new Date()) {
        const loginResponse: LoginResponse = JSON.parse(userInfo) as LoginResponse
        this.userInfo.next(loginResponse)
      }
    }
  }

  private login(email: string, password: string): Observable<HttpResponse<LoginResponse>> {
    return this.http.post<LoginResponse>(`/api/auth/authenticate`, {
      email: email,
      password: password
    }, {observe: "response"})
  }

  public register(user: SignUpRequest): Observable<HttpResponse<void>> {
    return this.http.post<void>(`/api/auth/register`, user, {observe: "response"})
  }

  private static getDateExp(): Date {
    const date: Date = new Date();
    date.setDate(date.getDate() + 1)
    return date
  }

  public loginUser(email: string, password: string): Promise<string> {
    return new Promise<string>((resolve) => {
      this.login(email, password).subscribe((res: HttpResponse<LoginResponse>) => {
        if (!res.ok || !res.body) {
          resolve("Erreur")
          return
        }
        this.userInfo.next(res.body)
      })
    })

  }


  public logout(): void {
    this.connected.next(false)
    this.admin.next(false)
    this.isConnected = false
    this.isEmploye = false
    localStorage.clear()
  }
}
