import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RestUsersService {

  private baseUrl = 'http://localhost:8080/api/v1/users/';

  constructor(private http: HttpClient) { }

  getUsersById(id: number): Observable<any>{
    return this
    .http.get(`${this.baseUrl}${id}`, {withCredentials:true});  
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, {withCredentials:true});
  }

  addUser( user: Object ): Observable<Object>{
    return this.http.post(`${this.baseUrl}`, user, {withCredentials:true});
  }

  updateUser(id: number, value: any): Observable<Object>{
    return this.http.put(`${this.baseUrl}${id}`, value, {withCredentials:true});
  }

  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}${id}`, { responseType: 'text',withCredentials:true });
  }


}
