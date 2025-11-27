import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title(title: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private router: Router) {}

  pageTitle = 'Food Delivery Application';
  loggedInUser: string = localStorage.getItem('LOGGED_IN_USER')!;
  userIsLoggedIn: Boolean = false;

  ngOnInit(): void {
    setInterval(() => {
      this.loggedInUser = localStorage.getItem('LOGGED_IN_USER')!;
    }, 1);
  }

  logout(){
    localStorage.removeItem('LOGGED_IN_USER');
    localStorage.removeItem('TOKEN');
    this.router.navigate(['welcome'])
  }
}
