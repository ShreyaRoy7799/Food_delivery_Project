import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private userSource = new BehaviorSubject<string | null>(localStorage.getItem("LOGGED_IN_USER"));
  user$ = this.userSource.asObservable();

  setUser(username: string | null) {
    if (username) {
      localStorage.setItem("LOGGED_IN_USER", username);
    } else {
      localStorage.removeItem("LOGGED_IN_USER");
      localStorage.removeItem("TOKEN");
    }
    this.userSource.next(username);
  }

  getUser() {
    return this.userSource.value;
  }
}
