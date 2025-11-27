import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent {

  username = "";
  password = "";

  constructor(private router: Router) {}

  login() {
    if (this.username === "admin" && this.password === "admin123") {
      localStorage.setItem("ADMIN_AUTH", "LOGGED_IN");
      alert("Admin login successful!");
      this.router.navigate(['/admin/dashboard']);
    } else {
      alert("Invalid credentials!");
    }
  }
}
