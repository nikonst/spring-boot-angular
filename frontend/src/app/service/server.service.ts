import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomResponse } from '../interface/custom-response';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { Server } from '../interface/server';
import { Status } from '../enum/status.enum';

const headers = new HttpHeaders()
.set('content-type', 'application/json')
.set('Access-Control-Allow-Origin', '*')
.set('Access-Control-Allow-Methods', 'HEAD, DELETE, POST, GET, OPTIONS, PUT, PATCH')
.set('Connection','keep-alive')
.set('Accept','/');

console.log(headers)

@Injectable({
  providedIn: 'root'
})


export class ServerService {
  private handelError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    return throwError(`An error occured - Error code: ${error.status}`)
  }

  private readonly apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

 

  servers$ = <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/server/list`, {'headers': headers})
      .pipe(
        tap(console.log),
        catchError(this.handelError)
      )

  save$ = (server: Server) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/server/list`, server)
      .pipe(
        tap(console.log),
        catchError(this.handelError)
      )

  ping$ = (ipAddress: string) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/server/ping'${ipAddress}`)
      .pipe(
        tap(console.log),
        catchError(this.handelError)
      )

  delete$ = (serverId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/server/delete'${serverId}`)
      .pipe(
        tap(console.log),
        catchError(this.handelError)
      )

  filter$ = (status: Status, response: CustomResponse) => <Observable<CustomResponse>>
    new Observable<CustomResponse>(
      subscriber => {
        console.log(response)
        subscriber.next(
          status === 'ALL' ? {
            ...response, message: `Servers filtered by ${status} status`
          } : {
            ...response, message: response.data.servers.filter(server =>
              server.status === status).length > 0
              ?
              `Servers filtered by ${status === Status.SERVER_UP
                ? 'SERVER UP' : 'SERVER DOWN'} status`
              : `No servers of ${status} found`,
            data: {
              servers: response.data.servers.filter(server =>
                server.status === status)
            }
          }
        )
        subscriber.complete()
      }
    )
}
