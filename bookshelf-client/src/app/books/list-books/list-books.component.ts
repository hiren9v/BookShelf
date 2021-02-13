import { Component, OnInit } from '@angular/core';
import { RestBooksService } from "..//../services/rest-books.service";
import { Book } from "../../pojo-classes/book";
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.css']
})
export class ListBooksComponent implements OnInit {

  books : Observable<[Book]>;

  constructor(private restBooksService :RestBooksService,
    private router: Router) { 
  }

  ngOnInit( ): void {
    this.reloadData();
  }

  reloadData(){
    this.books = this.restBooksService.getAllBooks();
    console.log(this.books);
  }

  deleteBook(id: number){
    this.restBooksService.deleteBook(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error)
      );
  }

  bookDetails(id: number){
    this.router.navigate(['detailsofbook', id]);
  }


  updateBook(id: number){
    this.router.navigate(['updatebook', id]);
  }

}
