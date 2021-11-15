import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaderResponse,
  HttpHeaders,
  HttpResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { JwtService } from './jwt.service';

@Injectable({
  providedIn: 'root',
})
export class MainService {
  constructor(private httpClient: HttpClient, private jwtService: JwtService) {}

  /**
   * Setting Headers for API Request
   */
  private setHeaders(): HttpHeaders {
    const headersConfig = {
      'Content-Type': 'application/json',
      Accept: 'application/json',
      Authorization: '',
    };
    // set auth token header
    if (this.jwtService.getToken()) {
      headersConfig.Authorization = `Bearer ${this.jwtService.getToken()}`;
    }
    return new HttpHeaders(headersConfig);
  }

  /**
   * format errors
   */
  formatErrors(error) {
    return throwError(error.error);
  }

  get(path, params?): Observable<any> {
    return this.httpClient
      .get(`${environment.api_url}${path}`, {
        headers: this.setHeaders(),
        params,
      })
      .pipe(
        map((res) => res),
        catchError(this.formatErrors)
      );
  }

  post(path, body, params?): Observable<any> {
    return this.httpClient
      .post(`${environment.api_url}${path}`, JSON.stringify(body), {
        headers: this.setHeaders(),
        params,
      })
      .pipe(
        map((res) => {
          return res;
        }),
        catchError(this.formatErrors)
      );
  }

  put(path, body, params?): Observable<any> {
    return this.httpClient
      .put(`${environment.api_url}${path}`, JSON.stringify(body), {
        headers: this.setHeaders(),
        params,
      })
      .pipe(
        map((res) => res),
        catchError(this.formatErrors)
      );
  }

  delete(path, params?): Observable<any> {
    return this.httpClient
      .delete(`${environment.api_url}${path}`, {
        headers: this.setHeaders(),
        params,
      })
      .pipe(
        map((res) => res),
        catchError(this.formatErrors)
      );
  }
}
