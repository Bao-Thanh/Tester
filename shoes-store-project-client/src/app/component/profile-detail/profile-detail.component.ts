import { Component, OnInit } from '@angular/core';
import { StorageService } from 'src/app/service/storage/storage.service';
import {CustomerService} from '../../service/customer/customer.service'
@Component({
  selector: 'app-profile-detail',
  templateUrl: './profile-detail.component.html',
  styleUrls: ['./profile-detail.component.css']
})
export class ProfileDetailComponent implements OnInit {
  customer : any = {};
  errorMessage='';
  // customers : any = {
  //   name: null,
  //   age: null,
  //   gender: null,
  //   address: null,
  //   phone: null,
  //   email: null,
  //   type:null,
  //   status:null
  // }
  constructor(private customerService:CustomerService, private storageService: StorageService) { }

  ngOnInit(): void {
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

  onSubmit(){
    //const customer =this.customer;
    const {name, age, gender, email, phone, address, type, status} = this.customer;
    if(name!=""&&age!=""&&gender!=""&&email!=""&&phone!=""&&address!=""){
      this.customerService.updateCustomer(name, age, gender, email, phone, address, type, status, this.customer.id).subscribe({
        next: data => {
          console.log("a",data);
          this.errorMessage="";
          this.reloadPage();
        },
        error: err => {console.log(err)
          this.errorMessage = err.error.message;
      }
    });
    } else this.errorMessage='Please Fill Info'
   

  }

  reloadPage(): void {
    window.location.reload();
    window.alert("Successfull");
  }
}
