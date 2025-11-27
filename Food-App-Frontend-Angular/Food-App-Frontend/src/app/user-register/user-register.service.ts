import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDto } from '../dto/UserDto';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class UserRegisterService {

  private api = environment.apiUrl;

  constructor(private http: HttpClient) {}

  createUser(user: UserDto): Observable<any> {
    return this.http.post<any>(`${this.api}/register`, user);
  }
}
