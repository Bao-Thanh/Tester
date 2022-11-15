import { Component, OnInit } from '@angular/core';
import {ProductService} from '../../service/product/product.service'
import { Router } from '@angular/router'
import { CategoryService } from 'src/app/service/category/category.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  productlists : any = {}
  products : any = {}
  product : any = {}
  category : any = {}
  categoryId:number=0;
  errorMessage='';
  errorMessageEdit='';
  errorMessageCreate='';
  constructor(private productService: ProductService, private categoryService: CategoryService, private _route: Router) { }

  ngOnInit(): void {
    this.categoryService.getAllCategory().subscribe({
      next: data => {
        this.category=data
        //this.products=data;
        console.log(data);
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
    }
  });
    this.productService.getAllProduct().subscribe({
      next: data => {
        //this.productlists=data;
        this.products=data;
        console.log(data);
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
    }
  });
  }

  onEdit(id:number){
      this.productService.getProductByID(id).subscribe({
        next: data => {
          this.product=data;
          console.log(data);
        },
        error: err => {console.log(err)
          this.errorMessage = err.error.message;
      }
    });
  }

  onDelete(id:number){
    this.productService.deleteProductByID(id).subscribe({
      next: data => {
        console.log(data);
        
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
        this.reloadPage();
    }
  });
  }
  ngOnClick(event: any){
    this.categoryId=event.target.value;
  }

  onSaveCreate(){
    const {name,price,image,amount,status,description,saleOff}=this.product;
    if(name!=""&&price!=""&&image!=""&&amount!=""&&status!=""&&description!=""&&saleOff!=""){
      if(this.categoryId!=0){
          this.productService.createByCategory(name,price,image,amount,status,description,saleOff,this.categoryId).subscribe({
            next: data => {
              console.log(data);
              this.errorMessageCreate='';
              this.errorMessageEdit='';
              this.reloadPage();
            },
            error: err => {console.log(err)
              this.errorMessage = err.error.message;
          }
      });
    } else this.errorMessageCreate="Please Select Category To Create";
    } else this.errorMessageCreate="Please Fill Info To Create";
  }
  

  onSaveEdit(id:number){
    const {name,price,image,amount,status,description,saleOff}=this.product;
    if(name!=""&&price!=""&&image!=""&&amount!=""&&status!=""&&description!=""&&saleOff!=""){
    this.productService.updateProduct(name,price,image,amount,status,description,saleOff,id).subscribe({
      next: data => {
        console.log(data);
        this.errorMessageCreate='';
        this.errorMessageEdit='';
        this.reloadPage();
      },
      error: err => {console.log(err)
        this.errorMessage = err.error.message;
    }

  });
    }else this.errorMessageEdit="Please Fill Info To Edit";
  }

  reloadPage(): void {
    window.location.reload();
    window.alert("Successfull");
  }
}
