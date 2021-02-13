import { Component, OnInit } from '@angular/core';
import { RestUsersService } from "..//../services/rest-users.service";
import { User } from "../../pojo-classes/user";
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id: number;
  user: User;

  constructor(private restUsersService: RestUsersService,
    private router: Router,
    private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.user = new User();
    this.id = this.route.snapshot.params['id'];

    this.restUsersService.getUsersById(this.id)
        .subscribe(data => {
          console.log(data)
          this.user = data;
        }, error => console.log(error));
  }

  list(){
    this.router.navigate(['listofusers']);
  }

}
