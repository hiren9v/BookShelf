import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AbstractUser } from "../pojo-classes/abstractuser";
import { map } from 'rxjs/operators';
import { FormGroup } from '@angular/forms';
import { CookieService } from "ngx-cookie-service";
import { CompileShallowModuleMetadata } from '@angular/compiler';


@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {

  private baseUrl = 'http://localhost:8080/login';
  private logoutURL = 'http://localhost:8080/logout';

  constructor(
    private http: HttpClient, 
    private cookieService:CookieService) { }

  login(user : Object) {
    return this.http.post(this.baseUrl,user, {withCredentials:true});
  }

  logout() {
    return this.http.post(this.logoutURL, '' ,{withCredentials:true});
  }

  isUserLoggedIn() {
    let user = this.cookieService.get('username');
    console.log()
    if (user == null || user == '') {
      return false;
    }
    else{
      return true;
    }
    
  }

  getLoggedInUserName() {
    let user = this.cookieService.get('username');
    if (user === null) {
      return '';
    }
    else{
      return user;
    }
    
  }

  isAdmin(){
    let user = this.cookieService.get('role');
    if (user == "ROLE_ADMIN") {
      return true;
    }
    else{
      return false;
    }
  }

  isLibrarianOrAdmin(){
    let user = this.cookieService.get('role');
    if (user == "ROLE_LIBRARIAN" || user == "ROLE_ADMIN") {
      return true;
    }
    else{
      return false;
    }
  }

}
