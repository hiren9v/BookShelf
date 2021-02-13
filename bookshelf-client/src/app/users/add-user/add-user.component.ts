import { Component, OnInit } from '@angular/core';
import { RestUsersService } from "..//../services/rest-users.service";
import { User } from "../../pojo-classes/user";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user : User = new User();
  submitted = false;

  constructor(private restUsersService: RestUsersService,
    private router: Router) { }

  ngOnInit(): void {
  }

  newUserr(): void{
    this.submitted = false;
    this.user= new User();
  }

  save() {
    this.restUsersService
    .addUser(this.user).subscribe(
    data => {
      console.log(data)
      this.user = new User();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList(){
    this.router.navigate(['listofusers']);
  }
}
