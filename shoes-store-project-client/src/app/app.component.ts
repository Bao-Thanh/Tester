import { Component } from '@angular/core';
import { CartService } from './service/storage/cart.service';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'electronic-store-project-client';

  constructor(
    public cartService: CartService,
  ) { }

  items : any = [];

  // get total() {
  //   return this.items.reduce(
  //     (sum : number, x: number) => ({
  //       qtyTotal: 1,
  //       variationCost: sum + x * x;
  //     }),
  //     { qtyTotal: 1, variationCost: 0 }
  //   ).variationCost;
  // }

  changeSubtotal(item:any, index:any) {
    const qty = item.qtyTotal;
    const amt = item.variationCost;
    const subTotal = amt * qty;
    //const subTotal_converted = this.currencyPipe.transform(subTotal, "USD");

    // this.subTotalItems.toArray()[
    //   index
    // ].nativeElement.innerHTML = subTotal_converted;
    this.cartService.saveCart();
  }

  //----- remove specific item
  removeFromCart(item:any) {
    this.cartService.removeItem(item);
    this.items = this.cartService.getItems();
  }

  //----- add item to cart
  addToCart(item:any) {
    if (!this.cartService.itemInCart(item)) {
      item.qtyTotal = 1;
      this.cartService.addToCart(item); //add items in cart
      this.items = [...this.cartService.getItems()];
    }
  }

  ngOnInit(): void {
    this.cartService.loadCart();
    this.items = this.cartService.getItems();
  }

}
