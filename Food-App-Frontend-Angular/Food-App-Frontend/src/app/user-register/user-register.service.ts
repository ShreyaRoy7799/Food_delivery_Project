import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserDto } from "../dto/UserDto";

@Injectable({
    providedIn: 'root'
})
export class UserRegisterService {

    private url = 'http://localhost:9099/api/v1.0/register';

    constructor(private http: HttpClient) { }

    createUser(newuser: UserDto): Observable<any>{
        return this.http.post<UserDto>(this.url, newuser);
    }

}
