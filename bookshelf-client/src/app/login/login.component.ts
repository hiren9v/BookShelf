import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from "../services/authentication.service";
import { AbstractUser } from "../pojo-classes/abstractuser";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: AbstractUser = new AbstractUser();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }
  
  handleLogin() {
    this.authenticationService.login(this.user).subscribe((result)=> {
      console.log(result);
      this.router.navigate(['listofbooks']);
    }, (error) => {
      console.log(error);
    });      
  }
}
