import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Town} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private messageUrl = 'http://localhost:8080/messages';


  constructor(private http: HttpClient) {
  }

  createMessage(title: string, text: string, townId: number): Observable<any> {
    return this.http.post(
      this.messageUrl,
      JSON.stringify({title: title, text: text, townId: townId}))
  }
}
