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

  getUserById(id: number | undefined): Observable<User> {
    return this.http.get<User>(this.usersUrl + "/" + id);
  }

  updateUser(user: User): Observable<any> {
    return this.http.put<any>(`${this.usersUrl}`, user);
  }

  changePassword(currentPassword: string, newPassword: string): Observable<void> {
    return this.http.patch<void>(this.usersUrl + "/password", JSON.stringify({
      currentPassword: currentPassword,
      newPassword: newPassword
    }));
  }
}
