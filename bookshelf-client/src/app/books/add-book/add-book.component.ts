import { Component, OnInit } from '@angular/core';
import { RestBooksService } from "..//../services/rest-books.service";
import { Book } from "../../pojo-classes/book";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  book : Book = new Book();
  submitted = false;

  constructor(private restBooksService :RestBooksService,
    private router: Router) { }

  ngOnInit(): void {
  }

  newBook(): void{
    this.submitted = false;
    this.book= new Book();
  }

  save() {
    this.restBooksService
    .addBook(this.book).subscribe(
    data => {
      console.log(data)
      this.book = new Book();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList(){
    this.router.navigate(['listofbooks']);
  }

}
