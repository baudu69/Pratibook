import {TestBed} from '@angular/core/testing';

import {FicheOeuvreService} from './fiche-oeuvre.service';

describe('FicheOeuvreService', () => {
  let service: FicheOeuvreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheOeuvreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
