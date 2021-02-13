import { Component, OnInit } from '@angular/core';
import { RestBooksService } from "..//../services/rest-books.service";
import { Book } from "../../pojo-classes/book";
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  id: number;
  book: Book;

  constructor(private restBooksService :RestBooksService,
    private router: Router,
    private route : ActivatedRoute) { }

  ngOnInit(): void {
    this.book = new Book();
    this.id = this.route.snapshot.params['id'];

    this.restBooksService.getBookById(this.id)
        .subscribe(data => {
          console.log(data)
          this.book = data;
        }, error => console.log(error));
  }

  list(){
    this.router.navigate(['listofbooks']);
  }
}
