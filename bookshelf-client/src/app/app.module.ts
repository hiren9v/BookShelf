import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UpdateUserComponent } from './users/update-user/update-user.component';
import { ListBooksComponent } from './books/list-books/list-books.component';
import { ListUsersComponent } from "./users/list-users/list-users.component";
import { BookDetailsComponent } from './books/book-details/book-details.component';
import { AddBookComponent } from './books/add-book/add-book.component';
import { UpdateBookComponent } from './books/update-book/update-book.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { UserDetailsComponent } from './users/user-details/user-details.component';
import { AddUserComponent } from './users/add-user/add-user.component';

@NgModule({
  declarations: [
    AppComponent,
    UpdateUserComponent,
    UpdateBookComponent,
    ListBooksComponent,
    ListUsersComponent,
    BookDetailsComponent,
    UserDetailsComponent,
    AddBookComponent,
    AddUserComponent,
    LoginComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
