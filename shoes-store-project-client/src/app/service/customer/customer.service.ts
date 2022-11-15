import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const httpOptions : any    = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
const API_URL = 'http://localhost:8094/api/customer/';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  constructor(private http: HttpClient) { }
  getCustomerByIdAccount(id: number): Observable<any> {
    return this.http.get(API_URL + 'profile-detail/' + `${id}`);
  }
  updateCustomer(name:string, age:number, gender: string, email: string, phone: string, address: string, type:number, status: string, id:number): Observable<any> {
   //console.log("b",customer)
    return this.http.put(API_URL + `${id}` ,{
      name, age, gender, email, phone, address, type, status,
    },
    httpOptions
  );
  }
}
