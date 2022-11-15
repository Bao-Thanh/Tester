import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product/product.service';
import {OrderService} from '../../service/order/order.service';
import { StorageService } from 'src/app/service/storage/storage.service';
import {CustomerService} from '../../service/customer/customer.service'
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  constructor(private productService: ProductService, private orderSerVice : OrderService,
     private storageService:StorageService, private customerService:CustomerService) { }
  errorMessage='';
  products: any[] = [];
  productPrice=0;
  subTotal=0;
  customer:any={};
  amount=1;
  info:any={
    address:null,
    phone:null
  }
  ngOnInit(): void {
    this.products = JSON.parse(localStorage.getItem('cart_items') as any) || [];
    for (var product of this.products) {
      const amount=this.amount;
      const productPrice=amount*product.price;
      this.productPrice = productPrice;
      this.subTotal = this.subTotal + productPrice;
    }
    const id = this.storageService.getUser().id;
    this.customerService.getCustomerByIdAccount(id).subscribe({
      next: data => {
        this.customer=data
        console.log(data);
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
    }
  });
  }
  removeFromCart(product: any) {
    this.productService.removeProduct(product);
    this.products = this.productService.getProduct();

    console.log(this.products);
  }
  changeSubtotal(product: any) {
    const qty = product.quantity;
    const amt = product.price;
    this.subTotal = amt * qty;

    this.productService.saveCart();
  }

  onOrder() {
    let orderDetail:any = []
    
    for (var product of this.products) {
      const amount=this.amount;
      const productPrice=amount*product.price;
      let odd:any={amount,productPrice,product}
      this.productPrice = productPrice;
      this.subTotal = this.subTotal + productPrice;
      orderDetail.push(odd);
    }
    const {address, phone}=this.info;
    if (address!=""&&phone!="") {
      this.orderSerVice.createOrder(address, phone, this.customer, orderDetail).subscribe({
        next: data => {
          console.log(data);
          this.productService.clearProducts();
          window.alert("Successfull");
          window.location.reload();
        },
        error: err => {
        }
      });
    } else this.errorMessage='Please fill info';
  }
}
