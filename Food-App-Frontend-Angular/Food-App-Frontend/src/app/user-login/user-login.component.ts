import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserLoginService } from './user-login.service';
import { UserLoginRequest } from '../dto/UserLoginRequest';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
})
export class UserLoginComponent {

  sub = new Subscription();
  errorMessage = "";
  isSuccess = false;
  successMessage = "";
errorOccured: any;

  constructor(
    private router: Router,
    private userLoginService: UserLoginService,
    private authService: AuthService
  ) {}

 onClickSubmit(userLoginForm: NgForm) {
  this.errorOccured = false;
  this.errorMessage = "";

  const form = userLoginForm.value;

  let req = new UserLoginRequest();
  req.email = form.username;
  req.password = form.userPassword;

  this.sub = this.userLoginService.validateLoginAndGetConfirmedUser(req)
    .subscribe({
      next: (res) => {
        console.log("Login response:", res);

        // ❗ Backend sends:  { status: "success", message: "...", ... }
        if (res.status !== "success") {
          this.errorOccured = true;
          this.errorMessage = res.message || "Invalid username or password";
          return; // STOP: do not redirect
        }

        // ⭐ If login is success
        localStorage.setItem("LOGGED_IN_USER", req.email);
        localStorage.setItem("ROLE", res.role || "USER");

        this.isSuccess = true;
        this.successMessage = "Login Successful! Redirecting...";

        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 800);
      },

      error: (err) => {
        console.log("HTTP Error:", err);
        this.errorOccured = true;
        this.errorMessage = err.error?.message || "Server error";
      }
    });
}

}
