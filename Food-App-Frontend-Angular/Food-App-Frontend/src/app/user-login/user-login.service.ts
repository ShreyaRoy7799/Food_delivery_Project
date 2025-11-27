import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserLoginRequest } from "../dto/UserLoginRequest";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class UserLoginService {

  private url = 'http://localhost:8080/api/v1.0/login';   // ✅ Correct URL

  constructor(private http: HttpClient) {}

  validateLoginAndGetConfirmedUser(req: UserLoginRequest): Observable<any> {
    return this.http.post<any>(this.url, req);
  }
}
