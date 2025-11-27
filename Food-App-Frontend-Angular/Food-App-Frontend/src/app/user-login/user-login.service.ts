import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserLoginRequest } from "../dto/UserLoginRequest";
import { HttpClient } from "@angular/common/http";


@Injectable({
    providedIn: 'root'
})
export class UserLoginService {

    private url = 'http://localhost:9099/api/v1.0/login';

    constructor(private http: HttpClient) { }

    validateLoginAndGetConfirmedUser(userLoginRequest: UserLoginRequest): Observable<any>{
        return this.http.post<any>(this.url, userLoginRequest);
    }

}
