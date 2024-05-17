import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private usersUrl = 'http://localhost:8080/users';


  constructor(private http: HttpClient) {
  }

  // For Profile and the id from the user who is logged in
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(this.usersUrl + "/" + id);
  }
}
