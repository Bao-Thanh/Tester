import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../service/product/product.service';
import { OrderService } from '../../service/order/order.service';
import { StorageService } from 'src/app/service/storage/storage.service';
import { CustomerService } from '../../service/customer/customer.service';
import { AuthService } from 'src/app/service/auth/auth.service';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private orderSerVice: OrderService,
    private storageService: StorageService,
    private customerService: CustomerService,
    private authService: AuthService
  ) {}
  errorMessage = '';
  products: any[] = [];
  productPrice = 0;
  subTotal = 0;
  changedSubtotal = 0;
  customer: any = {};
  amount = 1;
  info: any = {
    address: null,
    phone: null,
  };
  //for user
  isLoggedIn = false;
  isAdmin = false;
  isUser = false;
  ngOnInit(): void {
    this.products = [...this.productService.getProduct()];
    for (var product of this.products) {
      const amount = product.amount;
      const productPrice = amount * product.price;
      this.productPrice = productPrice;
      this.subTotal = this.subTotal + this.productPrice;
    }
    const id = this.storageService.getUser().id;
    this.customerService.getCustomerByIdAccount(id).subscribe({
      next: (data) => {
        this.customer = data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
      },
    });
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      if (this.storageService.getUser().role[0] === 'ROLE_ADMIN') {
        this.isAdmin = true;
        this.isUser = false;
      } else {
        this.isAdmin = false;
        this.isUser = true;
      }
    }
  }
  removeFromCart(product: any) {
    this.productService.removeProduct(product);
    this.products = this.productService.getProduct();

    console.log(this.products);
  }
  changeSubtotal(product: any) {
    this.subTotal = 0;
    for (var p of this.products) {
      if (p.id === product.id) {
        console.log('==========xxxxxxxxx');
        console.log(product);
        const qty = product.amount;
        const price = product.price;
        this.productPrice = qty * price;
        this.subTotal += this.productPrice;
      } else {
        console.log('==========');
        console.log(p);
        this.productPrice = p.amount * p.price;
        this.subTotal += this.productPrice;
      }
    }

    this.productService.saveCart();
  }

  onOrder() {
    let orderDetail: any = [];

    for (var product of this.products) {
      const amount = product.amount;
      const productPrice = amount * product.price;
      let odd: any = { amount, productPrice, product };
      this.productPrice = productPrice;
      this.subTotal = this.subTotal + productPrice;
      orderDetail.push(odd);
    }
    const { address, phone } = this.info;
    if (address === null) {
      this.errorMessage = 'Address is required';
    } else if (phone === null) {
      this.errorMessage = 'Phone is required';
    } else if (address !== null && phone !== null) {
      this.orderSerVice
        .createOrder(address, phone, this.customer, orderDetail)
        .subscribe({
          next: (data) => {
            console.log(data);
            this.productService.clearProducts();
            window.alert('Successfull');
            window.location.reload();
          },
          error: (err) => {},
        });
    }
  }
}
