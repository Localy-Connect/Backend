import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../../model/model";

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private messageUrl = 'http://localhost:8080/messages';


  constructor(private http: HttpClient) {
  }

  getAllMessageFromTown(id: number): Observable<Message> {
    return this.http.get<Message>(this.messageUrl + "/byTownId/" + id);
  }

  createMessage(title: string, text: string, townId: number): Observable<any> {
    return this.http.post(
      this.messageUrl,
      JSON.stringify({title: title, text: text, townId: townId}))
  }
}
