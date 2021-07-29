import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient } from '@angular/common/http';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private baseURl='http://localhost:8080/covidVaccineNotifier';

  constructor(private httpClient: HttpClient) { }

  addUser(user: User): Observable<Object>{
    return  this.httpClient.post(`${this.baseURl}`,user);
  }
}
