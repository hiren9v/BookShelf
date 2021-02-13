import { Component, OnInit } from '@angular/core';
import { RestBooksService } from "..//../services/rest-books.service";
import { Book } from "../../pojo-classes/book";
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-book',
  templateUrl: './update-book.component.html',
  styleUrls: ['./update-book.component.css']
})
export class UpdateBookComponent implements OnInit {

  id: number;
  book: Book;
  submitted = false;

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

  updateBook() {
    this.restBooksService.updateBook(this.id, this.book)
      .subscribe(data => {
        console.log(data);
        this.book = new Book();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateBook();    
  }

  gotoList() {
    this.router.navigate(['listofbooks']);
  }

}
