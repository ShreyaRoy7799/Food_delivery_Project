import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const isLoggedIn = localStorage.getItem("ADMIN_AUTH") === "LOGGED_IN";

    if (!isLoggedIn) {
      alert("Access denied! Admin login required.");
      this.router.navigate(['/admin-login']);
      return false;
    }

    return true;
  }
}
