import { TestBed } from '@angular/core/testing';

import { ListeOeuvreService } from './liste-oeuvre.service';

describe('ListeOeuvreService', () => {
  let service: ListeOeuvreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListeOeuvreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
