import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Town} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class TownService {
  private townsUrl = 'http://localhost:8080/towns';


  constructor(private http: HttpClient) {
  }

  getAllTowns(): Observable<any> {
    return this.http.get<Town>(this.townsUrl);
  }

  getTownById(id: number): Observable<Town> {
    return this.http.get<Town>(this.townsUrl + "/" + id);
  }

  joinTown(townId: number): Observable<Town> {
    return this.http.get<Town>(this.townsUrl + "/" + townId + "/join");
  }
}
