import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const httpOptions : any    = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
  })
};
const API_URL = 'http://localhost:8094/api/order/';
@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }
  getOrderDetailByOrderId(id: number): Observable<any> {
    return this.http.get(API_URL + 'orderDetail/' + `${id}`);
  }
  createOrder(address: string, phone: string, customer:{}, orderDetail:[]): Observable<any> {
    return this.http.post(
      API_URL + 'create',
      {
        address,
        phone,
        customer,
        orderDetail
      },
      httpOptions
    );
  }
}
