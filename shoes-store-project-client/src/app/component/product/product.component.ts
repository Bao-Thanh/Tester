import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/service/category/category.service';
import { ProductService } from '../../service/product/product.service';
import { CartService } from '../../service/storage/cart.service';
//import {Product} from '../../mock-test/product'
@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  //products={}
  productlists: any = {};
  products: any = {};
  pros: any[] = [];
  category: any = {};
  items: any = [];
  errorMessage = '';
  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.categoryService.getAllCategory().subscribe({
      next: (data) => {
        this.category = data;
        //this.products=data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
      },
    });
    this.productService.getAllProduct().subscribe({
      next: (data) => {
        this.productlists = data;
        this.products = data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
      },
    });
  }
  ngOnClick(event: any) {
    if (event.target.value == 'all') this.products = this.productlists;
    else {
      this.categoryService.getCategoryByID(event.target.value).subscribe({
        next: (data) => {
          this.products = data.productList;
          console.log(data);
        },
        error: (err) => {
          console.log(err);
          this.errorMessage = err.error.message;
        },
      });
    }
  }

  addToCart(product: any) {
    if (!this.productService.productInCart(product)) {
      product.amount = 1;
      this.productService.addToCart(product);
      alert('Add to cart successfully!');
    } else {
      product.amount += 1;
      this.productService.updateProduct(
        product.name,
        product.price,
        product.image,
        product.amount,
        product.status,
        product.description,
        product.saleOff,
        product.Id
      );
      alert('Update successfully!');
    }

    this.pros = [...this.productService.getProduct()];
  }
}
