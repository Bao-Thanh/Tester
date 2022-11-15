import { Component, OnInit } from '@angular/core'
import { Router } from '@angular/router'
import { AuthService } from 'src/app/service/auth/auth.service'
import { CustomerService } from 'src/app/service/customer/customer.service'

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
    cfpassword: null,
  };
  customer : any = {
    name: null,
    age: null,
    gender: null,
    address: null,
    phone: null,
    email: null,
    status:"active"
  }
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(private _service: AuthService, private __service: CustomerService, private _route: Router) {}

  ngOnInit(): void {
  }
  onSubmit(): void {
    const { username, password, cfpassword } = this.form;
    const status = "active";
    const role = "user";
    const customer = this.customer;
    console.log("cus",customer)
    if(username!=null&&password!=null&&cfpassword!=null&&customer.name!=null&&customer.age!=null&&customer.gender!=null&&customer.address!=null&&customer.phone!=null&&customer.email!=null){
      if (password===cfpassword) {
        this._service.register(username, password, status, role, customer).subscribe({
          next: data => {
            console.log(data);
            this.isSuccessful = true;
            this.isSignUpFailed = false;
            this._route.navigate(['/login']);
          },
          error: err => {
            this.errorMessage = err.error.message;
            this.isSignUpFailed = true;
          }
        });
      } else this.errorMessage='ConfirmPassword is not true';
    }else this.errorMessage="Please fill info";
  }
}
