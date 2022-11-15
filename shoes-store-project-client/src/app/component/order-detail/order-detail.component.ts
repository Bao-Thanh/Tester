import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {OrderService} from '../../service/order/order.service';
@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  orderDetails : any = {};
  errorMessage='';
  constructor(private orderService:OrderService,private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.orderService.getOrderDetailByOrderId(id).subscribe({
      next: data => {
        console.log(data);
        this.orderDetails=data;
        console.log(this.orderDetails);
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
    }
  });
  }

}
