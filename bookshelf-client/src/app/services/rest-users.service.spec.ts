import { TestBed } from '@angular/core/testing';

import { RestUsersService } from './rest-users.service';

describe('RestUsersService', () => {
  let service: RestUsersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RestUsersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
