import { Component, OnInit } from '@angular/core';
import { RestUsersService } from "..//../services/rest-users.service";
import { User } from "../../pojo-classes/user";
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  id: number;
  user: User;
  submitted = false;

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
  
    updateUser() {
      this.restUsersService.updateUser(this.id, this.user)
        .subscribe(data => {
          console.log(data);
          this.user= new User();
          this.gotoList();
        }, error => console.log(error));
    }
  
    onSubmit() {
      this.updateUser();    
    }
  
    gotoList() {
      this.router.navigate(['listofusers']);
    }

}
