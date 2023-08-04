import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomResponse } from '../interface/custom-response';
import { Observable, catchError, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class ServerService {
  handelError(handelError: any): Observable<never> {
    throw new Error('Method not implemented.');
  }

  private readonly apiUrl = 'any';

  constructor(private http: HttpClient) { }

  server$ = <Observable<CustomResponse>>
  this.http.get<CustomResponse>(`${this.apiUrl}/server/list`)
  .pipe(
    tap(console.log),
    catchError(this.handelError)
  )
}
