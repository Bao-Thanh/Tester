import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage/storage.service';
import { CustomerService } from '../../service/customer/customer.service';
@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  orders: any = {};
  errorMessage = '';
  orderTotal = 0;
  constructor(
    private customerService: CustomerService,
    private storageService: StorageService
  ) {}
  ngOnInit(): void {
    const id = this.storageService.getUser().id;
    this.customerService.getCustomerByIdAccount(id).subscribe({
      next: (data) => {
        this.orders = data.orderList;
        console.log('=============');
        console.log(data.orderList);
        console.log('=============');
        for (let o of this.orders) {
          for (let p of o) {
            console.log(p.productPrice);
          }
        }
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
      },
    });
  }
}
