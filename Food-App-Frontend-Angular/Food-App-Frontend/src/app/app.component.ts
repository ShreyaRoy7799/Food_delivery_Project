import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  // loggedInUser: string | null = null;
  // showProfileMenu = false;
  loggedInUser: string | null = null;
userRole: string | null = null;
showProfileMenu = false;

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

 ngOnInit(): void {
  this.loggedInUser = localStorage.getItem('LOGGED_IN_USER');
  this.userRole = localStorage.getItem('ROLE'); 
}
  toggleProfileMenu() {
    this.showProfileMenu = !this.showProfileMenu;
  }

  logout() {
    this.authService.setUser(null);
    this.router.navigate(['/']);
  }
}
