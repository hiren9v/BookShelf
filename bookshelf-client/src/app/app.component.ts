import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthenticationService } from "./services/authentication.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'The Bookshelf';

  constructor(public route: ActivatedRoute,
    public router: Router,
    public authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }
  
  handleLogout(){
    this.authenticationService.logout().subscribe((result)=> {
      console.log(result);
    }, (error) => {
      console.log(error);
    });
  }
}
