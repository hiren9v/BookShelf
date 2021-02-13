import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ListBooksComponent} from "./books/list-books/list-books.component";
import { ListUsersComponent } from './users/list-users/list-users.component';
import { AddUserComponent } from './users/add-user/add-user.component';
import { UpdateUserComponent } from './users/update-user/update-user.component';
import { UserDetailsComponent } from './users/user-details/user-details.component';
import { AddBookComponent } from './books/add-book/add-book.component';
import { UpdateBookComponent } from './books/update-book/update-book.component';
import { Book } from './pojo-classes/book';
import { BookDetailsComponent } from './books/book-details/book-details.component';

const routes: Routes = [
  {path: '', component: LoginComponent, pathMatch:'full'},
  {path: 'login', component: LoginComponent},
  {path: 'listofusers', component: ListUsersComponent},
  {path: 'adduser', component: AddUserComponent},
  {path: 'updateuser/:id', component:UpdateUserComponent},
  {path: 'detailsofuser/:id', component: UserDetailsComponent},
  {path: 'listofbooks', component: ListBooksComponent},
  {path: 'addbook', component: AddBookComponent},
  {path: 'updatebook/:id', component:UpdateBookComponent},
  {path: 'detailsofbook/:id', component: BookDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
