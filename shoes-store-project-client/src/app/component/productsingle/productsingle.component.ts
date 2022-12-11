import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../service/product/product.service';
@Component({
  selector: 'app-productsingle',
  templateUrl: './productsingle.component.html',
  styleUrls: ['./productsingle.component.css'],
})
export class ProductsingleComponent implements OnInit {
  product: any = {};
  errorMessage = '';
  pros: any[] = [];
  quantity = 1;
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  form: any = {
    quantity: null,
  };

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.productService.getProductByID(id).subscribe({
      next: (data) => {
        this.product = data;
        console.log(data);
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = err.error.message;
      },
    });
  }

  addToCart(product: any) {
    //return this.quantity;
    if (!this.productService.productInCart(product)) {
      product.amount = this.quantity;
      this.productService.addToCart(product);
      alert('Add to cart successfully!');
    } else {
      product.amount = this.quantity;
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
