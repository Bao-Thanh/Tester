import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8094/api/auth/';

const httpOptions : any    = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private http: HttpClient) { }
  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'login',
      {
        username,
        password,
      },
      httpOptions
    );
  }
  
  register(username: string, password: string, status: string, role: string, customer:{}): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        password,
        status,
        role,
        customer
      },
      httpOptions
    );
  }
  signout(): Observable<any> {
    return this.http.post(AUTH_API + 'signout', { }, httpOptions);
  }
}
