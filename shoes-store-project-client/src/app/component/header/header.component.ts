import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { StorageService } from 'src/app/service/storage/storage.service';
import {ProductService} from '../../service/product/product.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  dynamicVariable=true;
  constructor(private productService : ProductService, private storageService : StorageService,
    private authService:AuthService, private _route: Router) { }

  products : any ={};
  isLoggedIn=false;
  isAdmin=false;
  isUser=false;
  ngOnInit(): void {
    this.products=this.productService.getProduct();
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      if (this.storageService.getUser().role[0]==='ROLE_ADMIN'){
        this.isAdmin=true;
        this.isUser=false;
      }else {
        this.isAdmin=false;
        this.isUser=true;
      }
    }

  }
  removeFromCart(product: any) {
    this.productService.removeProduct(product);
    this.products = this.productService.getProduct();

    console.log(this.products);
  }
  onLogout(){
    this.authService.signout().subscribe({
      next: data => {
        this.isLoggedIn = false;
        window.sessionStorage.clear();
        this.productService.clearProducts();
        this._route.navigate(['/login']);
        this.isAdmin=false;
        this.isUser=false;
      },
      error: err => {
      }
    });
  }
}
