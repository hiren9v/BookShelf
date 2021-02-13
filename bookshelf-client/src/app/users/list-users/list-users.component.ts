import { Component, OnInit } from '@angular/core';
import { RestUsersService } from "..//../services/rest-users.service";
import { User } from "../../pojo-classes/user";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users : Observable<[User]>;

  constructor(private restUsersService: RestUsersService,
    private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData(){
    this.users = this.restUsersService.getAllUsers();
    console.log(this.users);
  }

  deleteUser(id: number){
    this.restUsersService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error)
      );
  }

  userDetails(id: number){
    this.router.navigate(['detailsofuser', id]);
  }


  updateUser(id: number){
    this.router.navigate(['updateuser', id]);
  }

}
