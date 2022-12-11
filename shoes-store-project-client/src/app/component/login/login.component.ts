import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth/auth.service';
import { StorageService } from '../../service/storage/storage.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null,
  };
  isLoggedIn = false;
  isLoginFailed = false;
  isSuccessful = false;
  errorMessage = '';

  constructor(
    private authService: AuthService,
    private _route: Router,
    private storageService: StorageService
  ) {}
  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this._route.navigate(['/product']);
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;
    if (username != '' && password != '') {
      this.authService.login(username, password).subscribe({
        next: (data) => {
          this.storageService.saveUser(data);
          //console.log(data);
          this.isSuccessful = true;
          this.isLoginFailed = false;
          this._route.navigate(['/product']);
        },
        error: (err) => {
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
        },
      });
    } else this.errorMessage = 'Please fill username and password';
  }
}
