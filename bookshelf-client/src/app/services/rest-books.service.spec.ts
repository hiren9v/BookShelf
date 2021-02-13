import { TestBed } from '@angular/core/testing';

import { RestBooksService } from './rest-books.service';

describe('RestBooksService', () => {
  let service: RestBooksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestBooksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
