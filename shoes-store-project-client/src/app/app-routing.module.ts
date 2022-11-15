import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './component/login/login.component';
import { SignupComponent } from './component/signup/signup.component';
import { ProductComponent } from './component/product/product.component';
import { ProductsingleComponent } from './component/productsingle/productsingle.component';
import { CartComponent } from './component/cart/cart.component';
import { OrdersComponent } from './component/orders/orders.component';
import { OrderDetailComponent } from './component/order-detail/order-detail.component';
import { ProfileDetailComponent } from './component/profile-detail/profile-detail.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';

const routes: Routes = [
  { path:"login", component:LoginComponent },
  { path:"signup", component:SignupComponent },
  { path:"", component:ProductComponent },
  { path:"product", component:ProductComponent },
  { path:"product/:id", component:ProductsingleComponent },
  { path:"cart", component:CartComponent },
  { path:"order", component:OrdersComponent },
  { path:"order/:id", component:OrderDetailComponent },
  { path:"profile-details", component:ProfileDetailComponent },
  { path:"dashboard", component:DashboardComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }