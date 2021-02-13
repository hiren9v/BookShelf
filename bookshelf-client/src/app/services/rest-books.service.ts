import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestBooksService {

  private baseUrl = 'http://localhost:8080/api/v1/books/';

  constructor(private http: HttpClient) { }

  getAllBooks(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, {withCredentials:true});
  }

  getBookById(id: number): Observable<any>{
    return this.http.get(`${this.baseUrl}${id}`,{withCredentials:true});  
  }

  addBook( book: Object ): Observable<Object>{
    return this.http.post(`${this.baseUrl}`, book, {withCredentials:true});
  }

  updateBook(id: number, value: any): Observable<Object>{
    return this.http.put(`${this.baseUrl}${id}`, value, {withCredentials:true});
  }

  deleteBook(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}${id}`, { responseType: 'text', withCredentials:true });
  }
}
